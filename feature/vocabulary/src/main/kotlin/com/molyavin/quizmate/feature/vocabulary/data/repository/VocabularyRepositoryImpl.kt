package com.molyavin.quizmate.feature.vocabulary.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFirestoreDataSource
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFolderDao
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFolderEntity
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyWordDao
import com.molyavin.quizmate.feature.vocabulary.data.remote.model.VocabularyFolderDto
import com.molyavin.quizmate.feature.vocabulary.data.remote.model.VocabularyWordDto
import com.molyavin.quizmate.feature.vocabulary.domain.model.Folder
import com.molyavin.quizmate.feature.vocabulary.domain.model.Word
import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
import com.molyavin.quizmate.feature.vocabulary.mapper.toDomain
import com.molyavin.quizmate.feature.vocabulary.mapper.toEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Date
import javax.inject.Inject

/**
 * Реалізація VocabularyRepository з Room (кеш) + Firestore (синхронізація)
 */
class VocabularyRepositoryImpl @Inject constructor(
    private val vocabularyWordDao: VocabularyWordDao,
    private val vocabularyFolderDao: VocabularyFolderDao,
    private val vocabularyFirestoreDataSource: VocabularyFirestoreDataSource,
    private val firebaseAuth: FirebaseAuth
) : VocabularyRepository {

    private val scope = CoroutineScope(Dispatchers.IO)

    /**
     * Ручна синхронізація конкретної папки з Firestore
     */
    suspend fun syncFolderFromFirestore(folderId: Long?) {
        if (firebaseAuth.currentUser == null) {
            Timber.w("User not authenticated, skipping sync")
            return
        }

        Timber.d("=== Manual folder sync started (folderId: $folderId) ===")

        try {
            if (folderId == null) {
                // Якщо папка не обрана, синхронізуємо все
                syncFromFirestore()
                return
            }

            // Отримати firestoreId папки
            val folder = vocabularyFolderDao.getFolderById(folderId)
            val folderFirestoreId = folder?.firestoreId

            if (folderFirestoreId == null) {
                Timber.w("Folder $folderId has no firestoreId, syncing all")
                syncFromFirestore()
                return
            }

            // Синхронізувати тільки слова цієї папки
            val remoteWords = vocabularyFirestoreDataSource.getWordsByFolder(folderFirestoreId)
            Timber.d("Fetched ${remoteWords.size} words from Firestore for folder $folderId")

            val remoteWordFirestoreIds = remoteWords.map { it.id }.toSet()

            remoteWords.forEach { dto ->
                try {
                    val existingWord = vocabularyWordDao.getWordByFirestoreId(dto.id)
                    val localFolderId = dto.folderId?.let { firestoreFolderId ->
                        vocabularyFolderDao.getFolderByFirestoreId(firestoreFolderId)?.id
                    }

                    if (existingWord != null) {
                        val updatedWord = dto.toEntity(
                            localId = existingWord.id,
                            localFolderId = localFolderId
                        )
                        vocabularyWordDao.updateWord(updatedWord)
                        Timber.d("Updated word: ${dto.english}")
                    } else {
                        val newWord = dto.toEntity(localFolderId = localFolderId)
                        vocabularyWordDao.insertWord(newWord)
                        Timber.d("Inserted new word: ${dto.english}")
                    }
                } catch (e: Exception) {
                    Timber.e(e, "Error syncing word: ${dto.id}")
                }
            }

            // Видалити локальні слова цієї папки, яких немає в Firestore
            val localWordsInFolder = vocabularyWordDao.getWordsByFolderSync(folderId)
            localWordsInFolder.forEach { localWord ->
                if (localWord.firestoreId != null && localWord.firestoreId !in remoteWordFirestoreIds) {
                    Timber.d("Deleting local word not in Firestore: ${localWord.english}")
                    vocabularyWordDao.deleteWord(localWord)
                }
            }

            Timber.d("=== Folder sync completed: ${remoteWords.size} words ===")
        } catch (e: Exception) {
            Timber.e(e, "Fatal error during folder sync")
            throw e
        }
    }

    /**
     * Ручна синхронізація з Firestore (викликається через swipe-to-refresh)
     */
    override suspend fun syncFromFirestore() {
        if (firebaseAuth.currentUser == null) {
            Timber.w("User not authenticated, skipping sync")
            return
        }

        Timber.d("=== Manual sync started ===")

        try {
            // Спочатку синхронізуємо папки
            val remoteFolders = vocabularyFirestoreDataSource.getFolders()
            Timber.d("Fetched ${remoteFolders.size} folders from Firestore")

            val remoteFirestoreIds = remoteFolders.map { it.id }.toSet()

            remoteFolders.forEach { dto ->
                try {
                    val existingFolder = vocabularyFolderDao.getFolderByFirestoreId(dto.id)

                    if (existingFolder != null) {
                        val updatedFolder = dto.toEntity(localId = existingFolder.id)
                        vocabularyFolderDao.insertFolder(updatedFolder)
                        Timber.d("Updated folder: ${dto.name}")
                    } else {
                        val newFolder = dto.toEntity()
                        vocabularyFolderDao.insertFolder(newFolder)
                        Timber.d("Inserted new folder: ${dto.name}")
                    }
                } catch (e: Exception) {
                    Timber.e(e, "Error syncing folder: ${dto.id}")
                }
            }

            // Видалити локальні папки, яких немає в Firestore
            val allLocalFolders = vocabularyFolderDao.getAllFoldersSync()
            allLocalFolders.forEach { localFolder ->
                if (localFolder.firestoreId != null && localFolder.firestoreId !in remoteFirestoreIds) {
                    Timber.d("Deleting local folder not in Firestore: ${localFolder.name}")
                    vocabularyFolderDao.deleteFolder(localFolder.id)
                } else if (localFolder.firestoreId == null) {
                    // Папка без firestoreId - потрібно завантажити в Firestore
                    Timber.d("Uploading local folder without firestoreId: ${localFolder.name}")
                    val dto = VocabularyFolderDto.fromEntity(localFolder, firebaseAuth.currentUser!!.uid)
                    vocabularyFirestoreDataSource.saveFolder(dto).onSuccess { savedDto ->
                        vocabularyFolderDao.insertFolder(localFolder.copy(firestoreId = savedDto.id))
                        Timber.d("Uploaded folder to Firestore: ${localFolder.name}, got firestoreId: ${savedDto.id}")
                    }
                }
            }

            val remoteWords = vocabularyFirestoreDataSource.getWords()
            Timber.d("Fetched ${remoteWords.size} words from Firestore")

            val remoteWordFirestoreIds = remoteWords.map { it.id }.toSet()

            val allLocalWords = vocabularyWordDao.getAllWordsSync()

            val localWordsMap = allLocalWords.associateBy { it.firestoreId }
            val localWordsByName = allLocalWords.filter { it.firestoreId == null }
                .associateBy { it.english.lowercase() }

            remoteWords.forEach { dto ->
                try {
                    // Спочатку шукаємо за firestoreId
                    var existingWord = localWordsMap[dto.id]

                    // Якщо не знайшли, шукаємо за назвою (для слів без firestoreId)
                    if (existingWord == null) {
                        existingWord = localWordsByName[dto.english.lowercase()]
                    }

                    // Знайти локальний folderId за firestoreId папки
                    val localFolderId = dto.folderId?.let { firestoreFolderId ->
                        vocabularyFolderDao.getFolderByFirestoreId(firestoreFolderId)?.id
                    }

                    if (existingWord != null) {
                        val updatedWord = dto.toEntity(
                            localId = existingWord.id,
                            localFolderId = localFolderId
                        )
                        vocabularyWordDao.updateWord(updatedWord)
                    } else {
                        val newWord = dto.toEntity(localFolderId = localFolderId)
                        vocabularyWordDao.insertWord(newWord)
                        Timber.d("Inserted new word: ${dto.english}")
                    }
                } catch (e: Exception) {
                    Timber.e(e, "Error syncing word: ${dto.id}")
                }
            }

            allLocalWords.forEach { localWord ->
                if (localWord.firestoreId != null && localWord.firestoreId !in remoteWordFirestoreIds) {
                    Timber.d("Deleting local word not in Firestore: ${localWord.english}")
                    vocabularyWordDao.deleteWord(localWord)
                } else if (localWord.firestoreId == null) {
                    // Слово без firestoreId - потрібно завантажити в Firestore
                    Timber.d("Uploading local word without firestoreId: ${localWord.english}")
                    val folderFirestoreId = localWord.folderId?.let { folderId ->
                        vocabularyFolderDao.getFolderById(folderId)?.firestoreId
                    }
                    val dto = VocabularyWordDto.fromEntity(
                        localWord,
                        firebaseAuth.currentUser!!.uid,
                        folderFirestoreId
                    )
                    vocabularyFirestoreDataSource.saveWord(dto).onSuccess { savedDto ->
                        vocabularyWordDao.updateWord(localWord.copy(firestoreId = savedDto.id))
                        Timber.d("Uploaded word to Firestore: ${localWord.english}, got firestoreId: ${savedDto.id}")
                    }
                }
            }

            Timber.d("=== Manual sync completed: ${remoteFolders.size} folders, ${remoteWords.size} words ===")
        } catch (e: Exception) {
            Timber.e(e, "Fatal error during manual sync")
            throw e
        }
    }

    override fun getAllWords(): Flow<List<Word>> {
        return vocabularyWordDao.getAllWords().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getWordById(id: Long): Word? {
        return vocabularyWordDao.getWordById(id)?.toDomain()
    }

    override fun searchWords(query: String): Flow<List<Word>> {
        return vocabularyWordDao.searchWords(query).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getWordsByCategory(category: String): Flow<List<Word>> {
        return vocabularyWordDao.getWordsByCategory(category).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getRandomWords(count: Int): List<Word> {
        return vocabularyWordDao.getRandomWords(count).map { it.toDomain() }
    }

    override suspend fun addWord(word: Word): Long {
        val entity = word.toEntity()
        val localId = vocabularyWordDao.insertWord(entity)

        // Синхронізувати з Firestore
        if (firebaseAuth.currentUser != null) {
            scope.launch {
                val wordWithLocalId = entity.copy(id = localId)

                // Отримати firestoreId папки, якщо слово має folderId
                val folderFirestoreId = wordWithLocalId.folderId?.let { folderId ->
                    vocabularyFolderDao.getFolderById(folderId)?.firestoreId
                }

                val dto = VocabularyWordDto.fromEntity(
                    wordWithLocalId,
                    firebaseAuth.currentUser!!.uid,
                    folderFirestoreId
                )
                vocabularyFirestoreDataSource.saveWord(dto).onSuccess { savedDto ->
                    // Оновити firestoreId в локальній БД
                    vocabularyWordDao.updateWord(wordWithLocalId.copy(firestoreId = savedDto.id))
                }
            }
        }

        return localId
    }

    override suspend fun updateWord(word: Word) {
        val entity = word.toEntity()
        vocabularyWordDao.updateWord(entity)

        // Синхронізувати з Firestore
        if (firebaseAuth.currentUser != null && entity.firestoreId != null) {
            scope.launch {
                // Отримати firestoreId папки, якщо слово має folderId
                val folderFirestoreId = entity.folderId?.let { folderId ->
                    vocabularyFolderDao.getFolderById(folderId)?.firestoreId
                }

                val dto = VocabularyWordDto.fromEntity(
                    entity,
                    firebaseAuth.currentUser!!.uid,
                    folderFirestoreId
                )
                vocabularyFirestoreDataSource.saveWord(dto)
            }
        }
    }

    override suspend fun deleteWord(word: Word) {
        val entity = word.toEntity()

        if (firebaseAuth.currentUser != null && entity.firestoreId != null) {
            vocabularyFirestoreDataSource.deleteWord(entity.firestoreId)
        }
        vocabularyWordDao.deleteWord(entity)
    }

    override suspend fun updatePracticeStats(wordId: Long, isCorrect: Boolean) {
        vocabularyWordDao.updatePracticeStats(
            wordId = wordId,
            incrementCorrect = if (isCorrect) 1 else 0,
            incrementIncorrect = if (isCorrect) 0 else 1,
            timestamp = Date().time
        )
    }

    override suspend fun deleteAllWords() {
        vocabularyWordDao.deleteAllWords()
    }

    override fun getWordsByFolder(folderId: Long): Flow<List<Word>> {
        return vocabularyWordDao.getWordsByFolder(folderId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getWordsWithoutFolder(): Flow<List<Word>> {
        return vocabularyWordDao.getWordsWithoutFolder().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getAllFolders(): Flow<List<Folder>> {
        return vocabularyFolderDao.getAllFoldersWithWordCount().map { foldersWithCount ->
            foldersWithCount.map { it.toDomain() }
        }
    }

    override suspend fun createFolder(name: String): Long {
        val entity = VocabularyFolderEntity(name = name)
        val localId = vocabularyFolderDao.insertFolder(entity)

        if (firebaseAuth.currentUser != null) {
            scope.launch {
                val folderWithLocalId = entity.copy(id = localId)
                val dto = VocabularyFolderDto.fromEntity(folderWithLocalId, firebaseAuth.currentUser!!.uid)
                vocabularyFirestoreDataSource.saveFolder(dto).onSuccess { savedDto ->
                    vocabularyFolderDao.insertFolder(folderWithLocalId.copy(firestoreId = savedDto.id))
                }
            }
        }

        return localId
    }

    override suspend fun deleteFolder(folderId: Long) {
        val folder = vocabularyFolderDao.getFolderById(folderId)

        if (firebaseAuth.currentUser != null && folder?.firestoreId != null) {
            val wordsInFolder = vocabularyWordDao.getWordsByFolderSync(folderId)
            wordsInFolder.forEach { word ->
                if (word.firestoreId != null) {
                    vocabularyFirestoreDataSource.deleteWord(word.firestoreId)
                }
            }

            vocabularyFirestoreDataSource.deleteFolder(folder.firestoreId!!)
        }

        vocabularyFolderDao.deleteFolder(folderId)
    }
}

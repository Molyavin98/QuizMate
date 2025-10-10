package com.molyavin.quizmate.feature.vocabulary.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFirestoreDataSource
import com.molyavin.quizmate.feature.vocabulary.data.remote.model.VocabularyFolderDto
import com.molyavin.quizmate.feature.vocabulary.data.remote.model.VocabularyWordDto
import com.molyavin.quizmate.feature.vocabulary.domain.model.VocabularyFolder
import com.molyavin.quizmate.feature.vocabulary.domain.model.Word
import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Реалізація VocabularyRepository тільки з Firestore (без кешування)
 */
@Singleton
class VocabularyRepositoryImpl @Inject constructor(
    private val firestoreDataSource: VocabularyFirestoreDataSource,
    private val firebaseAuth: FirebaseAuth
) : VocabularyRepository {

    // In-memory кеш для Flow
    private val wordsCache = MutableStateFlow<List<Word>>(emptyList())
    private val foldersCache = MutableStateFlow<List<VocabularyFolder>>(emptyList())

    override suspend fun syncFromFirestore() {
        if (firebaseAuth.currentUser == null) {
            Timber.w("User not authenticated, skipping sync")
            return
        }

        Timber.d("=== Sync started ===")

        try {
            // Завантажити папки
            val remoteFolders = firestoreDataSource.getFolders()
            Timber.d("Fetched ${remoteFolders.size} folders from Firestore")

            foldersCache.value = remoteFolders.map { it.toDomain() }

            // Завантажити слова
            val remoteWords = firestoreDataSource.getWords()
            Timber.d("Fetched ${remoteWords.size} words from Firestore")

            wordsCache.value = remoteWords.map { it.toDomain() }

            Timber.d("=== Sync completed: ${remoteFolders.size} folders, ${remoteWords.size} words ===")
        } catch (e: Exception) {
            Timber.e(e, "Fatal error during sync")
            throw e
        }
    }

    override fun getAllWords(): Flow<List<Word>> {
        return wordsCache
    }

    override suspend fun getWordById(id: String): Word? {
        return wordsCache.value.find { it.id == id }
    }

    override fun searchWords(query: String): Flow<List<Word>> {
        return wordsCache.map { words ->
            words.filter { word ->
                word.english.contains(query, ignoreCase = true) ||
                word.ukrainian.contains(query, ignoreCase = true)
            }
        }
    }

    override fun getWordsByCategory(category: String): Flow<List<Word>> {
        return wordsCache.map { words ->
            words.filter { it.category == category }
        }
    }

    override suspend fun getRandomWords(count: Int): List<Word> {
        return wordsCache.value.shuffled().take(count)
    }

    override suspend fun getRandomWordsFromFolder(folderId: String, count: Int): List<Word> {
        return wordsCache.value
            .filter { it.folderId == folderId }
            .shuffled()
            .take(count)
    }

    override suspend fun getFavoriteWords(): List<Word> {
        return wordsCache.value.filter { it.isFavorite }
    }

    override suspend fun addWord(word: Word): String {
        if (firebaseAuth.currentUser == null) {
            throw Exception("User not authenticated")
        }

        val dto = VocabularyWordDto.fromDomain(word, firebaseAuth.currentUser!!.uid)
        val result = firestoreDataSource.saveWord(dto)

        val savedDto = result.getOrElse { error ->
            Timber.e(error, "Failed to add word")
            throw error
        }

        // Оновити кеш
        val savedWord = savedDto.toDomain()
        wordsCache.value = wordsCache.value + savedWord

        return savedDto.id
    }

    override suspend fun addWordsBatch(words: List<Word>): List<String> {
        if (firebaseAuth.currentUser == null) {
            throw Exception("User not authenticated")
        }

        if (words.isEmpty()) {
            return emptyList()
        }

        val dtos = words.map { word ->
            VocabularyWordDto.fromDomain(word, firebaseAuth.currentUser!!.uid)
        }

        val result = firestoreDataSource.saveWordsBatch(dtos)

        val savedDtos = result.getOrElse { error ->
            Timber.e(error, "Failed to add words batch")
            throw error
        }

        // Оновити кеш
        val savedWords = savedDtos.map { it.toDomain() }
        wordsCache.value = wordsCache.value + savedWords

        return savedDtos.map { it.id }
    }

    override suspend fun updateWord(word: Word) {
        if (firebaseAuth.currentUser == null) {
            throw Exception("User not authenticated")
        }

        val dto = VocabularyWordDto.fromDomain(word, firebaseAuth.currentUser!!.uid)
        val savedDto = firestoreDataSource.saveWord(dto).getOrElse { error ->
            throw Exception("Failed to update word")
        }

        val savedWord = savedDto.toDomain()
        val updatedList = wordsCache.value.map {
            if (it.id == savedWord.id) savedWord else it
        }

        wordsCache.compareAndSet(wordsCache.value, updatedList)
    }

    override suspend fun deleteWord(word: Word) {
        if (firebaseAuth.currentUser == null) {
            throw Exception("User not authenticated")
        }

        if (word.id.isEmpty()) {
            throw Exception("Cannot delete word without ID")
        }

        firestoreDataSource.deleteWord(word.id).getOrElse { error ->
            Timber.e(error, "Failed to delete word")
            throw error
        }

        // Оновити кеш
        wordsCache.value = wordsCache.value.filter { it.id != word.id }
    }

    override suspend fun updatePracticeStats(wordId: String, isCorrect: Boolean) {
        val word = wordsCache.value.find { it.id == wordId } ?: return

        val updatedWord = word.copy(
            correctCount = if (isCorrect) word.correctCount + 1 else word.correctCount,
            incorrectCount = if (!isCorrect) word.incorrectCount + 1 else word.incorrectCount,
            lastPracticed = System.currentTimeMillis()
        )

        updateWord(updatedWord)
    }

    override suspend fun deleteAllWords() {
        if (firebaseAuth.currentUser == null) {
            throw Exception("User not authenticated")
        }

        wordsCache.value.forEach { word ->
            firestoreDataSource.deleteWord(word.id)
        }

        wordsCache.value = emptyList()
    }

    override fun getWordsByFolder(folderId: String): Flow<List<Word>> {
        return wordsCache.map { words ->
            words.filter { it.folderId == folderId }
        }
    }

    override fun getWordsWithoutFolder(): Flow<List<Word>> {
        return wordsCache.map { words ->
            words.filter { it.folderId == null }
        }
    }

    override fun getAllFolders(): Flow<List<VocabularyFolder>> {
        return foldersCache.map { folders ->
            folders.map { folder ->
                val wordCount = wordsCache.value.count { it.folderId == folder.id }
                folder.copy(wordCount = wordCount)
            }
            .sortedWith(compareByDescending<VocabularyFolder> { it.wordCount > 0 }.thenBy { it.name })
        }
    }

    override suspend fun createFolder(name: String): String {
        if (firebaseAuth.currentUser == null) {
            throw Exception("User not authenticated")
        }

        val folder = VocabularyFolder(
            id = "",
            name = name,
            createdAt = System.currentTimeMillis()
        )

        val dto = VocabularyFolderDto.fromDomain(folder, firebaseAuth.currentUser!!.uid)
        val result = firestoreDataSource.saveFolder(dto)

        val savedDto = result.getOrElse { error ->
            Timber.e(error, "Failed to create folder")
            throw error
        }

        // Оновити кеш
        val savedFolder = savedDto.toDomain()
        foldersCache.value = foldersCache.value + savedFolder

        return savedDto.id
    }

    override suspend fun deleteFolder(folderId: String) {
        if (firebaseAuth.currentUser == null) {
            throw Exception("User not authenticated")
        }

        // Спочатку видалити всі слова папки через batch операцію
        val wordsInFolder = wordsCache.value.filter { it.folderId == folderId }
        val wordIds = wordsInFolder.map { it.id }

        if (wordIds.isNotEmpty()) {
            firestoreDataSource.deleteWordsBatch(wordIds).getOrElse { error ->
                Timber.e(error, "Failed to delete words in folder")
                throw error
            }
        }

        // Потім видалити папку
        firestoreDataSource.deleteFolder(folderId).getOrElse { error ->
            Timber.e(error, "Failed to delete folder")
            throw error
        }

        // Оновити кеш
        wordsCache.value = wordsCache.value.filter { it.folderId != folderId }
        foldersCache.value = foldersCache.value.filter { it.id != folderId }
    }
}

// Extension functions для конвертації Dto -> Domain
private fun VocabularyWordDto.toDomain(): Word {
    return Word(
        id = this.id,
        english = this.english,
        ukrainian = this.ukrainian,
        example = this.example,
        category = this.category,
        difficulty = this.difficulty,
        imageUrl = this.imageUrl,
        folderId = this.folderId,
        isLearned = this.isLearned,
        practiceCount = this.practiceCount,
        correctCount = this.correctCount,
        incorrectCount = this.incorrectCount,
        lastPracticed = this.lastPracticed,
        createdAt = this.createdAt?.time ?: this.createdAtTimestamp,
        isFavorite = this.isFavorite
    )
}

private fun VocabularyFolderDto.toDomain(): VocabularyFolder {
    return VocabularyFolder(
        id = this.id,
        name = this.name,
        createdAt = this.createdAt?.time ?: this.createdAtTimestamp,
        wordCount = 0 // буде розраховано в getAllFolders()
    )
}

// Extension functions для конвертації Domain -> Dto
private fun VocabularyWordDto.Companion.fromDomain(word: Word, userId: String): VocabularyWordDto {
    return VocabularyWordDto(
        id = word.id,
        english = word.english,
        ukrainian = word.ukrainian,
        example = word.example,
        category = word.category,
        difficulty = word.difficulty,
        imageUrl = word.imageUrl,
        folderId = word.folderId,
        isLearned = word.isLearned,
        practiceCount = word.practiceCount,
        correctCount = word.correctCount,
        incorrectCount = word.incorrectCount,
        lastPracticed = word.lastPracticed,
        createdAtTimestamp = word.createdAt,
        userId = userId,
        isFavorite = word.isFavorite
    )
}

private fun VocabularyFolderDto.Companion.fromDomain(folder: VocabularyFolder, userId: String): VocabularyFolderDto {
    return VocabularyFolderDto(
        id = folder.id,
        name = folder.name,
        createdAtTimestamp = folder.createdAt,
        userId = userId
    )
}

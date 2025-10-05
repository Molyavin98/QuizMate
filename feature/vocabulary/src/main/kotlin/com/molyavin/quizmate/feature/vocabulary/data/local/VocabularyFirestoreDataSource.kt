package com.molyavin.quizmate.feature.vocabulary.data.local

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.molyavin.quizmate.feature.vocabulary.data.remote.model.VocabularyFolderDto
import com.molyavin.quizmate.feature.vocabulary.data.remote.model.VocabularyWordDto
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class VocabularyFirestoreDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) {

    private val userId: String?
        get() = firebaseAuth.currentUser?.uid

    /**
     * Одноразово отримати всі слова користувача з Firestore (для ручної синхронізації)
     */
    suspend fun getWords(): List<VocabularyWordDto> {
        val currentUserId = userId ?: return emptyList()

        return try {
            val snapshot = firestore.collection("words")
                .whereEqualTo("userId", currentUserId)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()

            snapshot.documents.mapNotNull { doc ->
                doc.toObject(VocabularyWordDto::class.java)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Отримати слова конкретної папки з Firestore
     */
    suspend fun getWordsByFolder(folderFirestoreId: String): List<VocabularyWordDto> {
        val currentUserId = userId ?: return emptyList()

        return try {
            val snapshot = firestore.collection("words")
                .whereEqualTo("userId", currentUserId)
                .whereEqualTo("folderId", folderFirestoreId)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()

            snapshot.documents.mapNotNull { doc ->
                doc.toObject(VocabularyWordDto::class.java)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Отримати всі слова користувача з Firestore (реал-тайм, НЕ використовується)
     */
    fun observeWords(): Flow<List<VocabularyWordDto>> = callbackFlow {
        val currentUserId = userId
        if (currentUserId == null) {
            trySend(emptyList())
            close()
            return@callbackFlow
        }

        val listener = firestore.collection("words")
            .whereEqualTo("userId", currentUserId)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    return@addSnapshotListener
                }

                val words = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(VocabularyWordDto::class.java)
                } ?: emptyList()

                trySend(words)
            }

        awaitClose { listener.remove() }
    }

    /**
     * Додати або оновити слово в Firestore
     */
    suspend fun saveWord(word: VocabularyWordDto): Result<VocabularyWordDto> {
        val currentUserId = userId ?: return Result.failure(Exception("User not authenticated"))

        return try {
            val wordWithUserId = word.copy(userId = currentUserId)

            if (word.id.isEmpty()) {
                // Створити нове слово
                val docRef = firestore.collection("words").document()
                val newWord = wordWithUserId.copy(id = docRef.id)
                docRef.set(newWord).await()
                Result.success(newWord)
            } else {
                // Оновити існуюче слово
                firestore.collection("words")
                    .document(word.id)
                    .set(wordWithUserId)
                    .await()
                Result.success(wordWithUserId)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Видалити слово з Firestore
     */
    suspend fun deleteWord(wordId: String): Result<Unit> {
        return try {
            firestore.collection("words")
                .document(wordId)
                .delete()
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ========== Folders ==========

    /**
     * Одноразово отримати всі папки користувача з Firestore (для ручної синхронізації)
     */
    suspend fun getFolders(): List<VocabularyFolderDto> {
        val currentUserId = userId ?: return emptyList()

        return try {
            val snapshot = firestore.collection("folders")
                .whereEqualTo("userId", currentUserId)
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .await()

            snapshot.documents.mapNotNull { doc ->
                doc.toObject(VocabularyFolderDto::class.java)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Отримати всі папки користувача з Firestore (реал-тайм, НЕ використовується)
     */
    fun observeFolders(): Flow<List<VocabularyFolderDto>> = callbackFlow {
        val currentUserId = userId
        if (currentUserId == null) {
            trySend(emptyList())
            close()
            return@callbackFlow
        }

        val listener = firestore.collection("folders")
            .whereEqualTo("userId", currentUserId)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    return@addSnapshotListener
                }

                val folders = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(VocabularyFolderDto::class.java)
                } ?: emptyList()

                trySend(folders)
            }

        awaitClose { listener.remove() }
    }

    /**
     * Додати або оновити папку в Firestore
     */
    suspend fun saveFolder(folder: VocabularyFolderDto): Result<VocabularyFolderDto> {
        val currentUserId = userId ?: return Result.failure(Exception("User not authenticated"))

        return try {
            val folderWithUserId = folder.copy(userId = currentUserId)

            if (folder.id.isEmpty()) {
                val docRef = firestore.collection("folders").document()
                val newFolder = folderWithUserId.copy(id = docRef.id)
                docRef.set(newFolder).await()
                Result.success(newFolder)
            } else {
                firestore.collection("folders")
                    .document(folder.id)
                    .set(folderWithUserId)
                    .await()
                Result.success(folderWithUserId)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Видалити папку з Firestore
     */
    suspend fun deleteFolder(folderId: String): Result<Unit> {
        return try {
            firestore.collection("folders")
                .document(folderId)
                .delete()
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
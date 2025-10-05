package com.molyavin.quizmate.feature.vocabulary.data.remote.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyWordEntity
import java.util.Date

/**
 * Firestore DTO для слова
 */
data class VocabularyWordDto(
    @DocumentId
    val id: String = "",
    val userId: String = "",
    val english: String = "",
    val ukrainian: String = "",
    val example: String? = null,
    val category: String? = null,
    val difficulty: String = "MEDIUM",
    val imageUrl: String? = null,
    val folderId: String? = null,
    val isFavorite: Boolean = false,
    val isLearned: Boolean = false,
    val practiceCount: Int = 0,
    val correctCount: Int = 0,
    val lastPracticed: Long? = null,
    @ServerTimestamp
    val createdAt: Date? = null,
    @ServerTimestamp
    val updatedAt: Date? = null
) {
    fun toEntity(localId: Long = 0, localFolderId: Long? = null): VocabularyWordEntity {
        return VocabularyWordEntity(
            id = localId, // Use provided local ID, or 0 for auto-increment
            english = english,
            ukrainian = ukrainian,
            example = example,
            category = category,
            difficulty = difficulty,
            imageUrl = imageUrl,
            folderId = localFolderId, // Use provided local folder ID
            isFavorite = isFavorite,
            isLearned = isLearned,
            practiceCount = practiceCount,
            correctCount = correctCount,
            lastPracticed = lastPracticed,
            createdAt = createdAt?.time ?: System.currentTimeMillis(),
            firestoreId = id
        )
    }

    companion object Companion {
        fun fromEntity(entity: VocabularyWordEntity, userId: String, folderFirestoreId: String? = null): VocabularyWordDto {
            return VocabularyWordDto(
                id = entity.firestoreId ?: "",
                userId = userId,
                english = entity.english,
                ukrainian = entity.ukrainian,
                example = entity.example,
                category = entity.category,
                difficulty = entity.difficulty,
                imageUrl = entity.imageUrl,
                folderId = folderFirestoreId, // Використовуємо firestoreId папки
                isFavorite = entity.isFavorite,
                isLearned = entity.isLearned,
                practiceCount = entity.practiceCount,
                correctCount = entity.correctCount,
                lastPracticed = entity.lastPracticed,
                createdAt = Date(entity.createdAt),
                updatedAt = Date(System.currentTimeMillis())
            )
        }
    }
}

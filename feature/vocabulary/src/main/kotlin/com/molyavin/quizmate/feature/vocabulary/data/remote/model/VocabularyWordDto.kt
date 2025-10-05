package com.molyavin.quizmate.feature.vocabulary.data.remote.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
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
    @PropertyName("favorite")
    val isFavorite: Boolean = false,
    @PropertyName("learned")
    val isLearned: Boolean = false,
    val practiceCount: Int = 0,
    val correctCount: Int = 0,
    val incorrectCount: Int = 0,
    val lastPracticed: Long? = null,
    val createdAtTimestamp: Long = System.currentTimeMillis(),
    @ServerTimestamp
    val createdAt: Date? = null,
    @ServerTimestamp
    val updatedAt: Date? = null
) {
    companion object
}

package com.molyavin.quizmate.feature.vocabulary.domain.model

import java.util.Date

data class Word(
    val id: Long = 0,
    val english: String,
    val ukrainian: String,
    val example: String? = null,
    val category: String? = null,
    val difficulty: Difficulty = Difficulty.MEDIUM,
    val imageUrl: String? = null,
    val folderId: Long? = null,
    val createdAt: Date = Date(),
    val lastPracticed: Date? = null,
    val correctCount: Int = 0,
    val incorrectCount: Int = 0,
    val isFavorite: Boolean = false,
    val isLearned: Boolean = false,
    val practiceCount: Int = 0
) {
    val successRate: Float
        get() {
            val total = correctCount + incorrectCount
            return if (total > 0) correctCount.toFloat() / total else 0f
        }
}

enum class Difficulty {
    EASY,
    MEDIUM,
    HARD
}

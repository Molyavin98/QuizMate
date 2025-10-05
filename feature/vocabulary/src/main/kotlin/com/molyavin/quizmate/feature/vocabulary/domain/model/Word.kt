package com.molyavin.quizmate.feature.vocabulary.domain.model

data class Word(
    val id: String = "",
    val english: String,
    val ukrainian: String,
    val example: String? = null,
    val category: String? = null,
    val difficulty: String = "MEDIUM",
    val imageUrl: String? = null,
    val folderId: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val lastPracticed: Long? = null,
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

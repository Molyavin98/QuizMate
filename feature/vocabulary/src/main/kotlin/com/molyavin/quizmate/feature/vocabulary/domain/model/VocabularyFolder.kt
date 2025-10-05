package com.molyavin.quizmate.feature.vocabulary.domain.model

data class VocabularyFolder(
    val id: String = "",
    val name: String,
    val createdAt: Long = System.currentTimeMillis(),
    val wordCount: Int = 0
)

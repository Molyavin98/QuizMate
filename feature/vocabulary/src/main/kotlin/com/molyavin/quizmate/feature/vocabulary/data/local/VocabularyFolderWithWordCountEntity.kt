package com.molyavin.quizmate.feature.vocabulary.data.local

data class VocabularyFolderWithWordCountEntity(
    val id: Long,
    val name: String,
    val createdAt: Long,
    val firestoreId: String?,
    val wordCount: Int
)

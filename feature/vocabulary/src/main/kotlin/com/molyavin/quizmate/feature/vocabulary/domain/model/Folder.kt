package com.molyavin.quizmate.feature.vocabulary.domain.model

import java.util.Date

data class Folder(
    val id: Long = 0,
    val name: String,
    val createdAt: Date = Date(),
    val wordCount: Int = 0
)

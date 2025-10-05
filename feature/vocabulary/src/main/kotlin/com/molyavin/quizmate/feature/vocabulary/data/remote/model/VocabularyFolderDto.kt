package com.molyavin.quizmate.feature.vocabulary.data.remote.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class VocabularyFolderDto(
    @DocumentId
    val id: String = "",
    val userId: String = "",
    val name: String = "",
    val createdAtTimestamp: Long = System.currentTimeMillis(),
    @ServerTimestamp
    val createdAt: Date? = null,
    @ServerTimestamp
    val updatedAt: Date? = null
) {
    companion object
}

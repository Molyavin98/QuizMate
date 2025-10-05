package com.molyavin.quizmate.feature.vocabulary.data.remote.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyFolderEntity
import java.util.Date


data class VocabularyFolderDto(
    @DocumentId
    val id: String = "",
    val userId: String = "",
    val name: String = "",
    @ServerTimestamp
    val createdAt: Date? = null,
    @ServerTimestamp
    val updatedAt: Date? = null
) {
    fun toEntity(localId: Long = 0): VocabularyFolderEntity {
        return VocabularyFolderEntity(
            id = localId,
            name = name,
            createdAt = createdAt?.time ?: System.currentTimeMillis(),
            firestoreId = id
        )
    }

    companion object Companion {
        fun fromEntity(entity: VocabularyFolderEntity, userId: String): VocabularyFolderDto {
            return VocabularyFolderDto(
                id = entity.firestoreId ?: "",
                userId = userId,
                name = entity.name,
                createdAt = Date(entity.createdAt),
                updatedAt = Date(System.currentTimeMillis())
            )
        }
    }
}

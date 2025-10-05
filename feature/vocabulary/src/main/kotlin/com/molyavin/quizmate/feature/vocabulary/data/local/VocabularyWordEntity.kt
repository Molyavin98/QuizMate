package com.molyavin.quizmate.feature.vocabulary.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.molyavin.quizmate.feature.vocabulary.domain.model.Difficulty
import java.util.Date

@Entity(
    tableName = "words",
    foreignKeys = [
        ForeignKey(
            entity = VocabularyFolderEntity::class,
            parentColumns = ["id"],
            childColumns = ["folderId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["folderId"]), Index(value = ["firestoreId"])]
)
data class VocabularyWordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val english: String,
    val ukrainian: String,
    val example: String? = null,
    val category: String? = null,
    val difficulty: String = Difficulty.MEDIUM.name,
    val imageUrl: String? = null,
    val folderId: Long? = null,
    val createdAt: Long = Date().time,
    val lastPracticed: Long? = null,
    val correctCount: Int = 0,
    val incorrectCount: Int = 0,
    val isFavorite: Boolean = false,
    val isLearned: Boolean = false,
    val practiceCount: Int = 0,
    val firestoreId: String? = null
)



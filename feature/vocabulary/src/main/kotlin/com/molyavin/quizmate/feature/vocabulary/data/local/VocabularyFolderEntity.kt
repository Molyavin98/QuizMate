package com.molyavin.quizmate.feature.vocabulary.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "folders",
    indices = [Index(value = ["firestoreId"])]
)
data class VocabularyFolderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val createdAt: Long = Date().time,
    val firestoreId: String? = null
)

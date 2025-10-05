package com.molyavin.quizmate.feature.vocabulary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface VocabularyFolderDao {
    @Transaction
    @Query("""
        SELECT f.*,
        (SELECT COUNT(*) FROM words WHERE folderId = f.id) as wordCount
        FROM folders f
        ORDER BY f.createdAt DESC
    """)
    fun getAllFoldersWithWordCount(): Flow<List<VocabularyFolderWithWordCountEntity>>

    @Query("SELECT * FROM folders ORDER BY createdAt DESC")
    fun getAllFolders(): Flow<List<VocabularyFolderEntity>>

    @Query("SELECT * FROM folders ORDER BY createdAt DESC")
    suspend fun getAllFoldersSync(): List<VocabularyFolderEntity>

    @Query("SELECT * FROM folders WHERE id = :folderId")
    suspend fun getFolderById(folderId: Long): VocabularyFolderEntity?

    @Query("SELECT * FROM folders WHERE firestoreId = :firestoreId LIMIT 1")
    suspend fun getFolderByFirestoreId(firestoreId: String): VocabularyFolderEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolder(folder: VocabularyFolderEntity): Long

    @Query("DELETE FROM folders WHERE id = :folderId")
    suspend fun deleteFolder(folderId: Long)

    @Query("SELECT COUNT(*) FROM words WHERE folderId = :folderId")
    suspend fun getWordCountInFolder(folderId: Long): Int
}

package com.molyavin.quizmate.feature.vocabulary.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface VocabularyWordDao {

    @Query("SELECT * FROM words ORDER BY createdAt DESC")
    fun getAllWords(): Flow<List<VocabularyWordEntity>>

    @Query("SELECT * FROM words ORDER BY createdAt DESC")
    suspend fun getAllWordsSync(): List<VocabularyWordEntity>

    @Query("SELECT * FROM words WHERE id = :id")
    suspend fun getWordById(id: Long): VocabularyWordEntity?

    @Query("SELECT * FROM words WHERE firestoreId = :firestoreId LIMIT 1")
    suspend fun getWordByFirestoreId(firestoreId: String): VocabularyWordEntity?

    @Query("""
        SELECT * FROM words
        WHERE english LIKE '%' || :query || '%'
        OR ukrainian LIKE '%' || :query || '%'
        ORDER BY createdAt DESC
    """)
    fun searchWords(query: String): Flow<List<VocabularyWordEntity>>

    @Query("SELECT * FROM words WHERE category = :category ORDER BY createdAt DESC")
    fun getWordsByCategory(category: String): Flow<List<VocabularyWordEntity>>

    @Query("SELECT * FROM words ORDER BY RANDOM() LIMIT :count")
    suspend fun getRandomWords(count: Int): List<VocabularyWordEntity>

    @Query("SELECT * FROM words WHERE folderId = :folderId ORDER BY createdAt DESC")
    fun getWordsByFolder(folderId: Long): Flow<List<VocabularyWordEntity>>

    @Query("SELECT * FROM words WHERE folderId = :folderId ORDER BY createdAt DESC")
    suspend fun getWordsByFolderSync(folderId: Long): List<VocabularyWordEntity>

    @Query("SELECT * FROM words WHERE folderId IS NULL ORDER BY createdAt DESC")
    fun getWordsWithoutFolder(): Flow<List<VocabularyWordEntity>>

    @Query("SELECT * FROM words WHERE isFavorite = 1 ORDER BY createdAt DESC")
    fun getFavoriteWords(): Flow<List<VocabularyWordEntity>>

    @Query("UPDATE words SET isFavorite = :isFavorite WHERE id = :wordId")
    suspend fun updateFavoriteStatus(wordId: Long, isFavorite: Boolean)

    @Query("DELETE FROM words")
    suspend fun deleteAllWords()

    @Query("DELETE FROM words WHERE folderId = :folderId")
    suspend fun deleteWordsByFolder(folderId: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: VocabularyWordEntity): Long

    @Update
    suspend fun updateWord(word: VocabularyWordEntity)

    @Delete
    suspend fun deleteWord(word: VocabularyWordEntity)

    @Query("""
        UPDATE words
        SET correctCount = correctCount + :incrementCorrect,
            incorrectCount = incorrectCount + :incrementIncorrect,
            lastPracticed = :timestamp
        WHERE id = :wordId
    """)
    suspend fun updatePracticeStats(
        wordId: Long,
        incrementCorrect: Int,
        incrementIncorrect: Int,
        timestamp: Long
    )
}

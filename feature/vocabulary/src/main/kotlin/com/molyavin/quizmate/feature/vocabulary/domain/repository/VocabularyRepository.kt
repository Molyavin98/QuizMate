package com.molyavin.quizmate.feature.vocabulary.domain.repository

import com.molyavin.quizmate.feature.vocabulary.domain.model.Folder
import com.molyavin.quizmate.feature.vocabulary.domain.model.Word
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface для словника
 * Dependency Inversion Principle - Domain залежить від абстракції
 */
interface VocabularyRepository {

    /**
     * Отримати всі слова
     */
    fun getAllWords(): Flow<List<Word>>

    /**
     * Отримати слово за ID
     */
    suspend fun getWordById(id: Long): Word?

    /**
     * Пошук слів
     */
    fun searchWords(query: String): Flow<List<Word>>

    /**
     * Отримати слова за категорією
     */
    fun getWordsByCategory(category: String): Flow<List<Word>>

    /**
     * Отримати випадкові слова для тесту
     */
    suspend fun getRandomWords(count: Int): List<Word>

    /**
     * Додати нове слово
     */
    suspend fun addWord(word: Word): Long

    /**
     * Оновити слово
     */
    suspend fun updateWord(word: Word)

    /**
     * Видалити слово
     */
    suspend fun deleteWord(word: Word)

    /**
     * Оновити статистику практики
     */
    suspend fun updatePracticeStats(wordId: Long, isCorrect: Boolean)

    /**
     * Видалити всі слова
     */
    suspend fun deleteAllWords()

    /**
     * Отримати слова в папці
     */
    fun getWordsByFolder(folderId: Long): Flow<List<Word>>

    /**
     * Отримати слова без папки
     */
    fun getWordsWithoutFolder(): Flow<List<Word>>

    // Folder operations
    /**
     * Отримати всі папки
     */
    fun getAllFolders(): Flow<List<Folder>>

    /**
     * Створити нову папку
     */
    suspend fun createFolder(name: String): Long

    /**
     * Видалити папку
     */
    suspend fun deleteFolder(folderId: Long)

    suspend fun syncFromFirestore()

}

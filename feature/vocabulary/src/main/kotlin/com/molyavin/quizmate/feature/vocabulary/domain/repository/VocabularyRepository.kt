package com.molyavin.quizmate.feature.vocabulary.domain.repository

import com.molyavin.quizmate.feature.vocabulary.domain.model.VocabularyFolder
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
    suspend fun getWordById(id: String): Word?

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
    suspend fun addWord(word: Word): String

    /**
     * Додати декілька слів одночасно (batch)
     */
    suspend fun addWordsBatch(words: List<Word>): List<String>

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
    suspend fun updatePracticeStats(wordId: String, isCorrect: Boolean)

    /**
     * Видалити всі слова
     */
    suspend fun deleteAllWords()

    /**
     * Отримати слова в папці
     */
    fun getWordsByFolder(folderId: String): Flow<List<Word>>

    /**
     * Отримати слова без папки
     */
    fun getWordsWithoutFolder(): Flow<List<Word>>

    // Folder operations
    /**
     * Отримати всі папки
     */
    fun getAllFolders(): Flow<List<VocabularyFolder>>

    /**
     * Створити нову папку
     */
    suspend fun createFolder(name: String): String

    /**
     * Видалити папку
     */
    suspend fun deleteFolder(folderId: String)

    suspend fun syncFromFirestore()

}

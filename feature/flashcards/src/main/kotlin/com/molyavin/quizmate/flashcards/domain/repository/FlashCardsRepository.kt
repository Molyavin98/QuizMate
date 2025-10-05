package com.molyavin.quizmate.flashcards.domain.repository

import com.molyavin.quizmate.flashcards.domain.model.FlashCard
import kotlinx.coroutines.flow.Flow

/**
 * Repository для flash cards
 */
interface FlashCardsRepository {
    /**
     * Отримати всі картки для навчання
     */
    fun getAllCards(): Flow<List<FlashCard>>

    /**
     * Отримати картки по папці
     */
    fun getCardsByFolder(folderId: Long): Flow<List<FlashCard>>

    /**
     * Отримати картки по категорії
     */
    fun getCardsByCategory(category: String): Flow<List<FlashCard>>

    /**
     * Отримати картки по рівню складності
     */
    fun getCardsByDifficulty(difficulty: String): Flow<List<FlashCard>>

    /**
     * Завантажити картки з vocabulary
     */
    suspend fun loadCardsFromVocabulary()
}

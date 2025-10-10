package com.molyavin.quizmate.flashcards.domain.usecase

import com.molyavin.quizmate.flashcards.domain.model.FlashCard
import com.molyavin.quizmate.flashcards.domain.repository.FlashCardsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case для отримання flash cards
 */
class GetFlashCardsUseCase(
    private val repository: FlashCardsRepository
) {
    operator fun invoke(folderId: String? = null): Flow<List<FlashCard>> {
        return when (folderId) {
            "favorites" -> repository.getFavoriteCards()
            null -> repository.getAllCards()
            else -> repository.getCardsByFolder(folderId)
        }
    }
}

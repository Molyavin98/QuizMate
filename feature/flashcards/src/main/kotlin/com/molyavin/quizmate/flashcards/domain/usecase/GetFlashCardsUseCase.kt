package com.molyavin.quizmate.flashcards.domain.usecase

import com.molyavin.quizmate.flashcards.domain.model.FlashCard
import com.molyavin.quizmate.flashcards.domain.repository.FlashCardsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case для отримання flash cards
 */
class GetFlashCardsUseCase @Inject constructor(
    private val repository: FlashCardsRepository
) {
    operator fun invoke(folderId: String? = null): Flow<List<FlashCard>> {
        return if (folderId != null) {
            repository.getCardsByFolder(folderId)
        } else {
            repository.getAllCards()
        }
    }
}

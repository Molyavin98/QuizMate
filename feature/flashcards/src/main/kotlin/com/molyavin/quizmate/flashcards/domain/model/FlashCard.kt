package com.molyavin.quizmate.flashcards.domain.model

import com.molyavin.quizmate.feature.vocabulary.domain.model.Difficulty

/**
 * Flash Card model
 */
data class FlashCard(
    val id: Long = 0,
    val frontText: String,
    val backText: String,
    val example: String? = null,
    val category: String? = null,
    val difficulty: Difficulty = Difficulty.MEDIUM,
    val imageUrl: String? = null,
    val isFlipped: Boolean = false
)

/**
 * Flash Card session state
 */
data class FlashCardSession(
    val cards: List<FlashCard> = emptyList(),
    val currentIndex: Int = 0,
    val knownCards: Set<Long> = emptySet(),
    val unknownCards: Set<Long> = emptySet(),
    val isFlipped: Boolean = false,
    val showEnglishFirst: Boolean = true
) {
    val currentCard: FlashCard?
        get() = cards.getOrNull(currentIndex)

    val progress: Float
        get() = if (cards.isEmpty()) 0f else (currentIndex + 1) / cards.size.toFloat()

    val totalCards: Int
        get() = cards.size

    val reviewedCards: Int
        get() = currentIndex + 1

    val knownCount: Int
        get() = knownCards.size

    val unknownCount: Int
        get() = unknownCards.size

    val isSessionComplete: Boolean
        get() = currentIndex >= cards.size
}

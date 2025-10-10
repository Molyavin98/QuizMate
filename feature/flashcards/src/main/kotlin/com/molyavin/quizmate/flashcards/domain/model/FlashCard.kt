package com.molyavin.quizmate.flashcards.domain.model

/**
 * Flash Card model
 */
data class FlashCard(
    val id: String = "",
    val frontText: String,
    val backText: String,
    val example: String? = null,
    val category: String? = null,
    val difficulty: String = "MEDIUM",
    val imageUrl: String? = null,
    val isFlipped: Boolean = false,
    val isFavorite: Boolean = false
)

/**
 * User choice for a card
 */
enum class CardChoice {
    KNOWN,
    UNKNOWN,
    NONE
}

/**
 * History entry for card review
 */
data class CardReviewHistory(
    val cardId: String,
    val choice: CardChoice
)

/**
 * Flash Card session state
 */
data class FlashCardSession(
    val cards: List<FlashCard> = emptyList(),
    val currentIndex: Int = 0,
    val knownCards: Set<String> = emptySet(),
    val unknownCards: Set<String> = emptySet(),
    val isFlipped: Boolean = false,
    val showEnglishFirst: Boolean = true,
    val reviewHistory: List<CardReviewHistory> = emptyList()
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

package com.molyavin.quizmate.flashcards.presentation

import com.molyavin.quizmate.flashcards.domain.model.FlashCardSession

/**
 * MVI Contract для FlashCards екрану
 */
object FlashCardsContract {

    data class State(
        val session: FlashCardSession = FlashCardSession(),
        val isLoading: Boolean = false,
        val error: String? = null,
        val showEnglishFirst: Boolean = true,
        val showSessionComplete: Boolean = false
    )

    sealed interface Intent {
        data object LoadCards : Intent
        data object FlipCard : Intent
        data object MarkAsKnown : Intent
        data object MarkAsUnknown : Intent
        data object NextCard : Intent
        data object PreviousCard : Intent
        data object ShuffleCards : Intent
        data object ToggleLanguageDirection : Intent
        data object RestartSession : Intent
        data object DismissSessionComplete : Intent
    }

    sealed interface Effect {
        data class ShowError(val message: String) : Effect
        data object SessionCompleted : Effect
    }
}

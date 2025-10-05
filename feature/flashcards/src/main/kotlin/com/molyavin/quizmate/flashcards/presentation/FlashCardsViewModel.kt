package com.molyavin.quizmate.flashcards.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molyavin.quizmate.flashcards.domain.model.FlashCardSession
import com.molyavin.quizmate.flashcards.domain.usecase.GetFlashCardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlashCardsViewModel @Inject constructor(
    private val getFlashCardsUseCase: GetFlashCardsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FlashCardsContract.State())
    val state: StateFlow<FlashCardsContract.State> = _state.asStateFlow()

    private val _effect = Channel<FlashCardsContract.Effect>()
    val effect = _effect.receiveAsFlow()

    private var currentFolderId: Long? = null

    fun loadCardsForFolder(folderId: Long?) {
        currentFolderId = folderId
        loadCards()
    }

    fun handleIntent(intent: FlashCardsContract.Intent) {
        when (intent) {
            is FlashCardsContract.Intent.LoadCards -> loadCards()
            is FlashCardsContract.Intent.FlipCard -> flipCard()
            is FlashCardsContract.Intent.MarkAsKnown -> markAsKnown()
            is FlashCardsContract.Intent.MarkAsUnknown -> markAsUnknown()
            is FlashCardsContract.Intent.NextCard -> nextCard()
            is FlashCardsContract.Intent.PreviousCard -> previousCard()
            is FlashCardsContract.Intent.ShuffleCards -> shuffleCards()
            is FlashCardsContract.Intent.ToggleLanguageDirection -> toggleLanguageDirection()
            is FlashCardsContract.Intent.RestartSession -> restartSession()
            is FlashCardsContract.Intent.DismissSessionComplete -> dismissSessionComplete()
        }
    }

    private fun loadCards() {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            getFlashCardsUseCase(currentFolderId)
                .catch { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                }
                .collect { cards ->
                    _state.update {
                        it.copy(
                            session = FlashCardSession(
                                cards = cards,
                                showEnglishFirst = it.showEnglishFirst
                            ),
                            isLoading = false,
                            error = null
                        )
                    }
                }
        }
    }

    private fun flipCard() {
        _state.update { state ->
            state.copy(
                session = state.session.copy(isFlipped = !state.session.isFlipped)
            )
        }
    }

    private fun markAsKnown() {
        val currentCard = _state.value.session.currentCard ?: return

        _state.update { state ->
            val updatedKnown = state.session.knownCards + currentCard.id
            state.copy(
                session = state.session.copy(
                    knownCards = updatedKnown,
                    unknownCards = state.session.unknownCards - currentCard.id
                )
            )
        }

        nextCard()
    }

    private fun markAsUnknown() {
        val currentCard = _state.value.session.currentCard ?: return

        _state.update { state ->
            val updatedUnknown = state.session.unknownCards + currentCard.id
            state.copy(
                session = state.session.copy(
                    unknownCards = updatedUnknown,
                    knownCards = state.session.knownCards - currentCard.id
                )
            )
        }

        nextCard()
    }

    private fun nextCard() {
        _state.update { state ->
            val nextIndex = state.session.currentIndex + 1
            if (nextIndex >= state.session.cards.size) {
                // Сесія завершена
                viewModelScope.launch {
                    _effect.send(FlashCardsContract.Effect.SessionCompleted)
                }
                state.copy(showSessionComplete = true)
            } else {
                state.copy(
                    session = state.session.copy(
                        currentIndex = nextIndex,
                        isFlipped = false
                    )
                )
            }
        }
    }

    private fun previousCard() {
        _state.update { state ->
            val prevIndex = (state.session.currentIndex - 1).coerceAtLeast(0)
            state.copy(
                session = state.session.copy(
                    currentIndex = prevIndex,
                    isFlipped = false
                )
            )
        }
    }

    private fun shuffleCards() {
        _state.update { state ->
            state.copy(
                session = state.session.copy(
                    cards = state.session.cards.shuffled(),
                    currentIndex = 0,
                    isFlipped = false
                )
            )
        }
    }

    private fun toggleLanguageDirection() {
        _state.update { state ->
            state.copy(
                showEnglishFirst = !state.showEnglishFirst,
                session = state.session.copy(
                    showEnglishFirst = !state.showEnglishFirst,
                    isFlipped = false
                )
            )
        }
    }

    private fun restartSession() {
        _state.update { state ->
            state.copy(
                session = state.session.copy(
                    currentIndex = 0,
                    isFlipped = false,
                    knownCards = emptySet(),
                    unknownCards = emptySet()
                ),
                showSessionComplete = false
            )
        }
    }

    private fun dismissSessionComplete() {
        _state.update { it.copy(showSessionComplete = false) }
    }
}

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
    private val getFlashCardsUseCase: GetFlashCardsUseCase,
    private val vocabularyRepository: com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FlashCardsContract.State())
    val state: StateFlow<FlashCardsContract.State> = _state.asStateFlow()

    private val _effect = Channel<FlashCardsContract.Effect>()
    val effect = _effect.receiveAsFlow()

    private var currentFolderId: String? = null

    fun loadCardsForFolder(folderId: String?) {
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
            is FlashCardsContract.Intent.ToggleFavorite -> toggleFavorite(intent.wordId)
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
            val history = state.session.reviewHistory + com.molyavin.quizmate.flashcards.domain.model.CardReviewHistory(
                cardId = currentCard.id,
                choice = com.molyavin.quizmate.flashcards.domain.model.CardChoice.KNOWN
            )
            state.copy(
                session = state.session.copy(
                    knownCards = updatedKnown,
                    unknownCards = state.session.unknownCards - currentCard.id,
                    reviewHistory = history
                )
            )
        }

        nextCard()
    }

    private fun markAsUnknown() {
        val currentCard = _state.value.session.currentCard ?: return

        _state.update { state ->
            val updatedUnknown = state.session.unknownCards + currentCard.id
            val history = state.session.reviewHistory + com.molyavin.quizmate.flashcards.domain.model.CardReviewHistory(
                cardId = currentCard.id,
                choice = com.molyavin.quizmate.flashcards.domain.model.CardChoice.UNKNOWN
            )
            state.copy(
                session = state.session.copy(
                    unknownCards = updatedUnknown,
                    knownCards = state.session.knownCards - currentCard.id,
                    reviewHistory = history
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
            if (state.session.currentIndex == 0) {
                // Якщо на першій картці, нічого не робимо
                return@update state
            }

            val prevIndex = state.session.currentIndex - 1
            val history = state.session.reviewHistory

            // Отримуємо ID попередньої картки
            val previousCardId = state.session.cards.getOrNull(prevIndex)?.id

            var updatedKnown = state.session.knownCards
            var updatedUnknown = state.session.unknownCards
            var updatedHistory = history

            // Шукаємо останній вибір для попередньої картки
            val lastReviewForPrevCard = history.findLast { it.cardId == previousCardId }

            // Якщо є історія для попередньої картки, відміняємо вибір
            if (lastReviewForPrevCard != null) {
                when (lastReviewForPrevCard.choice) {
                    com.molyavin.quizmate.flashcards.domain.model.CardChoice.KNOWN -> {
                        updatedKnown = updatedKnown - lastReviewForPrevCard.cardId
                    }
                    com.molyavin.quizmate.flashcards.domain.model.CardChoice.UNKNOWN -> {
                        updatedUnknown = updatedUnknown - lastReviewForPrevCard.cardId
                    }
                    com.molyavin.quizmate.flashcards.domain.model.CardChoice.NONE -> {}
                }
                // Видаляємо цей запис з історії
                updatedHistory = history.filter { it != lastReviewForPrevCard }
            }

            state.copy(
                session = state.session.copy(
                    currentIndex = prevIndex,
                    isFlipped = false,
                    knownCards = updatedKnown,
                    unknownCards = updatedUnknown,
                    reviewHistory = updatedHistory
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
                    isFlipped = false,
                    knownCards = emptySet(),
                    unknownCards = emptySet(),
                    reviewHistory = emptyList()
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
                    unknownCards = emptySet(),
                    reviewHistory = emptyList()
                ),
                showSessionComplete = false
            )
        }
    }

    private fun dismissSessionComplete() {
        _state.update { it.copy(showSessionComplete = false) }
    }

    private fun toggleFavorite(wordId: String) {
        val currentState = _state.value
        val currentIndex = currentState.session.currentIndex

        viewModelScope.launch {
            try {
                val word = vocabularyRepository.getWordById(wordId)
                if (word != null) {
                    _state.update { state ->
                        state.copy(
                            session = state.session.copy(
                                currentIndex = currentIndex,
                                cards = state.session.cards.map { card ->
                                    if (card.id == wordId) card.copy(isFavorite = !card.isFavorite)
                                    else card
                                },
                                knownCards = currentState.session.knownCards,
                                unknownCards = currentState.session.unknownCards
                            )
                        )
                    }

                    vocabularyRepository.updateWord(word.copy(isFavorite = !word.isFavorite))
                }
            } catch (e: Exception) {
                _effect.send(FlashCardsContract.Effect.ShowError("Failed to update favorite: ${e.message}"))
            }
        }
    }




}

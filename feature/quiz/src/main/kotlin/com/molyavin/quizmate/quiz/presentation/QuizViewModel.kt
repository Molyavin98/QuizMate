package com.molyavin.quizmate.quiz.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molyavin.quizmate.quiz.domain.GenerateQuizUseCase
import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val generateQuizUseCase: GenerateQuizUseCase,
    private val vocabularyRepository: VocabularyRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val folderId: String? = savedStateHandle.get<String>("folderId")

    private val _state = MutableStateFlow(QuizContract.State())
    val state: StateFlow<QuizContract.State> = _state.asStateFlow()

    private val _effect = Channel<QuizContract.Effect>()
    val effect = _effect.receiveAsFlow()

    init {
        handleIntent(QuizContract.Intent.StartQuiz)
    }

    fun handleIntent(intent: QuizContract.Intent) {
        when (intent) {
            is QuizContract.Intent.StartQuiz -> startQuiz()
            is QuizContract.Intent.SelectAnswer -> selectAnswer(intent.index)
            is QuizContract.Intent.CheckAnswer -> checkAnswer()
            is QuizContract.Intent.NextQuestion -> nextQuestion()
            is QuizContract.Intent.RestartQuiz -> restartQuiz()
        }
    }

    private fun startQuiz() {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = generateQuizUseCase(count = 10, folderId = folderId)

            result.onSuccess { questions ->
                _state.update {
                    it.copy(
                        questions = questions,
                        isLoading = false,
                        currentQuestionIndex = 0,
                        correctAnswers = 0,
                        isQuizFinished = false,
                        error = null
                    )
                }
            }.onFailure { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
                _effect.send(QuizContract.Effect.ShowError(error.message ?: "Failed to generate quiz"))
            }
        }
    }

    private fun selectAnswer(index: Int) {
        if (_state.value.isAnswerChecked) return

        _state.update { it.copy(selectedAnswerIndex = index) }
    }

    private fun checkAnswer() {
        val currentState = _state.value
        if (currentState.selectedAnswerIndex == null || currentState.isAnswerChecked) return

        val currentQuestion = currentState.currentQuestion ?: return
        val isCorrect = currentState.selectedAnswerIndex == currentQuestion.correctIndex

        // Update practice stats
        viewModelScope.launch {
            vocabularyRepository.updatePracticeStats(
                wordId = currentQuestion.word.id,
                isCorrect = isCorrect
            )
        }

        _state.update {
            it.copy(
                isAnswerChecked = true,
                correctAnswers = if (isCorrect) it.correctAnswers + 1 else it.correctAnswers
            )
        }
    }

    private fun nextQuestion() {
        val currentState = _state.value
        val nextIndex = currentState.currentQuestionIndex + 1

        if (nextIndex >= currentState.questions.size) {
            // Quiz finished
            _state.update { it.copy(isQuizFinished = true) }
        } else {
            _state.update {
                it.copy(
                    currentQuestionIndex = nextIndex,
                    selectedAnswerIndex = null,
                    isAnswerChecked = false
                )
            }
        }
    }

    private fun restartQuiz() {
        startQuiz()
    }
}

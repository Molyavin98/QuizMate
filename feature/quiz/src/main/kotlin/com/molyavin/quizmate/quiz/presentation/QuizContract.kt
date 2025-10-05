package com.molyavin.quizmate.quiz.presentation

import com.molyavin.quizmate.quiz.domain.QuizQuestion

/**
 * MVI Contract для Quiz екрану
 */
object QuizContract {

    data class State(
        val questions: List<QuizQuestion> = emptyList(),
        val currentQuestionIndex: Int = 0,
        val selectedAnswerIndex: Int? = null,
        val isAnswerChecked: Boolean = false,
        val correctAnswers: Int = 0,
        val isLoading: Boolean = false,
        val isQuizFinished: Boolean = false,
        val error: String? = null
    ) {
        val currentQuestion: QuizQuestion?
            get() = questions.getOrNull(currentQuestionIndex)

        val progress: Float
            get() = if (questions.isEmpty()) 0f else currentQuestionIndex.toFloat() / questions.size

        val score: Int
            get() = if (questions.isEmpty()) 0 else (correctAnswers * 100) / questions.size
    }

    sealed interface Intent {
        data object StartQuiz : Intent
        data class SelectAnswer(val index: Int) : Intent
        data object CheckAnswer : Intent
        data object NextQuestion : Intent
        data object RestartQuiz : Intent
    }

    sealed interface Effect {
        data class ShowError(val message: String) : Effect
    }
}

package com.molyavin.quizmate.quiz.domain

import com.molyavin.quizmate.feature.vocabulary.domain.model.Word

/**
 * Quiz question - multiple choice
 */
data class QuizQuestion(
    val word: Word,
    val questionType: QuestionType,
    val options: List<String>,
    val correctAnswer: String
) {
    val correctIndex: Int
        get() = options.indexOf(correctAnswer)
}

enum class QuestionType {
    TRANSLATE_TO_UKRAINIAN,  // English -> Ukrainian
    TRANSLATE_TO_ENGLISH     // Ukrainian -> English
}

package com.molyavin.quizmate.quiz.di

import com.molyavin.quizmate.quiz.domain.GenerateQuizUseCase
import com.molyavin.quizmate.quiz.presentation.QuizViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val quizModule = module {
    factoryOf(::GenerateQuizUseCase)
    viewModelOf(::QuizViewModel)
}

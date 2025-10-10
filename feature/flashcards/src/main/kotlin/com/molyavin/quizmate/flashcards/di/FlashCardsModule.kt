package com.molyavin.quizmate.flashcards.di

import com.molyavin.quizmate.flashcards.data.repository.FlashCardsRepositoryImpl
import com.molyavin.quizmate.flashcards.domain.repository.FlashCardsRepository
import com.molyavin.quizmate.flashcards.domain.usecase.GetFlashCardsUseCase
import com.molyavin.quizmate.flashcards.presentation.FlashCardsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val flashCardsModule = module {
    single<FlashCardsRepository> { FlashCardsRepositoryImpl(get()) }
    factoryOf(::GetFlashCardsUseCase)
    viewModelOf(::FlashCardsViewModel)
}

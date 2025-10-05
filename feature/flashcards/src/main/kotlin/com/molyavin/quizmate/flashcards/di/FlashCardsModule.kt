package com.molyavin.quizmate.flashcards.di

import com.molyavin.quizmate.flashcards.data.repository.FlashCardsRepositoryImpl
import com.molyavin.quizmate.flashcards.domain.repository.FlashCardsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt модуль для FlashCards feature
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class FlashCardsModule {

    @Binds
    @Singleton
    abstract fun bindFlashCardsRepository(
        impl: FlashCardsRepositoryImpl
    ): FlashCardsRepository
}

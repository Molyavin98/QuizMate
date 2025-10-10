package com.molyavin.quizmate.di

import com.google.firebase.auth.FirebaseAuth
import com.molyavin.quizmate.feature.auth.data.di.authDataModule
import com.molyavin.quizmate.feature.auth.domain.di.authDomainModule
import com.molyavin.quizmate.feature.auth.presentation.di.authPresentationModule
import com.molyavin.quizmate.feature.settings.di.settingsModule
import com.molyavin.quizmate.flashcards.di.flashCardsModule
import com.molyavin.quizmate.favorites.di.favoritesModule
import com.molyavin.quizmate.home.di.homeModule
import com.molyavin.quizmate.main.MainViewModel
import com.molyavin.quizmate.quiz.di.quizModule
import com.molyavin.quizmate.splash.di.splashModule
import com.molyavin.quizmate.feature.vocabulary.di.vocabularyModule
import com.molyavin.quizmate.core.datastore.dataStoreModule
import com.molyavin.quizmate.core.network.networkModule
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    // Core services
    single { FirebaseAuth.getInstance() }

    // MainViewModel
    viewModelOf(::MainViewModel)

    // Include all feature modules
    includes(
        authDomainModule,
        authDataModule,
        authPresentationModule,
        dataStoreModule,
        networkModule,
        vocabularyModule,
        homeModule,
        flashCardsModule,
        quizModule,
        splashModule,
        favoritesModule,
        settingsModule
    )
}

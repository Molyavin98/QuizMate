package com.molyavin.quizmate.di

import com.molyavin.quizmate.AppViewModel
import com.molyavin.quizmate.feature.auth.data.di.authDataModule
import com.molyavin.quizmate.feature.auth.domain.di.authDomainModule
import com.molyavin.quizmate.feature.auth.presentation.di.authPresentationModule
import org.koin.dsl.module

/**
 * Главный DI модуль приложения
 * Объединяет все feature модули
 */
val appModule = module {
    single { AppViewModel(get()) }

    // Подключаем все feature модули
    includes(
        authDomainModule,
        authDataModule,
        authPresentationModule
    )
}

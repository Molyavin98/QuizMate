package com.molyavin.quizmate.di

import com.molyavin.quizmate.feature.auth.data.di.authDataModule
import com.molyavin.quizmate.feature.auth.domain.di.authDomainModule
import com.molyavin.quizmate.feature.auth.presentation.di.authPresentationModule
import com.molyavin.quizmate.main.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    // MainViewModel
    viewModelOf(::MainViewModel)

    // Include all feature modules
    includes(
        authDomainModule,
        authDataModule,
        authPresentationModule
    )
}

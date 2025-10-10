package com.molyavin.quizmate.feature.auth.domain.di

import com.molyavin.quizmate.feature.auth.domain.usecase.AuthGetCurrentUserUseCase
import com.molyavin.quizmate.feature.auth.domain.usecase.AuthObserveAuthStateUseCase
import com.molyavin.quizmate.feature.auth.domain.usecase.AuthSignInWithEmailUseCase
import com.molyavin.quizmate.feature.auth.domain.usecase.AuthSignInWithGoogleUseCase
import com.molyavin.quizmate.feature.auth.domain.usecase.AuthSignOutUseCase
import com.molyavin.quizmate.feature.auth.domain.usecase.AuthSignUpWithEmailUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authDomainModule = module {
    // Use Cases
    factoryOf(::AuthGetCurrentUserUseCase)
    factoryOf(::AuthObserveAuthStateUseCase)
    factoryOf(::AuthSignInWithEmailUseCase)
    factoryOf(::AuthSignInWithGoogleUseCase)
    factoryOf(::AuthSignOutUseCase)
    factoryOf(::AuthSignUpWithEmailUseCase)
}

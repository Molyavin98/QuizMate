package com.molyavin.quizmate.feature.auth.data.di

import com.molyavin.quizmate.feature.auth.data.repository.AuthRepositoryImpl
import com.molyavin.quizmate.feature.auth.domain.repository.AuthRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual fun platformAuthDataModule(): Module = module {
    // Firebase Auth instance
    single { Firebase.auth }

    // Repository
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
}

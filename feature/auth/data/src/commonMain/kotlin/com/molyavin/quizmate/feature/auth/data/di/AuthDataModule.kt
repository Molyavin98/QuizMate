package com.molyavin.quizmate.feature.auth.data.di

import com.molyavin.quizmate.feature.auth.domain.repository.AuthRepository
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformAuthDataModule(): Module

val authDataModule = module {
    includes(platformAuthDataModule())
}

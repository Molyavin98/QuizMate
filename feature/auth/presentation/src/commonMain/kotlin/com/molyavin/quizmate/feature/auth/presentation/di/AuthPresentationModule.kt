package com.molyavin.quizmate.feature.auth.presentation.di

import com.molyavin.quizmate.feature.auth.presentation.ui.login.AuthLoginViewModel
import com.molyavin.quizmate.feature.auth.presentation.ui.register.AuthRegisterViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authPresentationModule = module {
    factoryOf(::AuthLoginViewModel)
    factoryOf(::AuthRegisterViewModel)
}

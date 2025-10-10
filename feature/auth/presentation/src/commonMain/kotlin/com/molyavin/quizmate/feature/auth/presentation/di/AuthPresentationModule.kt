package com.molyavin.quizmate.feature.auth.presentation.di

import com.molyavin.quizmate.feature.auth.presentation.ui.login.AuthLoginViewModel
import com.molyavin.quizmate.feature.auth.presentation.ui.register.AuthRegisterViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authPresentationModule = module {
    viewModelOf(::AuthLoginViewModel)
    viewModelOf(::AuthRegisterViewModel)
}

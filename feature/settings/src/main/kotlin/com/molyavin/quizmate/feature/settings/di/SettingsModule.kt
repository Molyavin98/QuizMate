package com.molyavin.quizmate.feature.settings.di

import com.molyavin.quizmate.feature.settings.data.local.SettingsDataStore
import com.molyavin.quizmate.feature.settings.data.repository.SettingsRepositoryImpl
import com.molyavin.quizmate.feature.settings.domain.repository.SettingsRepository
import com.molyavin.quizmate.feature.settings.presentation.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val settingsModule = module {
    single { SettingsDataStore(androidContext()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get(), get()) }
    viewModelOf(::SettingsViewModel)
}

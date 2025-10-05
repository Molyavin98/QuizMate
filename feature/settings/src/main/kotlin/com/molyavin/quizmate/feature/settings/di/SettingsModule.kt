package com.molyavin.quizmate.feature.settings.di

import com.google.firebase.auth.FirebaseAuth
import com.molyavin.quizmate.feature.settings.data.local.SettingsDataStore
import com.molyavin.quizmate.feature.settings.data.repository.SettingsRepositoryImpl
import com.molyavin.quizmate.feature.settings.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    @Singleton
    fun provideSettingsRepository(
        settingsDataStore: SettingsDataStore,
        firebaseAuth: FirebaseAuth
    ): SettingsRepository {
        return SettingsRepositoryImpl(settingsDataStore, firebaseAuth)
    }
}

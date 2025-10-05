package com.molyavin.quizmate.feature.settings.domain.repository

import com.molyavin.quizmate.feature.settings.domain.model.AppLanguage
import com.molyavin.quizmate.feature.settings.domain.model.AppTheme
import kotlinx.coroutines.flow.Flow

/**
 * Repository для налаштувань
 */
interface SettingsRepository {

    /**
     * Спостерігати за темою
     */
    fun observeTheme(): Flow<AppTheme>

    /**
     * Встановити тему
     */
    suspend fun setTheme(theme: AppTheme)

    /**
     * Спостерігати за мовою
     */
    fun observeLanguage(): Flow<AppLanguage>

    /**
     * Встановити мову
     */
    suspend fun setLanguage(language: AppLanguage)

    /**
     * Видалити акаунт користувача
     */
    suspend fun deleteAccount(): Result<Unit>
}

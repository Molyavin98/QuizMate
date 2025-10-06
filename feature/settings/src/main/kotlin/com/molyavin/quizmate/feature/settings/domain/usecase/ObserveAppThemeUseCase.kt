package com.molyavin.quizmate.feature.settings.domain.usecase

import com.molyavin.quizmate.feature.settings.domain.model.AppTheme
import com.molyavin.quizmate.feature.settings.domain.repository.SettingsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveAppThemeUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {

    operator fun invoke(): Flow<AppTheme> = settingsRepository.observeTheme()
}

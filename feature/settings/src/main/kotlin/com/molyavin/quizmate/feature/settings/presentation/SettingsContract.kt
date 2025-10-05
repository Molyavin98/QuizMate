package com.molyavin.quizmate.feature.settings.presentation

import com.molyavin.quizmate.feature.auth.domain.model.User
import com.molyavin.quizmate.feature.settings.domain.model.AppLanguage
import com.molyavin.quizmate.feature.settings.domain.model.AppTheme

/**
 * MVI Contract для Settings/Profile екрану
 */
object SettingsContract {

    data class State(
        val user: User? = null,
        val theme: AppTheme = AppTheme.SYSTEM,
        val language: AppLanguage = AppLanguage.UKRAINIAN,
        val isLoading: Boolean = false,
        val showDeleteDialog: Boolean = false
    )

    sealed interface Intent {
        data class SetTheme(val theme: AppTheme) : Intent
        data class SetLanguage(val language: AppLanguage) : Intent
        data object SignOut : Intent
        data object ShowDeleteDialog : Intent
        data object HideDeleteDialog : Intent
        data object DeleteAccount : Intent
    }

    sealed interface Effect {
        data object NavigateToLogin : Effect
        data class ShowError(val message: String) : Effect
        data class ShowSuccess(val message: String) : Effect
    }
}

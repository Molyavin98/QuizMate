package com.molyavin.quizmate.feature.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molyavin.quizmate.feature.auth.domain.usecase.AuthGetCurrentUserUseCase
import com.molyavin.quizmate.feature.auth.domain.usecase.AuthSignOutUseCase
import com.molyavin.quizmate.feature.settings.domain.model.AppLanguage
import com.molyavin.quizmate.feature.settings.domain.model.AppTheme
import com.molyavin.quizmate.feature.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
class SettingsViewModel(
    private val settingsRepository: SettingsRepository,
    private val authGetCurrentUserUseCase: AuthGetCurrentUserUseCase,
    private val authSignOutUseCase: AuthSignOutUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsContract.State())
    val state: StateFlow<SettingsContract.State> = _state.asStateFlow()

    private val _effect = Channel<SettingsContract.Effect>()
    val effect = _effect.receiveAsFlow()

    init {
        loadUserData()
        observeSettings()
    }

    private fun loadUserData() {
        val user = authGetCurrentUserUseCase()
        _state.update { it.copy(user = user) }
    }

    private fun observeSettings() {
        viewModelScope.launch {
            settingsRepository.observeTheme().collect { theme ->
                _state.update { it.copy(theme = theme) }
            }
        }

        viewModelScope.launch {
            settingsRepository.observeLanguage().collect { language ->
                _state.update { it.copy(language = language) }
            }
        }
    }

    fun handleIntent(intent: SettingsContract.Intent) {
        when (intent) {
            is SettingsContract.Intent.SetTheme -> setTheme(intent.theme)
            is SettingsContract.Intent.SetLanguage -> setLanguage(intent.language)
            is SettingsContract.Intent.SignOut -> signOut()
            is SettingsContract.Intent.ShowDeleteDialog -> {
                _state.update { it.copy(showDeleteDialog = true) }
            }
            is SettingsContract.Intent.HideDeleteDialog -> {
                _state.update { it.copy(showDeleteDialog = false) }
            }
            is SettingsContract.Intent.DeleteAccount -> deleteAccount()
        }
    }

    private fun setTheme(theme: AppTheme) {
        viewModelScope.launch {
            settingsRepository.setTheme(theme)
        }
    }

    private fun setLanguage(language: AppLanguage) {
        viewModelScope.launch {
            settingsRepository.setLanguage(language)
            _effect.send(SettingsContract.Effect.ShowSuccess(""))
        }
    }

    private fun signOut() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            authSignOutUseCase().onSuccess {
                _state.update { it.copy(isLoading = false) }
                _effect.send(SettingsContract.Effect.NavigateToLogin)
            }.onFailure { error ->
                _state.update { it.copy(isLoading = false) }
                _effect.send(SettingsContract.Effect.ShowError(error.message ?: "Помилка виходу"))
            }
        }
    }

    private fun deleteAccount() {
        viewModelScope.launch {
            Timber.d("ViewModel: Starting delete account process")
            _state.update { it.copy(isLoading = true, showDeleteDialog = false) }

            val result = settingsRepository.deleteAccount()
            Timber.d("ViewModel: Delete account result: ${result.isSuccess}")

            result.onSuccess {
                Timber.d("ViewModel: Account deleted successfully")
                _state.update { it.copy(isLoading = false) }
                _effect.send(SettingsContract.Effect.ShowSuccess("Акаунт видалено"))
                _effect.send(SettingsContract.Effect.NavigateToLogin)
            }.onFailure { error ->
                Timber.e(error, "ViewModel: Delete account failed")
                _state.update { it.copy(isLoading = false) }
                _effect.send(SettingsContract.Effect.ShowError(error.message ?: "Помилка видалення"))
            }
        }
    }
}

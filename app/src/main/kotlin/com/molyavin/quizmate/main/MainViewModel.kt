package com.molyavin.quizmate.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molyavin.quizmate.feature.auth.domain.model.AuthState
import com.molyavin.quizmate.feature.auth.domain.usecase.AuthObserveAuthStateUseCase
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.CreateFolderUseCase
import com.molyavin.quizmate.feature.settings.domain.model.AppTheme
import com.molyavin.quizmate.feature.settings.domain.usecase.ObserveAppThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val createFolderUseCase: CreateFolderUseCase,
    authObserveAuthStateUseCase: AuthObserveAuthStateUseCase,
    observeAppThemeUseCase: ObserveAppThemeUseCase
) : ViewModel() {

    private val _effect = Channel<MainEffect>()
    val effect = _effect.receiveAsFlow()

    private val authStateFlow = authObserveAuthStateUseCase()
    private val appThemeFlow = observeAppThemeUseCase()

    val uiState: StateFlow<MainUiState> = combine(
        authStateFlow,
        appThemeFlow,
    ) { authState, appTheme ->
        MainUiState(
            authState = authState,
            appTheme = appTheme,
            isLoading = authState is AuthState.Loading
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MainUiState()
    )

    fun createFolder(name: String) {
        viewModelScope.launch {
            try {
                createFolderUseCase(name)
                _effect.send(MainEffect.FolderCreated)
            } catch (e: Exception) {
                _effect.send(MainEffect.ShowError(e.message ?: "Failed to create folder"))
            }
        }
    }
}

sealed class MainEffect {
    data object FolderCreated : MainEffect()
    data class ShowError(val message: String) : MainEffect()
}

data class MainUiState(
    val authState: AuthState = AuthState.Loading,
    val appTheme: AppTheme = AppTheme.SYSTEM,
    val isLoading: Boolean = true
)

package com.molyavin.quizmate

import com.molyavin.quizmate.core.viewmodel.KmpViewModel
import com.molyavin.quizmate.feature.auth.domain.model.AuthState
import com.molyavin.quizmate.feature.auth.domain.usecase.AuthObserveAuthStateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Главный ViewModel приложения
 * Отслеживает состояние аутентификации
 */
class AppViewModel(
    authObserveAuthStateUseCase: AuthObserveAuthStateUseCase
) : KmpViewModel() {

    private val _authStateModel = MutableStateFlow<AuthState>(AuthState.Loading)
    val authStateModel: StateFlow<AuthState> = _authStateModel.asStateFlow()

    init {
        viewModelScope.launch {
            authObserveAuthStateUseCase().collect { state ->
                _authStateModel.value = state
            }
        }
    }
}

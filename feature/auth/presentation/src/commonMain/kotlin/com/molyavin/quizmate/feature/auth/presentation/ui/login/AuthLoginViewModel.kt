package com.molyavin.quizmate.feature.auth.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molyavin.quizmate.feature.auth.domain.model.AuthResult
import com.molyavin.quizmate.feature.auth.domain.usecase.AuthObserveAuthStateUseCase
import com.molyavin.quizmate.feature.auth.domain.usecase.AuthSignInWithEmailUseCase
import com.molyavin.quizmate.feature.auth.domain.usecase.AuthSignInWithGoogleUseCase
import com.molyavin.quizmate.feature.auth.presentation.model.AuthEffect
import com.molyavin.quizmate.feature.auth.presentation.model.AuthIntent
import com.molyavin.quizmate.feature.auth.presentation.model.AuthStateModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthLoginViewModel(
    private val authSignInWithEmailUseCase: AuthSignInWithEmailUseCase,
    private val authSignInWithGoogleUseCase: AuthSignInWithGoogleUseCase,
    authObserveAuthStateUseCase: AuthObserveAuthStateUseCase
) : ViewModel() {

    private val _authStateModel = MutableStateFlow(AuthStateModel())
    val authStateModel: StateFlow<AuthStateModel> = _authStateModel.asStateFlow()

    private val _authEffect = Channel<AuthEffect>()
    val effect = _authEffect.receiveAsFlow()

    init {
        viewModelScope.launch {
            authObserveAuthStateUseCase().collect { authState ->
                // Handle auth state changes if needed
            }
        }
    }

    fun handleIntent(authIntent: AuthIntent) {
        when (authIntent) {
            is AuthIntent.EmailChanged -> {
                _authStateModel.update { it.copy(email = authIntent.email) }
            }
            is AuthIntent.PasswordChanged -> {
                _authStateModel.update { it.copy(password = authIntent.password) }
            }
            is AuthIntent.SignInWithEmail -> {
                signInWithEmail()
            }
            is AuthIntent.SignInWithGoogle -> {
                // Google Sign-In обробляється через Activity callback
                // Це лише placeholder для UI взаємодії
            }
            is AuthIntent.NavigateToRegister -> {
                viewModelScope.launch {
                    _authEffect.send(AuthEffect.NavigateToRegister)
                }
            }
            is AuthIntent.ForgotPassword -> {
                // TODO: Implement forgot password
            }
            is AuthIntent.DismissError -> {
                _authStateModel.update { it.copy(error = null) }
            }
            else -> {}
        }
    }

    fun handleGoogleSignIn(idToken: String) {
        viewModelScope.launch {
            _authStateModel.update { it.copy(isLoading = true, error = null) }

            when (val result = authSignInWithGoogleUseCase(idToken)) {
                is AuthResult.Success -> {
                    _authStateModel.update { it.copy(isLoading = false) }
                    _authEffect.send(AuthEffect.NavigateToHome)
                }
                is AuthResult.Error -> {
                    _authStateModel.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    private fun signInWithEmail() {
        val email = _authStateModel.value.email
        val password = _authStateModel.value.password

        if (email.isBlank() || password.isBlank()) {
            _authStateModel.update { it.copy(error = "Заповніть всі поля") }
            return
        }

        viewModelScope.launch {
            _authStateModel.update { it.copy(isLoading = true, error = null) }

            when (val result = authSignInWithEmailUseCase(email, password)) {
                is AuthResult.Success -> {
                    _authStateModel.update { it.copy(isLoading = false) }
                    _authEffect.send(AuthEffect.NavigateToHome)
                }
                is AuthResult.Error -> {
                    _authStateModel.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }
}
package com.molyavin.quizmate.feature.auth.presentation.model

import com.molyavin.quizmate.feature.auth.domain.model.User

data class AuthStateModel(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val error: String? = null,
    val currentUser: User? = null
)

sealed interface AuthIntent {
    // Login Screen
    data class EmailChanged(val email: String) : AuthIntent
    data class PasswordChanged(val password: String) : AuthIntent
    data object SignInWithEmail : AuthIntent
    data object SignInWithGoogle : AuthIntent
    data object NavigateToRegister : AuthIntent
    data object ForgotPassword : AuthIntent

    // Register Screen
    data class RegisterEmailChanged(val email: String) : AuthIntent
    data class RegisterPasswordChanged(val password: String) : AuthIntent
    data class RegisterConfirmPasswordChanged(val password: String) : AuthIntent
    data object SignUpWithEmail : AuthIntent
    data object NavigateToLogin : AuthIntent

    // Common
    data object SignOut : AuthIntent
    data object DismissError : AuthIntent
}

sealed interface AuthEffect {
    data class ShowError(val message: String) : AuthEffect
    data class ShowSuccess(val message: String) : AuthEffect
    data object NavigateToHome : AuthEffect
    data object NavigateToLogin : AuthEffect
    data object NavigateToRegister : AuthEffect
}

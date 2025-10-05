package com.molyavin.quizmate.feature.auth.domain.model

sealed class AuthState {
    data class Authenticated(val user: User) : AuthState()
    data object Unauthenticated : AuthState()
    data object Loading : AuthState()
}
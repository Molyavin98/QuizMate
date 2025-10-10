package com.molyavin.quizmate.feature.auth.domain.usecase

import com.molyavin.quizmate.feature.auth.domain.model.AuthResult
import com.molyavin.quizmate.feature.auth.domain.repository.AuthRepository

class AuthSignUpWithEmailUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): AuthResult {
        return authRepository.signUpWithEmail(email, password)
    }
}
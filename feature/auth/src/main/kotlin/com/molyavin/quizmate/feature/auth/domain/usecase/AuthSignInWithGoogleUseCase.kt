package com.molyavin.quizmate.feature.auth.domain.usecase

import com.molyavin.quizmate.feature.auth.domain.model.AuthResult
import com.molyavin.quizmate.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthSignInWithGoogleUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(idToken: String): AuthResult {
        return authRepository.signInWithGoogle(idToken)
    }
}

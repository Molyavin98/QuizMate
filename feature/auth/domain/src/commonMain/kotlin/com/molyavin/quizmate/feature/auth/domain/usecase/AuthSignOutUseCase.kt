package com.molyavin.quizmate.feature.auth.domain.usecase

import com.molyavin.quizmate.feature.auth.domain.repository.AuthRepository

class AuthSignOutUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return authRepository.signOut()
    }
}

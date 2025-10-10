package com.molyavin.quizmate.feature.auth.domain.usecase

import com.molyavin.quizmate.feature.auth.domain.model.User
import com.molyavin.quizmate.feature.auth.domain.repository.AuthRepository

class AuthGetCurrentUserUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): User? {
        return authRepository.getCurrentUser()
    }
}

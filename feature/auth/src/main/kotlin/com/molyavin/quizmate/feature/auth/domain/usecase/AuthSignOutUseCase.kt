package com.molyavin.quizmate.feature.auth.domain.usecase

import com.molyavin.quizmate.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthSignOutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return authRepository.signOut()
    }
}

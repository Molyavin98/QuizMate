package com.molyavin.quizmate.feature.auth.data.repository

import com.molyavin.quizmate.feature.auth.data.remote.FirebaseAuthDataSource
import com.molyavin.quizmate.feature.auth.domain.model.AuthResult
import com.molyavin.quizmate.feature.auth.domain.model.AuthState
import com.molyavin.quizmate.feature.auth.domain.model.User
import com.molyavin.quizmate.feature.auth.domain.repository.AuthRepository
import com.molyavin.quizmate.feature.auth.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuthDataSource: FirebaseAuthDataSource
) : AuthRepository {

    override fun observeAuthState(): Flow<AuthState> {
        return firebaseAuthDataSource.observeAuthState().map { firebaseUser ->
            if (firebaseUser != null) {
                AuthState.Authenticated(firebaseUser.toDomain())
            } else {
                AuthState.Unauthenticated
            }
        }
    }

    override fun getCurrentUser(): User? {
        return firebaseAuthDataSource.getCurrentUser()?.toDomain()
    }

    override suspend fun signInWithEmail(email: String, password: String): AuthResult {
        return firebaseAuthDataSource.signInWithEmail(email, password).fold(
            onSuccess = { firebaseUser ->
                AuthResult.Success(firebaseUser.toDomain())
            },
            onFailure = { exception ->
                AuthResult.Error(exception.message ?: "Невідома помилка")
            }
        )
    }

    override suspend fun signUpWithEmail(email: String, password: String): AuthResult {
        return firebaseAuthDataSource.signUpWithEmail(email, password).fold(
            onSuccess = { firebaseUser ->
                AuthResult.Success(firebaseUser.toDomain())
            },
            onFailure = { exception ->
                AuthResult.Error(exception.message ?: "Невідома помилка")
            }
        )
    }

    override suspend fun signInWithGoogle(idToken: String): AuthResult {
        return firebaseAuthDataSource.signInWithGoogle(idToken).fold(
            onSuccess = { firebaseUser ->
                AuthResult.Success(firebaseUser.toDomain())
            },
            onFailure = { exception ->
                AuthResult.Error(exception.message ?: "Невідома помилка")
            }
        )
    }

    override suspend fun signOut(): Result<Unit> {
        return try {
            firebaseAuthDataSource.signOut()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun sendPasswordResetEmail(email: String): Result<Unit> {
        return firebaseAuthDataSource.sendPasswordResetEmail(email)
    }
}

package com.molyavin.quizmate.feature.auth.domain.repository

import com.molyavin.quizmate.feature.auth.domain.model.AuthResult
import com.molyavin.quizmate.feature.auth.domain.model.AuthState
import com.molyavin.quizmate.feature.auth.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    /**
     * Спостерігати за станом авторизації
     */
    fun observeAuthState(): Flow<AuthState>

    /**
     * Отримати поточного користувача
     */
    fun getCurrentUser(): User?

    /**
     * Вхід через Email/Password
     */
    suspend fun signInWithEmail(email: String, password: String): AuthResult

    /**
     * Реєстрація через Email/Password
     */
    suspend fun signUpWithEmail(email: String, password: String): AuthResult

    /**
     * Вхід через Google
     */
    suspend fun signInWithGoogle(idToken: String): AuthResult

    /**
     * Вихід
     */
    suspend fun signOut(): Result<Unit>

    /**
     * Відправити лист для скидання паролю
     */
    suspend fun sendPasswordResetEmail(email: String): Result<Unit>
}
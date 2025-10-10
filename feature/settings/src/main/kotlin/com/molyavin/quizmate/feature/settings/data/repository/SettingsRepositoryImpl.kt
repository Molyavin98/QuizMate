package com.molyavin.quizmate.feature.settings.data.repository

import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.molyavin.quizmate.feature.settings.data.local.SettingsDataStore
import com.molyavin.quizmate.feature.settings.domain.model.AppLanguage
import com.molyavin.quizmate.feature.settings.domain.model.AppTheme
import com.molyavin.quizmate.feature.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import timber.log.Timber

/**
 * Імплементація SettingsRepository
 */
class SettingsRepositoryImpl(
    private val settingsDataStore: SettingsDataStore,
    private val firebaseAuth: FirebaseAuth
) : SettingsRepository {

    override fun observeTheme(): Flow<AppTheme> {
        return settingsDataStore.theme
    }

    override suspend fun setTheme(theme: AppTheme) {
        settingsDataStore.setTheme(theme)
    }

    override fun observeLanguage(): Flow<AppLanguage> {
        return settingsDataStore.language
    }

    override suspend fun setLanguage(language: AppLanguage) {
        settingsDataStore.setLanguage(language)
    }

    override suspend fun deleteAccount(): Result<Unit> {
        return try {
            val user = firebaseAuth.currentUser
            if (user == null) {
                Timber.e("Delete account failed: User not authenticated")
                return Result.failure(Exception("Користувач не авторизований"))
            }

            Timber.d("Attempting to delete account for user: ${user.uid}")

            try {
                // Спробувати видалити акаунт
                user.delete().await()
                Timber.d("Account deleted successfully")
                Result.success(Unit)
            } catch (e: FirebaseAuthRecentLoginRequiredException) {
                Timber.e(e, "Recent login required for account deletion")
                // Якщо потрібна повторна автентифікація
                Result.failure(Exception("Будь ласка, вийдіть та увійдіть знову перед видаленням акаунта"))
            } catch (e: Exception) {
                Timber.e(e, "Error deleting account")
                Result.failure(e)
            }
        } catch (e: Exception) {
            Timber.e(e, "Unexpected error in deleteAccount")
            Result.failure(e)
        }
    }
}

package com.molyavin.quizmate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.molyavin.quizmate.core.viewmodel.rememberKoinViewModel
import com.molyavin.quizmate.core.viewmodel.KmpViewModel
import com.molyavin.quizmate.feature.auth.domain.model.AuthState
import com.molyavin.quizmate.feature.auth.presentation.ui.login.LoginScreen
import com.molyavin.quizmate.feature.auth.presentation.ui.register.RegisterScreen

private enum class AppDestination {
    Login,
    Register,
    Main
}

/**
 * Главная Composable функция приложения
 * Работает на Android и iOS
 */
@Composable
fun App(
    onGoogleSignInClick: () -> Unit = {},
    onActiveViewModelChanged: (KmpViewModel?) -> Unit = {},
    viewModel: AppViewModel = rememberKoinViewModel()
) {
    val authState by viewModel.authStateModel.collectAsState()
    var currentDestination by remember { mutableStateOf(AppDestination.Login) }

    LaunchedEffect(authState) {
        currentDestination = when (authState) {
            is AuthState.Authenticated -> AppDestination.Main
            is AuthState.Unauthenticated -> AppDestination.Login
            is AuthState.Loading -> AppDestination.Login
        }
    }

    LaunchedEffect(currentDestination) {
        if (currentDestination != AppDestination.Login && currentDestination != AppDestination.Register) {
            onActiveViewModelChanged(null)
        }
    }

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color(0xFF6B4EFF),
            secondary = Color(0xFF9C27B0),
            background = Color(0xFF1A1A2E),
            surface = Color(0xFF16213E),
            onPrimary = Color.White,
            onSecondary = Color.White,
            onBackground = Color.White,
            onSurface = Color.White
        )
    ) {
        when (currentDestination) {
            AppDestination.Login -> LoginScreen(
                onNavigateToRegister = { currentDestination = AppDestination.Register },
                onNavigateToHome = { currentDestination = AppDestination.Main },
                onGoogleSignInClick = onGoogleSignInClick,
                onActiveViewModelChanged = { onActiveViewModelChanged(it) }
            )

            AppDestination.Register -> RegisterScreen(
                onNavigateBack = { currentDestination = AppDestination.Login },
                onNavigateToHome = { currentDestination = AppDestination.Main },
                onGoogleSignInClick = onGoogleSignInClick,
                onActiveViewModelChanged = { onActiveViewModelChanged(it) }
            )

            AppDestination.Main -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "QuizMate Main Screen",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

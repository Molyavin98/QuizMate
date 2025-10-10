package com.molyavin.quizmate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.molyavin.quizmate.feature.auth.domain.model.AuthState
import com.molyavin.quizmate.feature.auth.presentation.ui.login.LoginScreen
import com.molyavin.quizmate.feature.auth.presentation.ui.register.RegisterScreen
import org.koin.compose.viewmodel.koinViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Главная Composable функция приложения
 * Работает на Android и iOS
 */
@Composable
fun App(
    onGoogleSignInClick: () -> Unit = {},
    viewModel: AppViewModel = koinViewModel()
) {
    val authState by viewModel.authStateModel.collectAsStateWithLifecycle()
    val navController = rememberNavController()

    // Тема приложения
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
        // Определяем стартовый маршрут на основе состояния аутентификации
        val startDestination = when (authState) {
            is AuthState.Authenticated -> "main"
            is AuthState.Unauthenticated -> "login"
            is AuthState.Loading -> "login"
        }

        // Реактивная навигация при изменении authState
        LaunchedEffect(authState) {
            when (authState) {
                is AuthState.Authenticated -> {
                    if (navController.currentDestination?.route != "main") {
                        navController.navigate("main") {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                }
                is AuthState.Unauthenticated -> {
                    if (navController.currentDestination?.route != "login") {
                        navController.navigate("login") {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                }
                is AuthState.Loading -> {}
            }
        }

        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable("login") {
                LoginScreen(
                    onNavigateToRegister = { navController.navigate("register") },
                    onNavigateToHome = {
                        navController.navigate("main") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    onGoogleSignInClick = onGoogleSignInClick
                )
            }

            composable("register") {
                RegisterScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToHome = {
                        navController.navigate("main") {
                            popUpTo("register") { inclusive = true }
                        }
                    },
                    onGoogleSignInClick = onGoogleSignInClick
                )
            }

            composable("main") {
                // Временный экран - заменим на MainScreen позже
                Box(
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
}

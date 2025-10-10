package com.molyavin.quizmate.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.molyavin.quizmate.core.theme.QuizMateTheme
import com.molyavin.quizmate.feature.auth.domain.model.AuthState
import com.molyavin.quizmate.feature.auth.presentation.ui.login.LoginScreen
import com.molyavin.quizmate.feature.auth.presentation.ui.register.RegisterScreen
import com.molyavin.quizmate.feature.settings.domain.model.AppTheme
import com.molyavin.quizmate.splash.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            var showSplash by remember { mutableStateOf(true) }

            LaunchedEffect(uiState.isLoading) {
                if (!uiState.isLoading) {
                    showSplash = false
                }
            }

            val isDarkTheme = when (uiState.appTheme) {
                AppTheme.LIGHT -> false
                AppTheme.DARK -> true
                AppTheme.SYSTEM -> isSystemInDarkTheme()
            }

            QuizMateTheme(darkTheme = isDarkTheme) {

                if (showSplash) {
                    SplashScreen(onSplashFinished = { showSplash = false })
                } else {
                    MainNavHost(authState = uiState.authState)
                }
            }
        }
    }
}

@Composable
private fun MainNavHost(authState: AuthState) {
    val navController = rememberNavController()
    val startDestination = when (authState) {
        is AuthState.Authenticated -> "main"
        is AuthState.Unauthenticated -> "login"
        is AuthState.Loading -> "login"
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
                }
            )
        }

        composable("register") {
            RegisterScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToHome = {
                    navController.navigate("main") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }

        composable("main") {
            MainScreen()
        }
    }
}

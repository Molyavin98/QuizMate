package com.molyavin.quizmate.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.molyavin.quizmate.BuildConfig
import com.molyavin.quizmate.R
import com.molyavin.quizmate.core.theme.QuizMateTheme
import com.molyavin.quizmate.feature.auth.domain.model.AuthState
import com.molyavin.quizmate.feature.auth.presentation.ui.login.AuthLoginViewModel
import com.molyavin.quizmate.feature.auth.presentation.ui.login.LoginScreen
import com.molyavin.quizmate.feature.auth.presentation.ui.register.AuthRegisterViewModel
import com.molyavin.quizmate.feature.auth.presentation.ui.register.RegisterScreen
import com.molyavin.quizmate.feature.settings.domain.model.AppTheme
import com.molyavin.quizmate.splash.SplashScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.molyavin.quizmate.feature.settings.domain.repository.SettingsRepository

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var googleSignInLauncher: ActivityResultLauncher<Intent>
    private var currentViewModel: Any? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Налаштування Google Sign-In
        setupGoogleSignIn()

        setContent {
            val viewModel: MainViewModel = hiltViewModel()
            val authState by viewModel.authStateModel.collectAsStateWithLifecycle()
            val theme by settingsRepository.observeTheme().collectAsState(initial = AppTheme.SYSTEM)
            var showSplash by remember { mutableStateOf(true) }

            val isDarkTheme = when (theme) {
                AppTheme.LIGHT -> false
                AppTheme.DARK -> true
                AppTheme.SYSTEM -> isSystemInDarkTheme()
            }

            QuizMateTheme(darkTheme = isDarkTheme) {

                if (showSplash) {
                    SplashScreen(onSplashFinished = { showSplash = false })
                } else {
                    val navController = rememberNavController()
                    val startDestination = when (authState) {
                        is AuthState.Authenticated -> "main"
                        is AuthState.Unauthenticated -> "login"
                        is AuthState.Loading -> "login" // fallback
                    }

                    NavHost(
                        navController = navController,
                        startDestination = startDestination
                    ) {
                        composable("login") {
                            val loginViewModel: AuthLoginViewModel = hiltViewModel()
                            currentViewModel = loginViewModel

                            LoginScreen(
                                onNavigateToRegister = { navController.navigate("register") },
                                onNavigateToHome = {
                                    navController.navigate("main") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                },
                                onGoogleSignInClick = { startGoogleSignIn() },
                                viewModel = loginViewModel
                            )
                        }

                        composable("register") {
                            val registerViewModel: AuthRegisterViewModel = hiltViewModel()
                            currentViewModel = registerViewModel

                            RegisterScreen(
                                onNavigateBack = { navController.popBackStack() },
                                onNavigateToHome = {
                                    navController.navigate("main") {
                                        popUpTo("register") { inclusive = true }
                                    }
                                },
                                onGoogleSignInClick = { startGoogleSignIn() },
                                viewModel = registerViewModel
                            )
                        }

                        composable("main") {
                            MainScreen()
                        }
                    }
                }
            }
        }
    }

    private fun setupGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.WEB_CLIENT_ID)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        googleSignInLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                handleGoogleSignInResult(result.data)
            }
        }
    }

    private fun startGoogleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    private fun handleGoogleSignInResult(data: Intent?) {
        try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            val idToken = account.idToken

            if (idToken != null) {
                // Передаємо idToken в ViewModel
                when (val vm = currentViewModel) {
                    is AuthLoginViewModel -> vm.handleGoogleSignIn(idToken)
                    is AuthRegisterViewModel -> vm.handleGoogleSignIn(idToken)
                }
            } else {
                Log.e("MainActivity", "ID Token is null")
            }
        } catch (e: ApiException) {
            Log.e("MainActivity", "Google Sign-In failed: ${e.statusCode}", e)
        }
    }
}
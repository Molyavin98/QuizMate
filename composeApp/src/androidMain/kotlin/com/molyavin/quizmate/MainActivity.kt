package com.molyavin.quizmate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.molyavin.quizmate.core.viewmodel.KmpViewModel
import com.molyavin.quizmate.feature.auth.presentation.ui.login.AuthLoginViewModel
import com.molyavin.quizmate.feature.auth.presentation.ui.register.AuthRegisterViewModel

class MainActivity : ComponentActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var googleSignInLauncher: ActivityResultLauncher<Intent>
    private var currentViewModel: KmpViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Настройка Google Sign-In
        setupGoogleSignIn()

        setContent {
            // Общий Compose UI для Android и iOS
            App(
                onGoogleSignInClick = { startGoogleSignIn() },
                onActiveViewModelChanged = { viewModel -> currentViewModel = viewModel }
            )
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
                // Передаем idToken в ViewModel
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

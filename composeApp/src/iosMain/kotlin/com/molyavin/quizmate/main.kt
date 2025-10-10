package com.molyavin.quizmate

import androidx.compose.ui.window.ComposeUIViewController
import com.molyavin.quizmate.di.appModule
import org.koin.core.context.startKoin
import platform.UIKit.UIViewController

/**
 * iOS Entry Point
 * Вызывается из SwiftUI: ComposeView()
 */
fun MainViewController(): UIViewController {
    // Инициализация Koin для iOS
    startKoin {
        modules(appModule)
    }

    return ComposeUIViewController {
        App(
            onGoogleSignInClick = {
                // TODO: Implement Google Sign-In for iOS
                println("Google Sign-In clicked on iOS")
            }
        )
    }
}

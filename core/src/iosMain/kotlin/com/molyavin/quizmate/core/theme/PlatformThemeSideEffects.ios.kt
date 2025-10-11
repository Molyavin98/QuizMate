package com.molyavin.quizmate.core.theme

import androidx.compose.runtime.Composable

@Composable
internal actual fun PlatformThemeSideEffects(darkTheme: Boolean) {
    // No-op on iOS
}

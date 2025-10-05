package com.molyavin.quizmate.core.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Custom colors for gradient theme
data class QuizMateColors(
    val cardBackground: Color = Color.White.copy(alpha = 0.2f),
    val cardBackgroundElevated: Color = Color.White.copy(alpha = 0.3f),
    val textOnGradient: Color = Color.White,
    val iconOnGradient: Color = Color.White,
    val iconTint: Color = Color.White.copy(alpha = 0.9f),
    val textSecondaryOnGradient: Color = Color.White.copy(alpha = 0.8f),
    val backgroundGradient: Brush = Brush.horizontalGradient(
        colors = listOf(GradientStart, GradientEnd)
    )
)

val LocalQuizMateColors = staticCompositionLocalOf { QuizMateColors() }

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    secondary = SecondaryDark,
    tertiary = TertiaryDark,
    background = Color(0xFF1A1A1A),
    surface = Color(0xFF2A2A2A)
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    secondary = Secondary,
    tertiary = Tertiary,
    background = GradientStart,
    surface = Color.White
)

@Composable
fun QuizMateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = GradientStart.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(LocalQuizMateColors provides QuizMateColors()) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

object QuizMateTheme {
    val colors: QuizMateColors
        @Composable
        get() = LocalQuizMateColors.current
}
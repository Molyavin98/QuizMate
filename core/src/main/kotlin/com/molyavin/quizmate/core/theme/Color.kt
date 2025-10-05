package com.molyavin.quizmate.core.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Gradient colors
val GradientStart = Color(0xFF40C9FF) // Light blue
val GradientEnd = Color(0xFFE81CFF)   // Pink/Purple

// Light theme colors
val Primary = GradientStart
val Secondary = Color(0xFF94D7FF)
val Tertiary = GradientEnd

// Dark theme colors
val PrimaryDark = Color(0xFF2BA0D8)
val SecondaryDark = Color(0xFF6AA8D8)
val TertiaryDark = Color(0xFFB815D8)

// Background gradient brush
val GradientBrush = Brush.verticalGradient(
    colors = listOf(GradientStart, GradientEnd)
)

val GradientBrushHorizontal = Brush.horizontalGradient(
    colors = listOf(GradientStart, GradientEnd)
)
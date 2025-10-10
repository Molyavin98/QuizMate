package com.molyavin.quizmate.core.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush

/**
 * Gradient background для всіх екранів
 */
@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    brush: Brush = GradientBrush,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = brush)
    ) {
        content()
    }
}

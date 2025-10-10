package com.molyavin.quizmate.core.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import org.koin.compose.getKoin

@Composable
inline fun <reified T : KmpViewModel> rememberKoinViewModel(): T {
    val koin = getKoin()
    val viewModel = remember { koin.get<T>() }

    DisposableEffect(viewModel) {
        onDispose { viewModel.onCleared() }
    }

    return viewModel
}

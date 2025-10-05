package com.molyavin.quizmate.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molyavin.quizmate.feature.auth.domain.model.AuthState
import com.molyavin.quizmate.feature.auth.domain.usecase.AuthObserveAuthStateUseCase
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.CreateFolderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val createFolderUseCase: CreateFolderUseCase,
    authObserveAuthStateUseCase: AuthObserveAuthStateUseCase
) : ViewModel() {

    private val _effect = Channel<MainEffect>()
    val effect = _effect.receiveAsFlow()

    private val _authStateModel = MutableStateFlow<AuthState>(AuthState.Loading)
    val authStateModel: StateFlow<AuthState> = _authStateModel.asStateFlow()

    init {
        viewModelScope.launch {
            authObserveAuthStateUseCase().collect { state ->
                _authStateModel.value = state
            }
        }
    }

    fun createFolder(name: String) {
        viewModelScope.launch {
            try {
                createFolderUseCase(name)
                _effect.send(MainEffect.FolderCreated)
            } catch (e: Exception) {
                _effect.send(MainEffect.ShowError(e.message ?: "Failed to create folder"))
            }
        }
    }
}

sealed class MainEffect {
    data object FolderCreated : MainEffect()
    data class ShowError(val message: String) : MainEffect()
}

package com.molyavin.quizmate.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molyavin.quizmate.home.model.HomeEffect
import com.molyavin.quizmate.home.model.HomeIntent
import com.molyavin.quizmate.home.model.HomeState
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.GetAllFoldersUseCase
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.VocabularySyncFromFirestoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllFoldersUseCase: GetAllFoldersUseCase,
    private val vocabularySyncFromFirestoreUseCase: VocabularySyncFromFirestoreUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _effect = Channel<HomeEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        handleIntent(HomeIntent.LoadFolders)
    }

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadFolders -> loadFolders()
        }
    }

    fun refresh() {
        viewModelScope.launch {
            try {
                vocabularySyncFromFirestoreUseCase()
            } catch (e: Exception) {
                _effect.send(HomeEffect.ShowError(e.message ?: "Sync failed"))
            }
        }
        loadFolders()
    }

    private fun loadFolders() {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            getAllFoldersUseCase()
                .catch { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                    _effect.send(HomeEffect.ShowError(error.message ?: "Unknown error"))
                }
                .collect { folders ->
                    _state.update {
                        it.copy(
                            vocabularyFolders = folders,
                            isLoading = false,
                            error = null
                        )
                    }
                }
        }
    }
}
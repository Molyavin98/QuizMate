package com.molyavin.quizmate.feature.vocabulary.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molyavin.quizmate.feature.vocabulary.domain.model.Folder
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.GetAllFoldersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FolderDetailsViewModel @Inject constructor(
    private val getAllFoldersUseCase: GetAllFoldersUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(FolderDetailsState())
    val state: StateFlow<FolderDetailsState> = _state.asStateFlow()

    init {
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
                }
                .collect { folders ->
                    _state.update {
                        it.copy(
                            folders = folders,
                            isLoading = false,
                            error = null
                        )
                    }
                }
        }
    }
}

data class FolderDetailsState(
    val folders: List<Folder> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

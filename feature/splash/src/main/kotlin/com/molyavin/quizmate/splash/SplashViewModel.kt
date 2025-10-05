package com.molyavin.quizmate.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val vocabularyRepository: VocabularyRepository
) : ViewModel() {

    private val _state = MutableStateFlow<SplashState>(SplashState.Loading)
    val state: StateFlow<SplashState> = _state.asStateFlow()

    init {
        syncData()
    }

    private fun syncData() {
        viewModelScope.launch {
            try {
                vocabularyRepository.syncFromFirestore()
                _state.value = SplashState.Success
            } catch (e: Exception) {
                _state.value = SplashState.Success
            }
        }
    }
}

sealed class SplashState {
    data object Loading : SplashState()
    data object Success : SplashState()
}

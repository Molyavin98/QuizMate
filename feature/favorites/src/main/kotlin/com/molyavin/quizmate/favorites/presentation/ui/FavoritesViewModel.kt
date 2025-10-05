package com.molyavin.quizmate.favorites.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molyavin.quizmate.feature.vocabulary.domain.model.Word
import com.molyavin.quizmate.feature.vocabulary.domain.repository.VocabularyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val vocabularyRepository: VocabularyRepository
) : ViewModel() {

    val favoriteWords: StateFlow<List<Word>> = vocabularyRepository.getAllWords()
        .map { words -> words.filter { it.isFavorite } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun toggleFavorite(wordId: String) {
        viewModelScope.launch {
            val word = favoriteWords.value.find { it.id == wordId }
            if (word != null) {
                vocabularyRepository.updateWord(word.copy(isFavorite = false))
            }
        }
    }

}

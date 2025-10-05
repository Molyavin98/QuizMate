package com.molyavin.quizmate.favorites.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyWordDao
import com.molyavin.quizmate.feature.vocabulary.domain.model.Word
import com.molyavin.quizmate.feature.vocabulary.mapper.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val vocabularyWordDao: VocabularyWordDao
) : ViewModel() {

    val favoriteWords: StateFlow<List<Word>> = vocabularyWordDao.getFavoriteWords()
        .map { entities -> entities.map { it.toDomain() } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun toggleFavorite(wordId: Long) {
        viewModelScope.launch {
            vocabularyWordDao.updateFavoriteStatus(wordId, false)
        }
    }

}

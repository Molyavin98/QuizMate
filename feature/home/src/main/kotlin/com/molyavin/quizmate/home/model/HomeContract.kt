package com.molyavin.quizmate.home.model

import com.molyavin.quizmate.feature.vocabulary.domain.model.Folder

data class HomeState(
    val folders: List<Folder> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class HomeIntent {
    data object LoadFolders : HomeIntent()
}

sealed class HomeEffect {
    data class ShowError(val message: String) : HomeEffect()
}
package com.molyavin.quizmate.feature.vocabulary.presentation

import com.molyavin.quizmate.feature.vocabulary.domain.model.Difficulty
import com.molyavin.quizmate.feature.vocabulary.domain.model.VocabularyFolder
import com.molyavin.quizmate.feature.vocabulary.domain.model.Word

/**
 * MVI Contract для Dictionary екрану
 */
object DictionaryContract {

    data class State(
        val words: List<Word> = emptyList(),
        val vocabularyFolders: List<VocabularyFolder> = emptyList(),
        val selectedFolderId: Long? = null,
        val isLoading: Boolean = false,
        val isImporting: Boolean = false,
        val searchQuery: String = "",
        val showAddDialog: Boolean = false,
        val showAddFolderDialog: Boolean = false,
        val selectedWord: Word? = null,
        val error: String? = null
    ) {
        val filteredWords: List<Word>
            get() = if (searchQuery.isBlank()) {
                words
            } else {
                words.filter {
                    it.english.contains(searchQuery, ignoreCase = true) ||
                    it.ukrainian.contains(searchQuery, ignoreCase = true)
                }
            }
    }

    sealed interface Intent {
        data object LoadWords : Intent
        data class SearchWords(val query: String) : Intent
        data object ShowAddDialog : Intent
        data object HideAddDialog : Intent
        data class AddWord(
            val english: String,
            val ukrainian: String,
            val example: String,
            val category: String,
            val difficulty: Difficulty,
            val imageUrl: String,
            val folderId: Long? = null
        ) : Intent
        data class DeleteWord(val word: Word) : Intent
        data class SelectWord(val word: Word) : Intent
        data object DismissWordDetails : Intent
        data class ImportFromJson(val jsonContent: String, val folderId: Long? = null) : Intent
        data object DeleteAllWords : Intent
        data class ToggleFavorite(val wordId: Long) : Intent

        // Folder actions
        data class SelectFolder(val folderId: Long?) : Intent
        data object ShowAddFolderDialog : Intent
        data object HideAddFolderDialog : Intent
        data class CreateFolder(val name: String) : Intent
        data class DeleteFolder(val folderId: Long) : Intent
    }

    sealed interface Effect {
        data class ShowError(val message: String) : Effect
        data class ShowSuccess(val message: String) : Effect
    }
}

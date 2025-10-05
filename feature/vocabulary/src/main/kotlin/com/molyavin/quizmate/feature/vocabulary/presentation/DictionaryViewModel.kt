package com.molyavin.quizmate.feature.vocabulary.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molyavin.quizmate.feature.vocabulary.data.local.VocabularyWordDao
import com.molyavin.quizmate.feature.vocabulary.domain.model.Word
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.AddWordUseCase
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.DeleteWordUseCase
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.GetAllWordsUseCase
import com.molyavin.quizmate.feature.vocabulary.domain.usecase.SearchWordsUseCase
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
class DictionaryViewModel @Inject constructor(
    private val getAllWordsUseCase: GetAllWordsUseCase,
    private val addWordUseCase: AddWordUseCase,
    private val deleteWordUseCase: DeleteWordUseCase,
    private val searchWordsUseCase: SearchWordsUseCase,
    private val importWordsFromJsonUseCase: com.molyavin.quizmate.feature.vocabulary.domain.usecase.ImportWordsFromJsonUseCase,
    private val deleteAllWordsUseCase: com.molyavin.quizmate.feature.vocabulary.domain.usecase.DeleteAllWordsUseCase,
    private val getAllFoldersUseCase: com.molyavin.quizmate.feature.vocabulary.domain.usecase.GetAllFoldersUseCase,
    private val createFolderUseCase: com.molyavin.quizmate.feature.vocabulary.domain.usecase.CreateFolderUseCase,
    private val deleteFolderUseCase: com.molyavin.quizmate.feature.vocabulary.domain.usecase.DeleteFolderUseCase,
    private val getWordsByFolderUseCase: com.molyavin.quizmate.feature.vocabulary.domain.usecase.GetWordsByFolderUseCase,
    private val vocabularyWordDao: VocabularyWordDao,
    private val vocabularyRepository: com.molyavin.quizmate.feature.vocabulary.data.repository.VocabularyRepositoryImpl
) : ViewModel() {

    private val _state = MutableStateFlow(DictionaryContract.State())
    val state: StateFlow<DictionaryContract.State> = _state.asStateFlow()

    private val _effect = Channel<DictionaryContract.Effect>()
    val effect = _effect.receiveAsFlow()

    init {
        loadFolders()
        handleIntent(DictionaryContract.Intent.LoadWords)
    }

    fun handleIntent(intent: DictionaryContract.Intent) {
        when (intent) {
            is DictionaryContract.Intent.LoadWords -> loadWords()
            is DictionaryContract.Intent.SearchWords -> searchWords(intent.query)
            is DictionaryContract.Intent.ShowAddDialog -> showAddDialog()
            is DictionaryContract.Intent.HideAddDialog -> hideAddDialog()
            is DictionaryContract.Intent.AddWord -> addWord(
                intent.english,
                intent.ukrainian,
                intent.example,
                intent.category,
                intent.difficulty,
                intent.imageUrl,
                intent.folderId
            )
            is DictionaryContract.Intent.DeleteWord -> deleteWord(intent.word)
            is DictionaryContract.Intent.SelectWord -> selectWord(intent.word)
            is DictionaryContract.Intent.DismissWordDetails -> dismissWordDetails()
            is DictionaryContract.Intent.ImportFromJson -> importFromJson(intent.jsonContent, intent.folderId)
            is DictionaryContract.Intent.DeleteAllWords -> deleteAllWords()
            is DictionaryContract.Intent.SelectFolder -> selectFolder(intent.folderId)
            is DictionaryContract.Intent.ShowAddFolderDialog -> showAddFolderDialog()
            is DictionaryContract.Intent.HideAddFolderDialog -> hideAddFolderDialog()
            is DictionaryContract.Intent.CreateFolder -> createFolder(intent.name)
            is DictionaryContract.Intent.DeleteFolder -> deleteFolder(intent.folderId)
            is DictionaryContract.Intent.ToggleFavorite -> toggleFavorite(intent.wordId)
        }
    }

    fun refresh() {
        viewModelScope.launch {
            try {
                // Синхронізувати тільки обрану папку (або все якщо папка не обрана)
                vocabularyRepository.syncFolderFromFirestore(_state.value.selectedFolderId)
            } catch (e: Exception) {
                _effect.send(DictionaryContract.Effect.ShowError(e.message ?: "Sync failed"))
            }
        }
        loadWords()
        loadFolders()
    }

    private fun loadWords() {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            getAllWordsUseCase()
                .catch { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                }
                .collect { words ->
                    _state.update {
                        it.copy(
                            words = words,
                            isLoading = false,
                            error = null
                        )
                    }
                }
        }
    }

    private fun searchWords(query: String) {
        _state.update { it.copy(searchQuery = query) }
    }

    private fun showAddDialog() {
        _state.update { it.copy(showAddDialog = true) }
    }

    private fun hideAddDialog() {
        _state.update { it.copy(showAddDialog = false) }
    }

    private fun addWord(
        english: String,
        ukrainian: String,
        example: String,
        category: String,
        difficulty: com.molyavin.quizmate.feature.vocabulary.domain.model.Difficulty,
        imageUrl: String,
        folderId: Long? = null
    ) {
        viewModelScope.launch {
            val word = Word(
                english = english.trim(),
                ukrainian = ukrainian.trim(),
                example = example.trim().takeIf { it.isNotBlank() },
                category = category.trim().takeIf { it.isNotBlank() },
                difficulty = difficulty,
                imageUrl = imageUrl.trim().takeIf { it.isNotBlank() },
                folderId = folderId
            )

            val result = addWordUseCase(word)

            result.onSuccess {
                _state.update { it.copy(showAddDialog = false) }
                _effect.send(DictionaryContract.Effect.ShowSuccess("Word added successfully!"))
            }.onFailure { error ->
                _effect.send(
                    DictionaryContract.Effect.ShowError(
                        error.message ?: "Failed to add word"
                    )
                )
            }
        }
    }

    private fun deleteWord(word: Word) {
        viewModelScope.launch {
            try {
                deleteWordUseCase(word)
                _effect.send(DictionaryContract.Effect.ShowSuccess("Word deleted"))
            } catch (e: Exception) {
                _effect.send(
                    DictionaryContract.Effect.ShowError(
                        e.message ?: "Failed to delete word"
                    )
                )
            }
        }
    }

    private fun selectWord(word: Word) {
        _state.update { it.copy(selectedWord = word) }
    }

    private fun dismissWordDetails() {
        _state.update { it.copy(selectedWord = null) }
    }

    private fun importFromJson(jsonContent: String, folderId: Long? = null) {
        _state.update { it.copy(isImporting = true) }

        viewModelScope.launch {
            val result = importWordsFromJsonUseCase(jsonContent, folderId)

            result.onSuccess { importResult ->
                _state.update { it.copy(isImporting = false) }

                val message = buildString {
                    append("Import completed!\n")
                    append("✓ Imported: ${importResult.importedWords}\n")
                    if (importResult.skippedWords > 0) {
                        append("⚠ Skipped: ${importResult.skippedWords}\n")
                    }
                    if (importResult.errors.isNotEmpty() && importResult.errors.size <= 5) {
                        append("\nErrors:\n")
                        importResult.errors.take(5).forEach {
                            append("• $it\n")
                        }
                    }
                }

                _effect.send(DictionaryContract.Effect.ShowSuccess(message.trim()))
            }.onFailure { error ->
                _state.update { it.copy(isImporting = false) }
                _effect.send(
                    DictionaryContract.Effect.ShowError(
                        error.message ?: "Failed to import JSON"
                    )
                )
            }
        }
    }

    private fun deleteAllWords() {
        viewModelScope.launch {
            try {
                deleteAllWordsUseCase()
                _effect.send(DictionaryContract.Effect.ShowSuccess("All words deleted"))
            } catch (e: Exception) {
                _effect.send(
                    DictionaryContract.Effect.ShowError(
                        e.message ?: "Failed to delete all words"
                    )
                )
            }
        }
    }

    private fun loadFolders() {
        viewModelScope.launch {
            getAllFoldersUseCase()
                .collect { folders ->
                    val currentSelectedId = _state.value.selectedFolderId

                    // Якщо папки завантажилися вперше і є хоча б одна папка
                    if (currentSelectedId == null && folders.isNotEmpty()) {
                        // Автоматично обрати першу папку
                        _state.update { it.copy(vocabularyFolders = folders, selectedFolderId = folders.first().id) }
                        loadWordsByFolder(folders.first().id)
                    } else {
                        _state.update { it.copy(vocabularyFolders = folders) }
                    }
                }
        }
    }

    private fun selectFolder(folderId: Long?) {
        _state.update { it.copy(selectedFolderId = folderId) }
        loadWordsByFolder(folderId)
    }

    private fun loadWordsByFolder(folderId: Long?) {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            getWordsByFolderUseCase(folderId)
                .catch { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                }
                .collect { words ->
                    _state.update {
                        it.copy(
                            words = words,
                            isLoading = false,
                            error = null
                        )
                    }
                }
        }
    }

    private fun showAddFolderDialog() {
        _state.update { it.copy(showAddFolderDialog = true) }
    }

    private fun hideAddFolderDialog() {
        _state.update { it.copy(showAddFolderDialog = false) }
    }

    private fun createFolder(name: String) {
        viewModelScope.launch {
            val hadFolders = _state.value.vocabularyFolders.isNotEmpty()
            val result = createFolderUseCase(name)

            result.onSuccess { newFolderId ->
                _state.update { it.copy(showAddFolderDialog = false) }
                _effect.send(DictionaryContract.Effect.ShowSuccess("Folder created"))

                // Якщо це перша папка, автоматично обрати її
                if (!hadFolders) {
                    selectFolder(newFolderId)
                }
            }.onFailure { error ->
                _effect.send(
                    DictionaryContract.Effect.ShowError(
                        error.message ?: "Failed to create folder"
                    )
                )
            }
        }
    }

    private fun deleteFolder(folderId: Long) {
        viewModelScope.launch {
            try {
                deleteFolderUseCase(folderId)
                _effect.send(DictionaryContract.Effect.ShowSuccess("Folder deleted"))

                // Якщо видалили обрану папку
                if (_state.value.selectedFolderId == folderId) {
                    val remainingFolders = _state.value.vocabularyFolders.filter { it.id != folderId }
                    if (remainingFolders.isNotEmpty()) {
                        // Обрати першу папку зі залишених
                        selectFolder(remainingFolders.first().id)
                    } else {
                        // Якщо папок не залишилось, показати всі слова
                        selectFolder(null)
                    }
                }
            } catch (e: Exception) {
                _effect.send(
                    DictionaryContract.Effect.ShowError(
                        e.message ?: "Failed to delete folder"
                    )
                )
            }
        }
    }

    private fun toggleFavorite(wordId: Long) {
        viewModelScope.launch {
            try {
                val currentWord = _state.value.words.find { it.id == wordId }
                if (currentWord != null) {
                    vocabularyWordDao.updateFavoriteStatus(wordId, !currentWord.isFavorite)
                }
            } catch (e: Exception) {
                _effect.send(
                    DictionaryContract.Effect.ShowError(
                        e.message ?: "Failed to toggle favorite"
                    )
                )
            }
        }
    }
}

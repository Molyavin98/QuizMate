package com.molyavin.quizmate.feature.vocabulary.ui.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.molyavin.quizmate.core.R
import com.molyavin.quizmate.core.theme.QuizMateTheme
import com.molyavin.quizmate.core.ui.GradientTopAppBar
import com.molyavin.quizmate.feature.vocabulary.domain.model.Difficulty
import com.molyavin.quizmate.feature.vocabulary.domain.model.VocabularyFolder
import com.molyavin.quizmate.feature.vocabulary.domain.model.Word
import com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryContract
import com.molyavin.quizmate.feature.vocabulary.presentation.DictionaryViewModel
import kotlinx.coroutines.delay
import java.io.BufferedReader
import java.io.InputStreamReader

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun DictionaryScreen(
    onNavigateBack: () -> Unit,
    onNavigateToFolder: ((Long) -> Unit)? = null,
    folderId: Long? = null,
    isLearningMode: Boolean = false,
    viewModel: DictionaryViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            viewModel.refresh()
            isRefreshing = false
        }
    )

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            try {
                val inputStream = context.contentResolver.openInputStream(it)
                val reader = BufferedReader(InputStreamReader(inputStream))
                val jsonContent = reader.use { it.readText() }
                viewModel.handleIntent(
                    DictionaryContract.Intent.ImportFromJson(
                        jsonContent,
                        state.selectedFolderId
                    )
                )
            } catch (e: Exception) {
                viewModel.handleIntent(
                    DictionaryContract.Intent.ImportFromJson(
                        "{}",
                        state.selectedFolderId
                    )
                )
            }
        }
    }

    // Вибрати папку якщо передано folderId
    LaunchedEffect(folderId) {
        if (folderId != null) {
            viewModel.handleIntent(DictionaryContract.Intent.SelectFolder(folderId))
        } else {
            viewModel.handleIntent(DictionaryContract.Intent.SelectFolder(null))
        }
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is DictionaryContract.Effect.ShowError -> {
                    snackbarHostState.showSnackbar(effect.message)
                }

                is DictionaryContract.Effect.ShowSuccess -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(brush =  QuizMateTheme.colors.backgroundGradient),
        containerColor = Color.Transparent,
        topBar = {
            GradientTopAppBar(
                title = { Text(stringResource(R.string.dictionary_title), color = Color.White) },
                navigationIcon = {
                    if (!isLearningMode) return@GradientTopAppBar
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            stringResource(R.string.back),
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    if (!isLearningMode) {
                        var showMenu by remember { mutableStateOf(false) }

                        IconButton(
                            onClick = { filePickerLauncher.launch("application/json") },
                            enabled = !state.isImporting
                        ) {
                            if (state.isImporting) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    strokeWidth = 2.dp,
                                    color = Color.White
                                )
                            } else {
                                Icon(
                                    Icons.Default.Upload,
                                    stringResource(R.string.dictionary_import_json),
                                    tint = Color.White
                                )
                            }
                        }

                        IconButton(onClick = { showMenu = true }) {
                            Icon(
                                Icons.Default.MoreVert,
                                stringResource(R.string.dictionary_more),
                                tint = Color.White
                            )
                        }

                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.dictionary_delete_all)) },
                                onClick = {
                                    showMenu = false
                                    viewModel.handleIntent(DictionaryContract.Intent.DeleteAllWords)
                                },
                                leadingIcon = { Icon(Icons.Default.DeleteForever, null) }
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            if (!isLearningMode) {
                FloatingActionButton(
                    onClick = { viewModel.handleIntent(DictionaryContract.Intent.ShowAddDialog) },
                    containerColor = Color.Transparent,
                    modifier = Modifier.background(
                        brush =  QuizMateTheme.colors.backgroundGradient,
                        shape = CircleShape
                    )
                ) {
                    Icon(
                        Icons.Default.Add,
                        stringResource(R.string.dictionary_add_word),
                        tint = Color.White
                    )
                }
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Search bar
            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = { viewModel.handleIntent(DictionaryContract.Intent.SearchWords(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text(stringResource(R.string.dictionary_search_hint)) },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                singleLine = true
            )

            // Folders chips (тільки в режимі бібліотеки)
            if (!isLearningMode) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.vocabularyFolders) { folder ->
                        var showDeleteDialog by remember { mutableStateOf(false) }

                        FilterChip(
                            selected = state.selectedFolderId == folder.id,
                            onClick = {
                                viewModel.handleIntent(
                                    DictionaryContract.Intent.SelectFolder(
                                        folder.id
                                    )
                                )
                            },
                            label = {
                                Text(
                                    text = "${folder.name} (${folder.wordCount})",
                                    color = if (state.selectedFolderId == folder.id) Color.Black else Color.White,
                                )
                            },
                            trailingIcon = {
                                IconButton(
                                    onClick = { showDeleteDialog = true },
                                    modifier = Modifier.size(18.dp)
                                ) {
                                    Icon(
                                        Icons.Default.Close,
                                        stringResource(R.string.folder_delete_icon_desc),
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            }
                        )

                        if (showDeleteDialog) {
                            AlertDialog(
                                onDismissRequest = { showDeleteDialog = false },
                                title = { Text(stringResource(R.string.folder_delete_confirm_title)) },
                                text = {
                                    Text(
                                        stringResource(
                                            R.string.folder_delete_confirm_message,
                                            folder.name,
                                            folder.wordCount
                                        )
                                    )
                                },
                                confirmButton = {
                                    TextButton(onClick = {
                                        viewModel.handleIntent(
                                            DictionaryContract.Intent.DeleteFolder(
                                                folder.id
                                            )
                                        )
                                        showDeleteDialog = false
                                    }) {
                                        Text(stringResource(R.string.delete))
                                    }
                                },
                                dismissButton = {
                                    TextButton(onClick = { showDeleteDialog = false }) {
                                        Text(stringResource(R.string.cancel))
                                    }
                                }
                            )
                        }
                    }

                    // Add Folder chip
                    item {
                        FilterChip(
                            selected = false,
                            onClick = {
                                viewModel.handleIntent(DictionaryContract.Intent.ShowAddFolderDialog)
                            },
                            label = {
                                Text(
                                    text = "+ Folder",
                                    color = Color.White
                                )
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            when {
                state.isLoading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                state.filteredWords.isEmpty() -> {
                    EmptyState()
                }

                else -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .pullRefresh(pullRefreshState)
                    ) {
                        LazyColumn(
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(state.filteredWords) { word ->
                                WordItem(
                                    word = word,
                                    onClick = {
                                        viewModel.handleIntent(
                                            DictionaryContract.Intent.SelectWord(
                                                word
                                            )
                                        )
                                    },
                                    onDelete = {
                                        viewModel.handleIntent(
                                            DictionaryContract.Intent.DeleteWord(
                                                word
                                            )
                                        )
                                    },
                                    onToggleFavorite = {
                                        viewModel.handleIntent(
                                            DictionaryContract.Intent.ToggleFavorite(
                                                word.id
                                            )
                                        )
                                    }
                                )
                            }
                        }

                        PullRefreshIndicator(
                            refreshing = isRefreshing,
                            state = pullRefreshState,
                            modifier = Modifier.align(Alignment.TopCenter)
                        )
                    }
                }
            }
        }

        if (state.showAddDialog) {
            AddWordDialog(
                vocabularyFolders = state.vocabularyFolders,
                selectedFolderId = state.selectedFolderId,
                onDismiss = { viewModel.handleIntent(DictionaryContract.Intent.HideAddDialog) },
                onAdd = { english, ukrainian, example, category, difficulty, imageUrl, folderId ->
                    viewModel.handleIntent(
                        DictionaryContract.Intent.AddWord(
                            english, ukrainian, example, category, difficulty, imageUrl, folderId
                        )
                    )
                }
            )
        }

        state.selectedWord?.let { word ->
            WordDetailsDialog(
                word = word,
                onDismiss = { viewModel.handleIntent(DictionaryContract.Intent.DismissWordDetails) }
            )
        }

        if (state.showAddFolderDialog) {
            AddFolderDialog(
                onDismiss = { viewModel.handleIntent(DictionaryContract.Intent.HideAddFolderDialog) },
                onCreate = { name ->
                    viewModel.handleIntent(DictionaryContract.Intent.CreateFolder(name))
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WordItem(
    word: Word,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    onToggleFavorite: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }
    var shouldDelete by remember { mutableStateOf(false) }

    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { dismissValue ->
            if (dismissValue == SwipeToDismissBoxValue.EndToStart) {
                shouldDelete = true
                true
            } else {
                false
            }
        }
    )

    LaunchedEffect(shouldDelete) {
        if (shouldDelete) {
            isVisible = false
            delay(300)
            onDelete()
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        exit = fadeOut() + shrinkVertically()
    ) {
        SwipeToDismissBox(
            state = dismissState,
            backgroundContent = {
                if (dismissState.targetValue == SwipeToDismissBoxValue.EndToStart) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Red.copy(alpha = 0.3f))
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            text = stringResource(R.string.delete),
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            },
            enableDismissFromStartToEnd = false
        ) {
            Card(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.2f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Image thumbnail
                    word.imageUrl?.takeIf { it.isNotBlank() }?.let { url ->
                        Surface(
                            modifier = Modifier
                                .size(60.dp)
                                .padding(end = 12.dp)
                                .clip(MaterialTheme.shapes.small),
                            color = Color.LightGray
                        ) {
                            AsyncImage(
                                model = url,
                                contentDescription = word.english,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = word.english,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = QuizMateTheme.colors.textOnGradient
                        )
                        Text(
                            text = word.ukrainian,
                            style = MaterialTheme.typography.bodyMedium,
                            color = QuizMateTheme.colors.textSecondaryOnGradient
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            if (word.category != null) {
                                AssistChip(
                                    onClick = {},
                                    label = {
                                        Text(
                                            word.category,
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                    }
                                )
                            }
                            if (word.isLearned) {
                                Icon(
                                    Icons.Default.CheckCircle,
                                    stringResource(R.string.dictionary_learned),
                                    tint = QuizMateTheme.colors.iconOnGradient,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                    IconButton(onClick = onToggleFavorite) {
                        Icon(
                            imageVector = if (word.isFavorite) Icons.Default.Star else Icons.Default.StarBorder,
                            contentDescription = if (word.isFavorite) {
                                stringResource(R.string.remove_from_favorites)
                            } else {
                                stringResource(R.string.add_to_favorites)
                            },
                            tint = if (word.isFavorite) Color(0xFFFFD700) else Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                Icons.Default.Book,
                null,
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(16.dp))
            Text(
                stringResource(R.string.dictionary_empty),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                stringResource(R.string.dictionary_empty_hint),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddWordDialog(
    vocabularyFolders: List<VocabularyFolder>,
    selectedFolderId: Long?,
    onDismiss: () -> Unit,
    onAdd: (String, String, String, String, Difficulty, String, Long?) -> Unit
) {
    var english by remember { mutableStateOf("") }
    var ukrainian by remember { mutableStateOf("") }
    var example by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var difficulty by remember { mutableStateOf(Difficulty.MEDIUM) }
    var folderId by remember { mutableStateOf(selectedFolderId) }
    var difficultyExpanded by remember { mutableStateOf(false) }
    var folderExpanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.add_word_title)) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = english,
                    onValueChange = { english = it },
                    label = { Text(stringResource(R.string.add_word_english)) },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = ukrainian,
                    onValueChange = { ukrainian = it },
                    label = { Text(stringResource(R.string.add_word_ukrainian)) },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = example,
                    onValueChange = { example = it },
                    label = { Text(stringResource(R.string.add_word_example)) },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text(stringResource(R.string.add_word_category)) },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = imageUrl,
                    onValueChange = { imageUrl = it },
                    label = { Text(stringResource(R.string.add_word_image_url)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                // Folder selector
                ExposedDropdownMenuBox(
                    expanded = folderExpanded,
                    onExpandedChange = { folderExpanded = it }
                ) {
                    OutlinedTextField(
                        value = folderId?.let { id -> vocabularyFolders.find { it.id == id }?.name }
                            ?: stringResource(R.string.add_word_no_folder),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.add_word_folder)) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(folderExpanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                    )
                    ExposedDropdownMenu(
                        expanded = folderExpanded,
                        onDismissRequest = { folderExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.add_word_no_folder)) },
                            onClick = {
                                folderId = null
                                folderExpanded = false
                            }
                        )
                        vocabularyFolders.forEach { folder ->
                            DropdownMenuItem(
                                text = { Text(folder.name) },
                                onClick = {
                                    folderId = folder.id
                                    folderExpanded = false
                                }
                            )
                        }
                    }
                }

                ExposedDropdownMenuBox(
                    expanded = difficultyExpanded,
                    onExpandedChange = { difficultyExpanded = it }
                ) {
                    OutlinedTextField(
                        value = when (difficulty) {
                            Difficulty.EASY -> stringResource(R.string.difficulty_easy)
                            Difficulty.MEDIUM -> stringResource(R.string.difficulty_medium)
                            Difficulty.HARD -> stringResource(R.string.difficulty_hard)
                        },
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.add_word_difficulty)) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(difficultyExpanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                    )
                    ExposedDropdownMenu(
                        expanded = difficultyExpanded,
                        onDismissRequest = { difficultyExpanded = false }
                    ) {
                        Difficulty.entries.forEach { diff ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        when (diff) {
                                            Difficulty.EASY -> stringResource(R.string.difficulty_easy)
                                            Difficulty.MEDIUM -> stringResource(R.string.difficulty_medium)
                                            Difficulty.HARD -> stringResource(R.string.difficulty_hard)
                                        }
                                    )
                                },
                                onClick = {
                                    difficulty = diff
                                    difficultyExpanded = false
                                }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (english.isNotBlank() && ukrainian.isNotBlank()) {
                        onAdd(english, ukrainian, example, category, difficulty, imageUrl, folderId)
                    }
                },
                enabled = english.isNotBlank() && ukrainian.isNotBlank()
            ) {
                Text(stringResource(R.string.add))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}

@Composable
private fun WordDetailsDialog(
    word: Word,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(word.english) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                word.imageUrl?.takeIf { it.isNotBlank() }?.let { url ->
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(MaterialTheme.shapes.medium),
                        color = Color.LightGray
                    ) {
                        AsyncImage(
                            model = url,
                            contentDescription = word.english,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                }
                Text(
                    stringResource(R.string.word_details_translation, word.ukrainian),
                    style = MaterialTheme.typography.bodyLarge
                )
                word.example?.let {
                    Text(
                        stringResource(R.string.word_details_example, it),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                word.category?.let {
                    Text(
                        stringResource(R.string.word_details_category, it),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Text(
                    stringResource(
                        R.string.word_details_difficulty, when (word.difficulty) {
                            Difficulty.EASY -> stringResource(R.string.difficulty_easy)
                            Difficulty.MEDIUM -> stringResource(R.string.difficulty_medium)
                            Difficulty.HARD -> stringResource(R.string.difficulty_hard)
                        }
                    ), style = MaterialTheme.typography.bodyMedium
                )
                HorizontalDivider(Modifier.padding(vertical = 8.dp))
                Text(stringResource(R.string.word_details_statistics), fontWeight = FontWeight.Bold)
                Text(stringResource(R.string.word_details_correct, word.correctCount))
                Text(stringResource(R.string.word_details_incorrect, word.incorrectCount))
                Text(
                    stringResource(
                        R.string.word_details_success_rate,
                        (word.successRate * 100).toInt()
                    )
                )
                if (word.isLearned) {
                    Text(
                        stringResource(R.string.word_details_learned_mark),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.close))
            }
        }
    )
}

@Composable
private fun AddFolderDialog(
    onDismiss: () -> Unit,
    onCreate: (String) -> Unit
) {
    var folderName by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.add_folder_title)) },
        text = {
            OutlinedTextField(
                value = folderName,
                onValueChange = { folderName = it },
                label = { Text(stringResource(R.string.add_folder_name)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    if (folderName.isNotBlank()) {
                        onCreate(folderName)
                    }
                },
                enabled = folderName.isNotBlank()
            ) {
                Text(stringResource(R.string.add_folder_button))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}

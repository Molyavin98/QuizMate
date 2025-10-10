package com.molyavin.quizmate.favorites.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import coil.compose.AsyncImage
import com.molyavin.quizmate.core.R
import com.molyavin.quizmate.core.theme.GradientBrushHorizontal
import com.molyavin.quizmate.core.theme.QuizMateTheme
import com.molyavin.quizmate.core.ui.GradientTopAppBar
import com.molyavin.quizmate.feature.vocabulary.domain.model.Word

@Composable
fun FavoritesScreen(
    onNavigateBack: () -> Unit,
    onNavigateToFlashCards: () -> Unit = {},
    onNavigateToQuiz: () -> Unit = {},
    viewModel: FavoritesViewModel = koinViewModel()
) {
    val favoriteWords by viewModel.favoriteWords.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = GradientBrushHorizontal),
        containerColor = Color.Transparent,
        topBar = {
            GradientTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.favorites_title),
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    if (favoriteWords.isNotEmpty()) {
                        IconButton(onClick = onNavigateToFlashCards) {
                            Icon(
                                imageVector = Icons.Default.Style,
                                contentDescription = "Flash Cards",
                                tint = Color.White
                            )
                        }
                        IconButton(onClick = onNavigateToQuiz) {
                            Icon(
                                imageVector = Icons.Default.Quiz,
                                contentDescription = "Quiz",
                                tint = Color.White
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        if (favoriteWords.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.5f),
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.favorites_empty),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = favoriteWords,
                    key = { it.id }
                ) { word ->
                    FavoriteWordCard(
                        word = word,
                        onToggleFavorite = { viewModel.toggleFavorite(word.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun FavoriteWordCard(
    word: Word,
    onToggleFavorite: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = QuizMateTheme.colors.cardBackground
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = word.english,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Text(
                        text = word.ukrainian,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.8f)
                    )

                    word.example?.let {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.6f)
                        )
                    }

                    word.category?.let { category ->
                        Spacer(modifier = Modifier.height(8.dp))
                        AssistChip(
                            onClick = {},
                            label = {
                                Text(
                                    text = category,
                                    style = MaterialTheme.typography.labelSmall
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = Color.White.copy(alpha = 0.2f),
                                labelColor = Color.White
                            )
                        )
                    }
                }

                IconButton(onClick = onToggleFavorite) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = stringResource(R.string.remove_from_favorites),
                        tint = Color(0xFFFFD700)
                    )
                }
            }

            word.imageUrl?.let { imageUrl ->
                Spacer(modifier = Modifier.height(12.dp))
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    color = Color.LightGray.copy(alpha = 0.3f)
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = word.english,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

package com.molyavin.quizmate.home.ui

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.molyavin.quizmate.core.R
import com.molyavin.quizmate.core.theme.CardSize
import com.molyavin.quizmate.core.theme.GradientBrushHorizontal
import com.molyavin.quizmate.core.theme.GridSpacing
import com.molyavin.quizmate.core.theme.IconSize
import com.molyavin.quizmate.core.theme.ListHeight
import com.molyavin.quizmate.core.theme.QuizMateTheme
import com.molyavin.quizmate.core.theme.Spacing
import com.molyavin.quizmate.core.ui.GradientTopAppBar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    onNavigateToFolder: (String) -> Unit,
    onNavigateToFavorites: () -> Unit = {},
    onNavigateToProfile: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var isRefreshing by remember { mutableStateOf(false) }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            viewModel.refresh()
            isRefreshing = false
        }
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = GradientBrushHorizontal),
        containerColor = Color.Transparent,
        topBar = {
            GradientTopAppBar(
                title = { Text(text = stringResource(R.string.app_name), color = Color.White) },
                actions = {
                    IconButton(onClick = onNavigateToProfile) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            tint = Color.White,
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .pullRefresh(pullRefreshState)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(Spacing.L)
            ) {
                Text(
                    text = stringResource(R.string.home_recent_folders),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = QuizMateTheme.colors.textOnGradient,
                    modifier = Modifier.padding(bottom = Spacing.L)
                )

                LazyHorizontalGrid(
                    rows = GridCells.Fixed(2),
                    modifier = Modifier.height(ListHeight.FolderGrid),
                    contentPadding = PaddingValues(GridSpacing.ContentPadding),
                    horizontalArrangement = Arrangement.spacedBy(GridSpacing.Horizontal),
                    verticalArrangement = Arrangement.spacedBy(GridSpacing.Vertical)
                ) {
                    items(state.vocabularyFolders) { folder ->
                        FolderCard(
                            name = folder.name,
                            wordCount = folder.wordCount,
                            onClick = { onNavigateToFolder(folder.id) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(Spacing.XXXL))

                Text(
                    text = stringResource(R.string.favorites_title),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = QuizMateTheme.colors.textOnGradient,
                    modifier = Modifier.padding(bottom = Spacing.L)
                )

                FavoritesCard(onClick = onNavigateToFavorites)
            }

            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
private fun FolderCard(
    name: String,
    wordCount: Int,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .width(CardSize.FolderCardWidth)
            .height(CardSize.FolderCardHeight),
        colors = CardDefaults.cardColors(
            containerColor = QuizMateTheme.colors.cardBackground
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Spacing.L),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Default.Folder,
                contentDescription = null,
                modifier = Modifier.size(IconSize.L),
                tint = QuizMateTheme.colors.iconOnGradient
            )

            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = QuizMateTheme.colors.textOnGradient,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(Spacing.XS))
                Text(
                    text = stringResource(R.string.words_count, wordCount),
                    style = MaterialTheme.typography.bodySmall,
                    color = QuizMateTheme.colors.textSecondaryOnGradient
                )
            }
        }
    }
}

@Composable
private fun FavoritesCard(onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = QuizMateTheme.colors.cardBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.XL),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Spacing.L)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier.size(IconSize.L),
                    tint = Color(0xFFFFD700)
                )
                Column {
                    Text(
                        text = stringResource(R.string.favorites_title),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = QuizMateTheme.colors.textOnGradient
                    )
                    Text(
                        text = stringResource(R.string.favorites_subtitle),
                        style = MaterialTheme.typography.bodyMedium,
                        color = QuizMateTheme.colors.textSecondaryOnGradient
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = QuizMateTheme.colors.iconTint
            )
        }
    }
}


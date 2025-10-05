package com.molyavin.quizmate.flashcards.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.molyavin.quizmate.core.R
import com.molyavin.quizmate.core.theme.GradientBrushHorizontal
import com.molyavin.quizmate.core.ui.GradientTopAppBar
import com.molyavin.quizmate.flashcards.domain.model.FlashCard
import com.molyavin.quizmate.flashcards.presentation.FlashCardsContract
import com.molyavin.quizmate.flashcards.presentation.FlashCardsViewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashCardsScreen(
    onNavigateBack: () -> Unit,
    folderId: Long? = null,
    viewModel: FlashCardsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    // Завантажити картки для конкретної папки
    LaunchedEffect(folderId) {
        viewModel.loadCardsForFolder(folderId)
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is FlashCardsContract.Effect.ShowError -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
                is FlashCardsContract.Effect.SessionCompleted -> {
                    // Handled by showSessionComplete state
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = GradientBrushHorizontal),
        containerColor = Color.Transparent,
        topBar = {
            GradientTopAppBar(
                title = { Text(stringResource(R.string.flashcards_title), color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.back), tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.handleIntent(FlashCardsContract.Intent.ShuffleCards) }) {
                        Icon(Icons.Default.Shuffle, stringResource(R.string.flashcards_shuffle), tint = Color.White)
                    }
                    IconButton(onClick = { viewModel.handleIntent(FlashCardsContract.Intent.ToggleLanguageDirection) }) {
                        Icon(Icons.Default.SwapHoriz, stringResource(R.string.flashcards_toggle_language), tint = Color.White)
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                state.isLoading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                state.session.cards.isEmpty() -> {
                    EmptyStateMessage()
                }
                state.showSessionComplete -> {
                    SessionCompleteDialog(
                        knownCount = state.session.knownCount,
                        unknownCount = state.session.unknownCount,
                        totalCount = state.session.totalCards,
                        onRestart = { viewModel.handleIntent(FlashCardsContract.Intent.RestartSession) },
                        onDismiss = onNavigateBack
                    )
                }
                else -> {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Progress bar
                        ProgressIndicator(
                            current = state.session.reviewedCards,
                            total = state.session.totalCards,
                            known = state.session.knownCount,
                            unknown = state.session.unknownCount
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Flash card
                        state.session.currentCard?.let { card ->
                            FlashCardView(
                                card = card,
                                isFlipped = state.session.isFlipped,
                                showEnglishFirst = state.showEnglishFirst,
                                onFlip = { viewModel.handleIntent(FlashCardsContract.Intent.FlipCard) },
                                onSwipeLeft = { viewModel.handleIntent(FlashCardsContract.Intent.MarkAsUnknown) },
                                onSwipeRight = { viewModel.handleIntent(FlashCardsContract.Intent.MarkAsKnown) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(horizontal = 24.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Navigation buttons
                        NavigationButtons(
                            canGoPrevious = state.session.currentIndex > 0,
                            onPrevious = { viewModel.handleIntent(FlashCardsContract.Intent.PreviousCard) },
                            onMarkUnknown = { viewModel.handleIntent(FlashCardsContract.Intent.MarkAsUnknown) },
                            onMarkKnown = { viewModel.handleIntent(FlashCardsContract.Intent.MarkAsKnown) }
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun ProgressIndicator(
    current: Int,
    total: Int,
    known: Int,
    unknown: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$current / $total",
                style = MaterialTheme.typography.bodyMedium
            )
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = Color.Green,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(text = "$known", style = MaterialTheme.typography.bodyMedium)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Icon(
                        Icons.Default.Cancel,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(text = "$unknown", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { if (total > 0) current / total.toFloat() else 0f },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
        )
    }
}

@Composable
private fun FlashCardView(
    card: FlashCard,
    isFlipped: Boolean,
    showEnglishFirst: Boolean,
    onFlip: () -> Unit,
    onSwipeLeft: () -> Unit,
    onSwipeRight: () -> Unit,
    modifier: Modifier = Modifier
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }

    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 400),
        label = "Flip animation"
    )

    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        when {
                            offsetX < -200 -> onSwipeLeft()
                            offsetX > 200 -> onSwipeRight()
                            else -> {
                                offsetX = 0f
                                offsetY = 0f
                            }
                        }
                        offsetX = 0f
                        offsetY = 0f
                    },
                    onDragCancel = {
                        offsetX = 0f
                        offsetY = 0f
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                )
            }
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) },
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 12f * density
                },
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.3f)
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .graphicsLayer {
                        rotationY = if (rotation > 90f) 180f else 0f
                    },
                contentAlignment = Alignment.Center
            ) {
                if (rotation < 90f) {
                    // Front side
                    FrontSide(
                        text = if (showEnglishFirst) card.frontText else card.backText,
                        imageUrl = if (showEnglishFirst) card.imageUrl else null,
                        onClick = onFlip
                    )
                } else {
                    // Back side
                    BackSide(
                        text = if (showEnglishFirst) card.backText else card.frontText,
                        example = card.example,
                        category = card.category,
                        onClick = onFlip
                    )
                }
            }
        }

        // Swipe indicators
        if (offsetX < -50) {
            SwipeIndicator(
                text = stringResource(R.string.flashcards_swipe_dont_know),
                color = Color.Red,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        } else if (offsetX > 50) {
            SwipeIndicator(
                text = stringResource(R.string.flashcards_swipe_know),
                color = Color.Green,
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }
    }
}

@Composable
private fun FrontSide(
    text: String,
    imageUrl: String?,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        imageUrl?.let { url ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(MaterialTheme.shapes.medium),
                color = Color.LightGray
            ) {
                AsyncImage(
                    model = url,
                    contentDescription = text,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        Text(
            text = text,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.flashcards_flip_hint),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f)
        )
    }
}

@Composable
private fun BackSide(
    text: String,
    example: String?,
    category: String?,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )

        example?.let {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = it,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }

        category?.let {
            Spacer(modifier = Modifier.height(16.dp))
            AssistChip(
                onClick = {},
                label = { Text(it) }
            )
        }
    }
}

@Composable
private fun SwipeIndicator(
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.8f))
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun NavigationButtons(
    canGoPrevious: Boolean,
    onPrevious: () -> Unit,
    onMarkUnknown: () -> Unit,
    onMarkKnown: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onPrevious,
            enabled = canGoPrevious
        ) {
            Icon(Icons.Default.ArrowBack, stringResource(R.string.flashcards_previous))
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = onMarkUnknown,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            ) {
                Icon(Icons.Default.Close, contentDescription = null)
                Spacer(Modifier.width(4.dp))
                Text(stringResource(R.string.flashcards_swipe_dont_know))
            }

            Button(
                onClick = onMarkKnown,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.White
                )
            ) {
                Icon(Icons.Default.Check, contentDescription = null)
                Spacer(Modifier.width(4.dp))
                Text(stringResource(R.string.flashcards_swipe_know))
            }
        }

        Spacer(Modifier.width(48.dp))
    }
}

@Composable
private fun SessionCompleteDialog(
    knownCount: Int,
    unknownCount: Int,
    totalCount: Int,
    onRestart: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = { Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color.Green) },
        title = { Text(stringResource(R.string.flashcards_session_complete_title)) },
        text = {
            Column {
                Text(stringResource(R.string.flashcards_session_complete_total, totalCount))
                Text(stringResource(R.string.flashcards_session_complete_known, knownCount), color = Color.Green)
                Text(stringResource(R.string.flashcards_session_complete_unknown, unknownCount), color = Color.Red)
                if (unknownCount > 0) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        stringResource(R.string.flashcards_session_complete_hint),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = onRestart) {
                Text(stringResource(R.string.flashcards_session_restart))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.close))
            }
        }
    )
}

@Composable
private fun EmptyStateMessage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                Icons.Default.Style,
                null,
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(16.dp))
            Text(stringResource(R.string.flashcards_empty), style = MaterialTheme.typography.titleLarge)
            Text(
                stringResource(R.string.flashcards_empty_hint),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

package com.molyavin.quizmate.quiz.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.molyavin.quizmate.core.R
import com.molyavin.quizmate.core.theme.GradientBrushHorizontal
import com.molyavin.quizmate.core.ui.GradientTopAppBar
import com.molyavin.quizmate.quiz.domain.QuestionType
import com.molyavin.quizmate.quiz.presentation.QuizContract
import com.molyavin.quizmate.quiz.presentation.QuizViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    onNavigateBack: () -> Unit,
    viewModel: QuizViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is QuizContract.Effect.ShowError -> {
                    snackbarHostState.showSnackbar(effect.message)
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
                title = { Text(stringResource(R.string.quiz_title), color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.back), tint = Color.White)
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
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                state.error != null -> {
                    ErrorState(
                        error = state.error!!,
                        onRetry = { viewModel.handleIntent(QuizContract.Intent.StartQuiz) },
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                state.isQuizFinished -> {
                    QuizResults(
                        correctAnswers = state.correctAnswers,
                        totalQuestions = state.questions.size,
                        score = state.score,
                        onRestart = { viewModel.handleIntent(QuizContract.Intent.RestartQuiz) },
                        onExit = onNavigateBack
                    )
                }
                else -> {
                    QuizContent(
                        state = state,
                        onIntent = viewModel::handleIntent
                    )
                }
            }
        }
    }
}

@Composable
private fun QuizContent(
    state: QuizContract.State,
    onIntent: (QuizContract.Intent) -> Unit
) {
    val question = state.currentQuestion ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Progress
        LinearProgressIndicator(
            progress = { state.progress },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = stringResource(R.string.quiz_question_number, state.currentQuestionIndex + 1, state.questions.size),
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(Modifier.height(8.dp))

        // Question
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.3f)
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = when (question.questionType) {
                        QuestionType.TRANSLATE_TO_UKRAINIAN -> stringResource(R.string.quiz_translate_to_ukrainian)
                        QuestionType.TRANSLATE_TO_ENGLISH -> stringResource(R.string.quiz_translate_to_english)
                    },
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = when (question.questionType) {
                        QuestionType.TRANSLATE_TO_UKRAINIAN -> question.word.english
                        QuestionType.TRANSLATE_TO_ENGLISH -> question.word.ukrainian
                    },
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        // Options
        question.options.forEachIndexed { index, option ->
            AnswerOption(
                text = option,
                isSelected = state.selectedAnswerIndex == index,
                isCorrect = if (state.isAnswerChecked) index == question.correctIndex else null,
                onClick = {
                    if (!state.isAnswerChecked) {
                        onIntent(QuizContract.Intent.SelectAnswer(index))
                    }
                }
            )
        }

        Spacer(Modifier.weight(1f))

        // Action button
        if (!state.isAnswerChecked) {
            Button(
                onClick = { onIntent(QuizContract.Intent.CheckAnswer) },
                enabled = state.selectedAnswerIndex != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.quiz_check_answer))
            }
        } else {
            Button(
                onClick = { onIntent(QuizContract.Intent.NextQuestion) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (state.currentQuestionIndex < state.questions.size - 1) stringResource(R.string.quiz_next_question) else stringResource(R.string.quiz_finish))
            }
        }
    }
}

@Composable
private fun AnswerOption(
    text: String,
    isSelected: Boolean,
    isCorrect: Boolean?,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = when {
            isCorrect == true -> Color(0xFF4CAF50)
            isCorrect == false && isSelected -> Color(0xFFF44336)
            isSelected -> MaterialTheme.colorScheme.primaryContainer
            else -> MaterialTheme.colorScheme.surface
        },
        label = "background"
    )

    val contentColor by animateColorAsState(
        targetValue = when {
            isCorrect == true -> Color.White
            isCorrect == false && isSelected -> Color.White
            isSelected -> MaterialTheme.colorScheme.onPrimaryContainer
            else -> MaterialTheme.colorScheme.onSurface
        },
        label = "content"
    )

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 4.dp else 2.dp
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun QuizResults(
    correctAnswers: Int,
    totalQuestions: Int,
    score: Int,
    onRestart: () -> Unit,
    onExit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.quiz_completed_title),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.3f)
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$score%",
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(R.string.quiz_score),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.quiz_correct_answers, correctAnswers, totalQuestions),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = onRestart,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.quiz_try_again))
        }

        Spacer(Modifier.height(8.dp))

        OutlinedButton(
            onClick = onExit,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.quiz_back_to_menu))
        }
    }
}

@Composable
private fun ErrorState(
    error: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = error,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}

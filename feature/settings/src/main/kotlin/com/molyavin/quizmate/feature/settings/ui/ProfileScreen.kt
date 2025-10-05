package com.molyavin.quizmate.feature.settings.ui

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.molyavin.quizmate.core.R
import com.molyavin.quizmate.core.theme.*
import com.molyavin.quizmate.core.theme.QuizMateTheme
import com.molyavin.quizmate.core.ui.GradientTopAppBar
import com.molyavin.quizmate.feature.settings.domain.model.AppLanguage
import com.molyavin.quizmate.feature.settings.domain.model.AppTheme
import com.molyavin.quizmate.feature.settings.presentation.SettingsContract
import com.molyavin.quizmate.feature.settings.presentation.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onNavigateBack: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val activity = context as? Activity

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SettingsContract.Effect.NavigateToLogin -> onNavigateToLogin()
                is SettingsContract.Effect.ShowError -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
                is SettingsContract.Effect.ShowSuccess -> {
                    // Перезапуск Activity відразу для застосування змін мови
                    activity?.recreate()
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            GradientTopAppBar(
                title = { Text(stringResource(R.string.profile_title), color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush =  QuizMateTheme.colors.backgroundGradient)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(Spacing.L)
            ) {
                // User Profile Header
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Spacing.XXL),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Avatar
                        Box(
                            modifier = Modifier
                                .size(IconSize.Avatar)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                modifier = Modifier.size(IconSize.L),
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }

                        Spacer(modifier = Modifier.height(Spacing.L))

                        Text(
                            text = state.user?.displayName ?: stringResource(R.string.profile_user),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = state.user?.email ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(Spacing.XXL))

                // Settings Section
                Text(
                    text = stringResource(R.string.profile_settings),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(start = Spacing.S, bottom = Spacing.S)
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                    )
                ) {
                    Column {
                        // Theme Setting
                        ThemeSettingItem(
                            currentTheme = state.theme,
                            onThemeSelected = { theme ->
                                viewModel.handleIntent(SettingsContract.Intent.SetTheme(theme))
                            }
                        )

                        HorizontalDivider()

                        // Language Setting
                        LanguageSettingItem(
                            currentLanguage = state.language,
                            onLanguageSelected = { language ->
                                viewModel.handleIntent(SettingsContract.Intent.SetLanguage(language))
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(Spacing.XXL))

                // Account Actions
                Text(
                    text = stringResource(R.string.profile_account),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(start = Spacing.S, bottom = Spacing.S)
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                    )
                ) {
                    Column {
                        // Sign Out
                        ListItem(
                            headlineContent = { Text(stringResource(R.string.profile_sign_out)) },
                            leadingContent = {
                                Icon(Icons.Default.ExitToApp, contentDescription = null)
                            },
                            modifier = Modifier.clickableRipple {
                                viewModel.handleIntent(SettingsContract.Intent.SignOut)
                            }
                        )

                        HorizontalDivider()

                        // Delete Account
                        ListItem(
                            headlineContent = {
                                Text(stringResource(R.string.profile_delete_account), color = MaterialTheme.colorScheme.error)
                            },
                            leadingContent = {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.error
                                )
                            },
                            modifier = Modifier.clickableRipple {
                                viewModel.handleIntent(SettingsContract.Intent.ShowDeleteDialog)
                            }
                        )
                    }
                }
            }

            // Delete Confirmation Dialog
            if (state.showDeleteDialog) {
                DeleteAccountDialog(
                    onDismiss = { viewModel.handleIntent(SettingsContract.Intent.HideDeleteDialog) },
                    onConfirm = { viewModel.handleIntent(SettingsContract.Intent.DeleteAccount) }
                )
            }

            // Loading Overlay
            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun ThemeSettingItem(
    currentTheme: AppTheme,
    onThemeSelected: (AppTheme) -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    ListItem(
        headlineContent = { Text(stringResource(R.string.profile_theme)) },
        supportingContent = { Text(getThemeName(currentTheme)) },
        leadingContent = { Icon(Icons.Default.Palette, contentDescription = null) },
        modifier = Modifier.clickableRipple { showBottomSheet = true }
    )

    if (showBottomSheet) {
        ThemeBottomSheet(
            currentTheme = currentTheme,
            onDismiss = { showBottomSheet = false },
            onThemeSelected = { theme ->
                onThemeSelected(theme)
                showBottomSheet = false
            }
        )
    }
}

@Composable
private fun LanguageSettingItem(
    currentLanguage: AppLanguage,
    onLanguageSelected: (AppLanguage) -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    ListItem(
        headlineContent = { Text(stringResource(R.string.profile_language)) },
        supportingContent = { Text(currentLanguage.displayName) },
        leadingContent = { Icon(Icons.Default.Language, contentDescription = null) },
        modifier = Modifier.clickableRipple { showBottomSheet = true }
    )

    if (showBottomSheet) {
        LanguageBottomSheet(
            currentLanguage = currentLanguage,
            onDismiss = { showBottomSheet = false },
            onLanguageSelected = { language ->
                onLanguageSelected(language)
                showBottomSheet = false
            }
        )
    }
}

@Composable
private fun DeleteAccountDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                Icons.Default.Warning,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )
        },
        title = { Text(stringResource(R.string.delete_account_title)) },
        text = {
            Text(stringResource(R.string.delete_account_message))
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text(stringResource(R.string.delete))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ThemeBottomSheet(
    currentTheme: AppTheme,
    onDismiss: () -> Unit,
    onThemeSelected: (AppTheme) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.theme_select),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = Spacing.XXL, vertical = Spacing.L)
            )

            AppTheme.entries.forEach { theme ->
                ListItem(
                    headlineContent = { Text(getThemeName(theme)) },
                    leadingContent = {
                        Icon(
                            imageVector = when (theme) {
                                AppTheme.LIGHT -> Icons.Default.LightMode
                                AppTheme.DARK -> Icons.Default.DarkMode
                                AppTheme.SYSTEM -> Icons.Default.SettingsBrightness
                            },
                            contentDescription = null
                        )
                    },
                    trailingContent = {
                        if (theme == currentTheme) {
                            Icon(Icons.Default.Check, contentDescription = null)
                        }
                    },
                    modifier = Modifier.clickableRipple { onThemeSelected(theme) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LanguageBottomSheet(
    currentLanguage: AppLanguage,
    onDismiss: () -> Unit,
    onLanguageSelected: (AppLanguage) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.language_select),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = Spacing.XXL, vertical = Spacing.L)
            )

            AppLanguage.entries.forEach { language ->
                ListItem(
                    headlineContent = { Text(language.displayName) },
                    leadingContent = {
                        Text(
                            text = when (language) {
                                AppLanguage.UKRAINIAN -> "🇺🇦"
                                AppLanguage.ENGLISH -> "🇬🇧"
                            },
                            style = MaterialTheme.typography.headlineMedium
                        )
                    },
                    trailingContent = {
                        if (language == currentLanguage) {
                            Icon(Icons.Default.Check, contentDescription = null)
                        }
                    },
                    modifier = Modifier.clickableRipple { onLanguageSelected(language) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun Modifier.clickableRipple(onClick: () -> Unit): Modifier {
    return this.then(
        Modifier.clickable(onClick = onClick)
    )
}

@Composable
private fun getThemeName(theme: AppTheme): String {
    return when (theme) {
        AppTheme.LIGHT -> stringResource(R.string.theme_light)
        AppTheme.DARK -> stringResource(R.string.theme_dark)
        AppTheme.SYSTEM -> stringResource(R.string.theme_system)
    }
}

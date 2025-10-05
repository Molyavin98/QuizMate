package com.molyavin.quizmate.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.molyavin.quizmate.core.R
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.molyavin.quizmate.core.theme.GradientBrushHorizontal
import com.molyavin.quizmate.flashcards.ui.FlashCardsScreen
import com.molyavin.quizmate.home.ui.HomeScreen
import com.molyavin.quizmate.quiz.ui.QuizScreen
import com.molyavin.quizmate.feature.vocabulary.ui.screen.DictionaryScreen
import com.molyavin.quizmate.feature.vocabulary.ui.screen.FolderDetailsScreen
import com.molyavin.quizmate.favorites.presentation.ui.FavoritesScreen
import com.molyavin.quizmate.feature.auth.presentation.ui.login.LoginScreen
import com.molyavin.quizmate.feature.auth.presentation.ui.register.RegisterScreen
import com.molyavin.quizmate.feature.settings.ui.ProfileScreen

/**
 * Головний екран з bottom navigation
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var showCreateSheet by remember { mutableStateOf(false) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Список роутів, де показується bottom navigation
    val routesWithBottomNav = listOf("home", "library")
    val showBottomBar = currentRoute in routesWithBottomNav

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = GradientBrushHorizontal),
        containerColor = Color.Transparent,
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = Color.Transparent,
                    modifier = Modifier.background(brush = GradientBrushHorizontal)
                ) {
                    val currentDestination = navBackStackEntry?.destination

                    BottomNavItem.entries.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.title, tint = Color.White) },
                            label = { Text(item.title, color = Color.White) },
                            selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                selectedTextColor = Color.White,
                                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                                unselectedTextColor = Color.White.copy(alpha = 0.6f),
                                indicatorColor = Color.White.copy(alpha = 0.2f)
                            ),
                            onClick = {
                                if (item == BottomNavItem.Create) {
                                    showCreateSheet = true
                                } else {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomNavItem.Home.route) {
                HomeScreen(
                    onNavigateToFolder = { folderId ->
                        navController.navigate("folder/$folderId")
                    },
                    onNavigateToFavorites = {
                        navController.navigate("favorites")
                    },
                    onNavigateToProfile = {
                        navController.navigate("profile")
                    }
                )
            }

            composable(BottomNavItem.Library.route) {
                DictionaryScreen(
                    onNavigateBack = { },
                    onNavigateToFolder = { folderId ->
                        navController.navigate("folder/$folderId")
                    }
                )
            }

            composable("folder/{folderId}") { backStackEntry ->
                val folderId = backStackEntry.arguments?.getString("folderId")?.toLongOrNull()
                FolderDetailsScreen(
                    folderId = folderId,
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToLearn = { id ->
                        navController.navigate("learn/$id")
                    },
                    onNavigateToFlashCards = { id ->
                        navController.navigate("flashcards/$id")
                    },
                    onNavigateToQuiz = { id ->
                        navController.navigate("quiz/$id")
                    }
                )
            }

            composable("learn/{folderId}") { backStackEntry ->
                val folderId = backStackEntry.arguments?.getString("folderId")?.toLongOrNull()
                DictionaryScreen(
                    onNavigateBack = { navController.popBackStack() },
                    folderId = folderId,
                    isLearningMode = true
                )
            }

            composable("flashcards/{folderId}") { backStackEntry ->
                val folderId = backStackEntry.arguments?.getString("folderId")?.toLongOrNull()
                FlashCardsScreen(
                    onNavigateBack = { navController.popBackStack() },
                    folderId = folderId
                )
            }

            composable("quiz/{folderId}") { backStackEntry ->
                QuizScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable("favorites") {
                FavoritesScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable("login") {
                LoginScreen(
                    onNavigateToRegister = { navController.navigate("register") },
                    onNavigateToHome = {
                        navController.navigate(BottomNavItem.Home.route) {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            }

            composable("register") {
                RegisterScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToHome = {
                        navController.navigate(BottomNavItem.Home.route) {
                            popUpTo("register") { inclusive = true }
                        }
                    }
                )
            }

            composable("profile") {
                ProfileScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToLogin = {
                        // Clear back stack and navigate to login
                        navController.navigate("login") {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }
        }

        // Create Bottom Sheet
        if (showCreateSheet) {
            CreateBottomSheet(
                onDismiss = { showCreateSheet = false },
                onFolderCreated = { showCreateSheet = false }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateBottomSheet(
    onDismiss: () -> Unit,
    onFolderCreated: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    var folderName by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is MainEffect.FolderCreated -> onFolderCreated()
                is MainEffect.ShowError -> {
                    // Можна показати SnackBar, якщо потрібно
                }
            }
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.create_folder_title),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = folderName,
                onValueChange = { folderName = it },
                label = { Text(stringResource(R.string.create_folder_name)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onDismiss) {
                    Text(stringResource(R.string.cancel))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (folderName.isNotBlank()) {
                            viewModel.createFolder(folderName)
                        }
                    },
                    enabled = folderName.isNotBlank()
                ) {
                    Text(stringResource(R.string.create_folder_button))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

enum class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    Home("home", "Home", Icons.Default.Home),
    Create("create", "Create", Icons.Default.Add),
    Library("library", "Library", Icons.Default.List)
}

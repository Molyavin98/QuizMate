# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**QuizMate (CrashyApp)** - Android-приложение для изучения английского языка с функционалом словаря, flashcards и квизов.

**Основные возможности:**
- 📚 Словарь с переводами, примерами и изображениями
- 🗂️ Организация слов по папкам
- 🎴 Flash Cards в стиле Quizlet для запоминания слов
- 📝 Квизы для проверки знаний
- 📥 Импорт слов из JSON файлов
- 🌐 Загрузка изображений из интернета (Unsplash)

## Architecture

**Clean Architecture + MVI Pattern:**
- **Presentation Layer**: ViewModels (MVI pattern), UI (Jetpack Compose)
- **Domain Layer**: UseCases, Repository Interfaces, Models
- **Data Layer**: Repository Implementations, Room Database, Data Sources

**Модульная структура:**
- `app` - главный модуль приложения, навигация
- `core` - общие компоненты, тема, утилиты, DI модули
- `feature` - модуль с feature-модулями:
  - `feature:home` - домашний экран со списком последних папок
  - `feature:vocabulary` - модуль словаря и управления словами/папками
  - `feature:flashcards` - модуль flash cards (Quizlet-style)
  - `feature:quiz` - модуль квизов
  - `feature:splash` - splash screen при запуске
  - `feature:favorites` - избранные слова

## Tech Stack

**Framework & Language:**
- Kotlin 2.0.21
- Android Gradle Plugin 8.13.0
- Min SDK: 24, Target/Compile SDK: 36
- Java 17 compatibility

**UI:**
- Jetpack Compose (BOM 2024.12.01)
- Material 3 Design
- Compose Navigation 2.8.5
- Coil 2.7.0 (image loading)

**Architecture Components:**
- ViewModel & Lifecycle 2.8.7
- Kotlin Coroutines 1.9.0
- Flow for reactive streams

**Dependency Injection:**
- Hilt 2.52
- KSP 2.0.21-1.0.28

**Database:**
- Room 2.6.1 (local persistence)

**Networking:**
- Retrofit 2.11.0
- OkHttp 4.12.0
- Gson (JSON parsing)

**Other:**
- DataStore Preferences 1.1.1
- Timber 5.0.1 (logging)

## Project Structure

```
QuizMate/
├── app/                              # Main application module
│   ├── src/main/
│   │   ├── kotlin/com/molyavin/quizmate/
│   │   │   ├── MainActivity.kt      # Entry point with Splash screen
│   │   │   ├── MainScreen.kt        # Bottom navigation + NavHost
│   │   │   ├── MainViewModel.kt     # ViewModel для создания папок
│   │   │   └── QuizMateApplication.kt # App initialization, Hilt, Timber
│   │   ├── res/                     # Resources
│   │   │   └── xml/
│   │   │       └── network_security_config.xml # HTTPS config for Unsplash
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
│
├── core/                             # Core module (shared components)
│   ├── src/main/kotlin/com/molyavin/quizmate/core/
│   │   ├── theme/                   # Material 3 theme, gradients
│   │   ├── ui/                      # Reusable UI components (GradientTopAppBar)
│   │   ├── datastore/               # DataStore preferences
│   │   └── network/                 # Network DI module
│   └── build.gradle.kts
│
├── feature/                          # Feature modules container
│   ├── home/                        # Home screen (start destination)
│   │   └── ui/HomeScreen.kt         # Recent folders + favorites
│   │
│   ├── vocabulary/                  # Dictionary & words management
│   │   ├── domain/
│   │   │   ├── model/               # Word, Folder, Difficulty
│   │   │   ├── repository/          # VocabularyRepository interface
│   │   │   └── usecase/             # 11 use cases (Add, Delete, Search, Import, etc.)
│   │   ├── data/
│   │   │   ├── local/               # Room DB (WordDao, FolderDao, Entities)
│   │   │   └── repository/          # VocabularyRepositoryImpl
│   │   ├── presentation/
│   │   │   ├── DictionaryContract.kt  # MVI (State, Intent, Effect)
│   │   │   └── DictionaryViewModel.kt # MVI ViewModel
│   │   ├── ui/screen/               # DictionaryScreen, FolderDetailsScreen
│   │   └── build.gradle.kts
│   │
│   ├── flashcards/                  # Flash cards (Quizlet-style)
│   │   ├── domain/
│   │   │   ├── model/               # FlashCard model
│   │   │   ├── repository/          # FlashCardsRepository
│   │   │   └── usecase/             # GetFlashCardsUseCase
│   │   ├── data/                    # Repository implementation
│   │   ├── presentation/            # FlashCardsContract + ViewModel (MVI)
│   │   ├── ui/FlashCardsScreen.kt   # Swipe animations, flip cards
│   │   └── build.gradle.kts
│   │
│   ├── quiz/                        # Quiz feature
│   │   ├── domain/                  # QuizQuestion, GenerateQuizUseCase
│   │   ├── presentation/            # QuizContract + ViewModel (MVI)
│   │   ├── ui/QuizScreen.kt         # Quiz UI
│   │   └── build.gradle.kts
│   │
│   ├── splash/                      # Splash screen
│   │   └── ui/SplashScreen.kt       # Animated splash
│   │
│   └── favorites/                   # Favorites feature
│       └── ui/FavoritesScreen.kt    # Favorite words list
│
├── gradle/
│   ├── libs.versions.toml           # Gradle Version Catalog
│   ├── app-module.gradle            # Shared app module config
│   └── feature-module.gradle        # Shared feature module config
├── settings.gradle.kts
└── build.gradle.kts

```

## Build Commands

**Сборка debug APK:**
```bash
./gradlew assembleDebug
```

**Сборка release APK:**
```bash
./gradlew assembleRelease
```

**Очистка проекта:**
```bash
./gradlew clean
```

**Полная пересборка:**
```bash
./gradlew clean assembleDebug
```

## Testing

**Запуск unit-тестов:**
```bash
./gradlew test
```

**Запуск unit-тестов для debug варианта:**
```bash
./gradlew testDebugUnitTest
```

**Запуск instrumented тестов (требуется эмулятор или устройство):**
```bash
./gradlew connectedAndroidTest
```

**Запуск конкретного теста:**
```bash
./gradlew test --tests "com.molyavin.quizmate.ExampleUnitTest.addition_isCorrect"
```

**Запуск тестов конкретного модуля:**
```bash
./gradlew :feature:vocabulary:test
```

## Code Quality

**Lint проверка:**
```bash
./gradlew lint
```

**Lint с отчетом:**
```bash
./gradlew lintDebug
```

## Dependencies Management

Зависимости управляются через **Gradle Version Catalog** (`gradle/libs.versions.toml`).

**Использование в build.gradle.kts:**
```kotlin
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}
```

**Shared Gradle configurations:**
- `gradle/app-module.gradle` - общая конфигурация для app модуля
- `gradle/feature-module.gradle` - общая конфигурация для feature модулей

Подключение в модуле:
```kotlin
apply(from = "$rootDir/gradle/feature-module.gradle")
```

## Package Name

`com.molyavin.quizmate`

## Key Features Implementation

### 1. Flash Cards (Quizlet-style)
- Flip animation on click
- Swipe gestures: left = "don't know", right = "know"
- Progress tracking
- Language direction toggle (English→Ukrainian / Ukrainian→English)
- Shuffle cards
- Session completion screen

### 2. Dictionary
- Two modes: Library (full access) и Learning (read-only for specific folder)
- Add/Edit/Delete words
- Folder organization
- Search functionality
- Image support (Unsplash URLs)
- JSON import with duplicate detection

### 3. Navigation
- Bottom Navigation Bar: Home / Create / Library
- Create button opens BottomSheet for folder creation
- Folder details with 3 learning modes: Learn Words, Flash Cards, Quiz
- Routes:
  - `home` - HomeScreen
  - `library` - DictionaryScreen (Library mode)
  - `folder/{folderId}` - FolderDetailsScreen
  - `learn/{folderId}` - DictionaryScreen (Learning mode)
  - `flashcards/{folderId}` - FlashCardsScreen
  - `quiz/{folderId}` - QuizScreen
  - `favorites` - FavoritesScreen

### 4. Database Schema (Room)
```kotlin
@Entity(tableName = "words")
data class WordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val english: String,
    val ukrainian: String,
    val example: String? = null,
    val category: String? = null,
    val difficulty: String = "MEDIUM",
    val imageUrl: String? = null,
    val folderId: Long? = null,
    val isLearned: Boolean = false,
    val practiceCount: Int = 0,
    val correctCount: Int = 0,
    val lastPracticed: Long? = null,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "folders")
data class FolderEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val createdAt: Long = System.currentTimeMillis()
)
```

## Development Guidelines

### MVI Pattern
Каждый feature module следует MVI pattern:
- **Contract**: State, Intent, Effect
- **ViewModel**: обрабатывает Intents, обновляет State, отправляет Effects
- **UI**: наблюдает за State, отправляет Intents

### Compose Best Practices
- Используйте `remember`, `LaunchedEffect`, `derivedStateOf` правильно
- Избегайте side-effects в Composable функциях
- Используйте `collectAsStateWithLifecycle()` для Flow

### Image Loading
- Используйте Coil для загрузки изображений
- Unsplash URL format: `https://images.unsplash.com/photo-XXXXXXXXX?w=400`
- НЕ используйте старый API: `source.unsplash.com` (deprecated, returns HTTP 503)

### JSON Import
- Формат: массив объектов с полями `english`, `ukrainian`, `example`, `category`, `difficulty`, `imageUrl`
- Автоматическое удаление дублікатов по english слову (case-insensitive)
- Валидация пустых полей

## Common Issues & Solutions

**Problem**: Images not loading
- Check network_security_config.xml allows HTTPS
- Verify Unsplash URLs use `images.unsplash.com` format
- Check INTERNET permission in AndroidManifest

**Problem**: Build fails with Hilt errors
- Run `./gradlew clean`
- Check all @Inject constructors are correct
- Verify module dependencies include required features

**Problem**: Navigation not working
- Check route parameters match in NavHost and navigate() calls
- Verify folderId extraction from backStackEntry arguments

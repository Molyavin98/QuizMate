# KMP Migration Guide - Руководство по миграции на Kotlin Multiplatform

## 🎯 Цель
Подготовить QuizMate к миграции на Kotlin Multiplatform для поддержки Android + iOS

## 📊 Текущая vs Целевая архитектура

### Текущая структура (Android-only):
```
feature/auth/
└── src/main/kotlin/
    ├── data/
    ├── domain/
    └── presentation/
```

### Целевая структура (KMP):
```
feature/auth/
├── domain/                    # KMP модуль
│   └── src/
│       ├── commonMain/kotlin/ # Общий код
│       ├── androidMain/
│       └── iosMain/
├── data/                      # KMP модуль
│   └── src/
│       ├── commonMain/kotlin/
│       ├── androidMain/
│       └── iosMain/
└── presentation/              # Платформенные модули
    ├── android/               # Android UI (Compose)
    └── ios/                   # iOS UI (SwiftUI)
```

## 🔧 ШАГ 1: Реструктуризация модулей

### 1.1. Разделить каждый feature на подмодули

Для каждого feature (auth, vocabulary, flashcards, settings):

```
feature/
├── auth/
│   ├── domain/
│   │   ├── build.gradle.kts          # KMP plugin
│   │   └── src/
│   │       └── commonMain/kotlin/
│   │           ├── model/
│   │           ├── repository/
│   │           └── usecase/
│   ├── data/
│   │   ├── build.gradle.kts          # KMP plugin
│   │   └── src/
│   │       ├── commonMain/kotlin/
│   │       │   └── repository/
│   │       └── androidMain/kotlin/   # Room, Firebase
│   └── presentation/
│       ├── android/
│       │   ├── build.gradle.kts      # Android plugin
│       │   └── src/main/kotlin/
│       │       └── ui/               # Compose UI
│       └── ios/                       # Будет создано позже
```

### 1.2. Обновить settings.gradle.kts

```kotlin
// Было:
include(":feature:auth")

// Станет:
include(":feature:auth:domain")
include(":feature:auth:data")
include(":feature:auth:presentation:android")
```

## 🔧 ШАГ 2: Конфигурация KMP модулей

### 2.1. Domain модуль (100% общий код)

**feature/auth/domain/build.gradle.kts:**
```kotlin
plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    androidTarget()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            // Только KMP совместимые библиотеки
            implementation(libs.kotlinx.coroutines.core)
        }

        androidMain.dependencies {
            // Android-специфичные зависимости (если нужны)
        }

        iosMain.dependencies {
            // iOS-специфичные зависимости (если нужны)
        }
    }
}
```

### 2.2. Data модуль (commonMain + expect/actual)

**feature/auth/data/build.gradle.kts:**
```kotlin
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.sqldelight) // Вместо Room
}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:auth:domain"))

            // KMP библиотеки
            implementation(libs.ktor.client.core)
            implementation(libs.sqldelight.runtime)
            implementation(libs.kotlinx.coroutines.core)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            implementation(libs.sqldelight.android.driver)
            implementation(libs.firebase.auth) // Через expect/actual
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sqldelight.native.driver)
        }
    }
}
```

### 2.3. Presentation модуль (платформенный)

**feature/auth/presentation/android/build.gradle.kts:**
```kotlin
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
}

dependencies {
    implementation(project(":feature:auth:domain"))

    // Android UI
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.hilt.android)
}
```

## 🔧 ШАГ 3: Миграция кода

### 3.1. Domain слой - убрать Android зависимости

❌ **ПЛОХО (Android-специфично):**
```kotlin
import android.os.Parcelable
import androidx.lifecycle.ViewModel

data class User(val id: String) : Parcelable  // ❌ Android
```

✅ **ХОРОШО (KMP совместимо):**
```kotlin
// Только Kotlin stdlib
data class User(val id: String)

// Kotlinx.serialization для сериализации
@Serializable
data class User(val id: String)
```

### 3.2. Data слой - использовать expect/actual

#### Room → SQLDelight

**Было (Room - Android only):**
```kotlin
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String
)

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    suspend fun getAll(): List<UserEntity>
}
```

**Станет (SQLDelight - KMP):**

**commonMain/sqldelight/database/User.sq:**
```sql
CREATE TABLE User (
    id TEXT PRIMARY KEY NOT NULL,
    name TEXT NOT NULL
);

selectAll:
SELECT * FROM User;

insert:
INSERT INTO User(id, name) VALUES (?, ?);
```

**commonMain/repository/UserRepositoryImpl.kt:**
```kotlin
class UserRepositoryImpl(
    private val database: Database
) : UserRepository {
    override suspend fun getUsers(): List<User> {
        return database.userQueries.selectAll()
            .executeAsList()
            .map { it.toDomain() }
    }
}
```

#### Firebase Auth - expect/actual

**commonMain:**
```kotlin
expect class FirebaseAuthService {
    suspend fun signInWithEmail(email: String, password: String): AuthResult
    suspend fun signInWithGoogle(idToken: String): AuthResult
}
```

**androidMain:**
```kotlin
actual class FirebaseAuthService(
    private val auth: FirebaseAuth
) {
    actual suspend fun signInWithEmail(email: String, password: String): AuthResult {
        return suspendCoroutine { continuation ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { /* ... */ }
                .addOnFailureListener { /* ... */ }
        }
    }

    actual suspend fun signInWithGoogle(idToken: String): AuthResult {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        return suspendCoroutine { continuation ->
            auth.signInWithCredential(credential)
                .addOnSuccessListener { /* ... */ }
                .addOnFailureListener { /* ... */ }
        }
    }
}
```

**iosMain:**
```kotlin
actual class FirebaseAuthService {
    actual suspend fun signInWithEmail(email: String, password: String): AuthResult {
        // iOS Firebase SDK implementation
    }

    actual suspend fun signInWithGoogle(idToken: String): AuthResult {
        // iOS Firebase SDK implementation
    }
}
```

### 3.3. Presentation слой - ViewModels

#### Shared ViewModel (опционально)

Вы можете создать общий ViewModel используя **KMP ViewModel** библиотеку:

**commonMain (shared logic):**
```kotlin
// feature/auth/domain/src/commonMain/kotlin/viewmodel/
class AuthViewModel(
    private val signInUseCase: AuthSignInUseCase
) {
    val state = MutableStateFlow(AuthState())

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            // Общая бизнес-логика
        }
    }
}
```

**androidMain (Compose wrapper):**
```kotlin
@HiltViewModel
class AndroidAuthViewModel @Inject constructor(
    signInUseCase: AuthSignInUseCase
) : ViewModel() {
    private val sharedViewModel = AuthViewModel(signInUseCase)
    val state = sharedViewModel.state.asStateFlow()

    fun signIn(email: String, password: String) {
        sharedViewModel.signIn(email, password)
    }
}
```

## 🔧 ШАГ 4: Обновить DI (Dependency Injection)

### 4.1. Koin вместо Hilt (для commonMain)

**commonMain:**
```kotlin
// feature/auth/domain/src/commonMain/kotlin/di/
val authDomainModule = module {
    factory { AuthSignInUseCase(get()) }
    factory { AuthRegisterUseCase(get()) }
}

val authDataModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single { FirebaseAuthService() }
}
```

**androidMain:**
```kotlin
// feature/auth/data/src/androidMain/kotlin/di/
actual fun platformAuthModule() = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseAuthService(get()) }
}
```

**iosMain:**
```kotlin
actual fun platformAuthModule() = module {
    single { FirebaseAuthService() } // iOS implementation
}
```

### 4.2. Hilt только для Android UI

Hilt остается только в `presentation/android` для Android-специфичного кода.

## 🔧 ШАГ 5: Библиотеки для замены

| Android | KMP Alternative |
|---------|----------------|
| Room | **SQLDelight** |
| Retrofit | **Ktor Client** |
| Gson | **Kotlinx.serialization** |
| Hilt/Dagger | **Koin** (для commonMain) |
| Jetpack ViewModel | **KMP ViewModel** или custom |
| DataStore | **Multiplatform Settings** |
| Coil | **Kamel** или **Coil3** (KMP) |

## 📋 Пошаговый план миграции

### Фаза 1: Подготовка (1-2 недели)
- [ ] Изучить KMP документацию
- [ ] Настроить Xcode и iOS окружение
- [ ] Создать тестовый KMP модуль

### Фаза 2: Реструктуризация (2-3 недели)
- [ ] Разделить feature:auth на domain/data/presentation
- [ ] Разделить feature:vocabulary на domain/data/presentation
- [ ] Разделить feature:flashcards на domain/data/presentation
- [ ] Разделить feature:settings на domain/data/presentation

### Фаза 3: Миграция Data слоя (3-4 недели)
- [ ] Room → SQLDelight миграция
- [ ] Retrofit → Ktor миграция
- [ ] Добавить expect/actual для Firebase
- [ ] DataStore → Multiplatform Settings

### Фаза 4: iOS UI (4-6 недель)
- [ ] Создать iOS проект
- [ ] Интегрировать KMP фреймворк
- [ ] Разработать SwiftUI экраны
- [ ] Или использовать Compose Multiplatform

### Фаза 5: Тестирование (2-3 недели)
- [ ] Тестирование на iOS
- [ ] Регрессионное тестирование Android
- [ ] Исправление багов

## 🔗 Полезные ресурсы

1. **Официальная документация:**
   - https://kotlinlang.org/docs/multiplatform.html
   - https://www.jetbrains.com/help/kotlin-multiplatform-dev/

2. **KMP библиотеки:**
   - SQLDelight: https://cashapp.github.io/sqldelight/
   - Ktor: https://ktor.io/docs/client.html
   - Koin: https://insert-koin.io/docs/reference/koin-mp/kmp/
   - Kotlinx.serialization: https://github.com/Kotlin/kotlinx.serialization

3. **Примеры проектов:**
   - https://github.com/JetBrains/compose-multiplatform
   - https://github.com/touchlab/KaMPKit

## ⚡ Быстрый старт (Proof of Concept)

Хотите начать прямо сейчас? Вот минимальный план:

1. **Создать отдельный KMP модуль `shared`:**
```
shared/
├── build.gradle.kts (KMP plugin)
└── src/
    ├── commonMain/kotlin/
    ├── androidMain/kotlin/
    └── iosMain/kotlin/
```

2. **Переместить один UseCase туда:**
```kotlin
// shared/src/commonMain/kotlin/
class GetWordsUseCase(private val repository: WordRepository) {
    suspend operator fun invoke(): List<Word> = repository.getWords()
}
```

3. **Собрать iOS framework:**
```bash
./gradlew :shared:linkDebugFrameworkIosArm64
```

4. **Использовать в iOS проекте через Xcode**

---

## 🚀 Следующие шаги

**Хотите начать миграцию?** Выберите подход:

### Вариант A: Постепенная миграция (рекомендуется)
1. Сначала реструктурировать один модуль (например, auth)
2. Протестировать на Android
3. Добавить iOS поддержку
4. Повторить для остальных модулей

### Вариант B: Параллельная разработка
1. Создать новый `shared` KMP модуль
2. Постепенно переносить код из feature модулей
3. Поддерживать оба варианта до полной миграции

**Что выбираете?**

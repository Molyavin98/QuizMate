# 🎨 Compose Multiplatform Guide для QuizMate

## 🎯 Что такое Compose Multiplatform?

**Compose Multiplatform** - это фреймворк от JetBrains, который позволяет писать **один UI код** на Kotlin с Jetpack Compose, который работает на:
- ✅ Android
- ✅ iOS
- ✅ Desktop (Windows, macOS, Linux)
- ✅ Web (экспериментально)

## 🏗️ Архитектура QuizMate

```
┌─────────────────────────────────────────────────┐
│                  composeApp/                    │
│         (Compose Multiplatform Module)          │
│                                                 │
│  ┌─────────────────────────────────────────┐  │
│  │       commonMain/                       │  │
│  │  ┌─────────────────────────────────┐   │  │
│  │  │  App.kt - Общий UI для всех     │   │  │
│  │  │  платформ (Compose)              │   │  │
│  │  └─────────────────────────────────┘   │  │
│  │                                         │  │
│  │  ┌─────────────────────────────────┐   │  │
│  │  │  AppViewModel.kt                │   │  │
│  │  │  - AuthState management         │   │  │
│  │  │  - Navigation logic             │   │  │
│  │  └─────────────────────────────────┘   │  │
│  └─────────────────────────────────────────┘  │
│                                                 │
│  ┌──────────────────┐  ┌──────────────────┐   │
│  │  androidMain/    │  │    iosMain/      │   │
│  │                  │  │                  │   │
│  │  MainActivity.kt │  │  main.kt         │   │
│  │  (Entry point)   │  │  (UIViewController)  │
│  └──────────────────┘  └──────────────────┘   │
└─────────────────────────────────────────────────┘
                    │
        ┌───────────┼───────────┐
        │           │           │
┌───────▼──────┐ ┌─▼────────┐ ┌▼─────────┐
│ Auth Module  │ │Vocabulary│ │   Core   │
│   (KMP)      │ │  (KMP)   │ │  (KMP)   │
└──────────────┘ └──────────┘ └──────────┘
```

## 📁 Структура проекта

```
QuizMate/
├── composeApp/                          # Compose Multiplatform App
│   ├── src/
│   │   ├── commonMain/kotlin/           # Общий Compose UI
│   │   │   └── com/molyavin/quizmate/
│   │   │       ├── App.kt               # Главный UI компонент
│   │   │       ├── AppViewModel.kt      # ViewModel для навигации
│   │   │       └── di/
│   │   │           └── AppModule.kt     # Koin DI
│   │   │
│   │   ├── androidMain/                 # Android специфика
│   │   │   ├── kotlin/
│   │   │   │   └── MainActivity.kt      # Android Entry Point
│   │   │   │   └── QuizMateApplication.kt
│   │   │   ├── AndroidManifest.xml
│   │   │   └── res/
│   │   │
│   │   └── iosMain/                     # iOS специфика
│   │       └── kotlin/
│   │           └── main.kt              # iOS Entry Point
│   │
│   └── build.gradle.kts                 # KMP конфигурация
│
├── iosApp/                              # iOS Wrapper (минимальный)
│   └── iosApp/
│       ├── iOSApp.swift                 # SwiftUI App
│       └── ContentView.swift            # Обёртка для Compose
│
├── feature/                             # KMP Feature модули
│   ├── auth/                            # ✅ Готов
│   │   ├── domain/                      # Бизнес-логика
│   │   ├── data/                        # Репозитории + Firebase
│   │   └── presentation/                # ViewModels + UI
│   │
│   └── vocabulary/                      # ⚠️ В процессе
│       ├── domain/
│       ├── data/
│       └── presentation/
│
└── app/                                 # Старое Android приложение (deprecated)
    └── ...                              # Будет удалено после миграции
```

## 🚀 Как это работает?

### Android

1. **MainActivity.kt** (Android Entry Point):
```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(onGoogleSignInClick = { startGoogleSignIn() })
        }
    }
}
```

2. **App.kt** (Общий Compose UI):
```kotlin
@Composable
fun App(onGoogleSignInClick: () -> Unit) {
    // Один UI для Android и iOS!
    NavHost(...) {
        composable("login") { LoginScreen(...) }
        composable("register") { RegisterScreen(...) }
        composable("main") { MainScreen(...) }
    }
}
```

### iOS

1. **iOSApp.swift** (iOS Entry Point):
```swift
@main
struct iOSApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView() // Вызывает Compose
        }
    }
}
```

2. **ContentView.swift** (SwiftUI → Compose мост):
```swift
struct ContentView: View {
    var body: some View {
        ComposeView() // Рендерит Kotlin Compose UI
    }
}

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        return MainKt.MainViewController() // Вызов Kotlin функции
    }
}
```

3. **main.kt** (Kotlin → iOS UIViewController):
```kotlin
fun MainViewController(): UIViewController {
    startKoin { modules(appModule) }

    return ComposeUIViewController {
        App(onGoogleSignInClick = { /* iOS logic */ })
    }
}
```

## 🎨 Преимущества этого подхода

### ✅ Что переиспользуется между платформами:

1. **100% UI кода** - `LoginScreen`, `RegisterScreen`, все экраны
2. **100% бизнес-логики** - ViewModels, Use Cases, Repositories
3. **100% навигации** - Compose Navigation работает везде
4. **100% state management** - MVI pattern, Flow, StateFlow
5. **100% DI** - Koin модули
6. **Все анимации и темы** - Material 3 компоненты

### ⚡ Что специфично для платформ:

- **Android**: Google Sign-In integration, Activity lifecycle
- **iOS**: SwiftUI wrapper, UIViewController интеграция

## 📝 Сравнение подходов

| Аспект | SwiftUI + KMP | Compose Multiplatform |
|--------|---------------|----------------------|
| UI код | Дублируется (Swift + Kotlin) | Один раз (Kotlin) |
| Навигация | 2 системы (SwiftUI + Compose Nav) | Одна (Compose Nav) |
| Themes | Ручная синхронизация | Автоматически |
| Animations | Дублируются | Переиспользуются |
| Обучение | Swift + Kotlin | Только Kotlin |
| Разработка | Медленнее (2 UI) | Быстрее (1 UI) |

## 🛠️ Как запустить

### Android

```bash
# Собрать и запустить на Android
./gradlew :composeApp:installDebug
```

### iOS

#### 1. Соберите Compose framework

```bash
./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64
```

Это создаст framework в:
```
composeApp/build/bin/iosSimulatorArm64/debugFramework/ComposeApp.framework
```

#### 2. Создайте Xcode проект

1. Откройте Xcode
2. File → New → Project
3. iOS → App
4. Product Name: `iosApp`
5. Bundle ID: `com.molyavin.quizmate.ios`
6. Interface: **SwiftUI**
7. Сохраните в `QuizMate/iosApp/`

#### 3. Добавьте существующие файлы

Перетащите из Finder в Xcode:
- `iOSApp.swift`
- `ContentView.swift`

#### 4. Настройте Build Phases

**Build Phases** → "+" → **New Run Script Phase**:

```bash
cd "$SRCROOT/.."
./gradlew :composeApp:embedAndSignAppleFrameworkForXcode
```

#### 5. Добавьте Framework Search Paths

**Build Settings** → **Framework Search Paths**:
```
$(SRCROOT)/../composeApp/build/bin/iosSimulatorArm64/debugFramework
```

#### 6. Свяжите framework

**General** → **Frameworks, Libraries, and Embedded Content**:
- Добавьте `ComposeApp.framework`
- Установите "Embed & Sign"

#### 7. Установите CocoaPods

```bash
cd iosApp
pod install
```

#### 8. Запустите

```bash
open iosApp.xcworkspace
# Выберите симулятор и нажмите Run
```

## 🔄 Миграция существующих экранов

### Шаг 1: Скопируйте Composable функции

Из `app/src/main/kotlin/...` в `composeApp/src/commonMain/kotlin/...`

### Шаг 2: Замените Android-specific код

```kotlin
// ❌ Было (Android-only):
import android.content.Context
val context = LocalContext.current

// ✅ Стало (Multiplatform):
// Используйте expect/actual для platform-specific кода
```

### Шаг 3: Обновите импорты

```kotlin
// ❌ Было:
import androidx.compose.runtime.*

// ✅ Стало (то же самое!):
import androidx.compose.runtime.*
```

Compose Multiplatform использует те же API!

## 🎯 Следующие шаги

### Краткосрочные

1. ✅ Создан `composeApp` модуль с Compose Multiplatform
2. ✅ Создан общий `App.kt` с навигацией
3. ✅ Настроены Entry Points для Android и iOS
4. ⏳ Мигрировать `MainScreen.kt` из `app` в `composeApp`
5. ⏳ Мигрировать все feature screens в `composeApp`

### Среднесрочные

6. ⏳ Настроить Xcode проект
7. ⏳ Собрать и запустить на iOS симуляторе
8. ⏳ Протестировать навигацию на iOS
9. ⏳ Реализовать Google Sign-In для iOS
10. ⏳ Протестировать на реальном iOS устройстве

### Долгосрочные

11. ⏳ Удалить старый `app` модуль
12. ⏳ Переименовать `composeApp` в `app`
13. ⏳ Опубликовать в App Store и Google Play

## 📚 Ресурсы

- [Compose Multiplatform Official](https://www.jetbrains.com/lp/compose-multiplatform/)
- [Compose for iOS Tutorial](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-create-first-app.html)
- [KMP Samples](https://github.com/JetBrains/compose-multiplatform/tree/master/examples)
- [Koin Multiplatform](https://insert-koin.io/docs/reference/koin-mp/kmp)

## 🎉 Почему это круто?

```
🚀 Один UI → Две платформы
⚡ Быстрая разработка
🔄 Легкая поддержка
✨ Нативная производительность
🎨 Одинаковый UX везде
```

---

**Готовы к разработке на Compose Multiplatform!** 🎊

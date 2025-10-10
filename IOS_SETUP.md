# 🍎 iOS Setup Guide для QuizMate

Полное руководство по настройке и запуску iOS версии QuizMate.

## 📋 Что вам понадобится

- **macOS** (Big Sur 11.0 или новее)
- **Xcode 15+** (скачать из App Store)
- **CocoaPods** для управления зависимостями
- **Command Line Tools** для Xcode
- **Android Studio** с Kotlin Multiplatform Mobile плагином

## 🚀 Пошаговая настройка

### Шаг 1: Установка инструментов

#### 1.1 Установите Xcode

```bash
# Откройте App Store и установите Xcode
# Или используйте команду:
xcode-select --install
```

#### 1.2 Установите CocoaPods

```bash
# Установка через RubyGems
sudo gem install cocoapods

# Проверка установки
pod --version
```

#### 1.3 Установите KMM плагин в Android Studio

1. Откройте Android Studio
2. Settings → Plugins
3. Найдите "Kotlin Multiplatform Mobile"
4. Установите и перезапустите IDE

### Шаг 2: Настройка Firebase для iOS

#### 2.1 Добавьте iOS приложение в Firebase Console

1. Откройте [Firebase Console](https://console.firebase.google.com/)
2. Выберите проект **QuizMate**
3. Нажмите "Add app" → iOS
4. Заполните форму:
   - **iOS bundle ID**: `com.molyavin.quizmate`
   - **App nickname**: `QuizMate iOS` (опционально)
   - **App Store ID**: оставьте пустым (пока)

#### 2.2 Скачайте GoogleService-Info.plist

1. После создания iOS app, скачайте `GoogleService-Info.plist`
2. Замените файл в проекте:
   ```bash
   cp ~/Downloads/GoogleService-Info.plist iosApp/iosApp/GoogleService-Info.plist
   ```

#### 2.3 Настройте Authentication

В Firebase Console:
1. **Authentication** → Sign-in method
2. Включите **Email/Password**
3. Включите **Google** (если нужно)
   - Для Google Sign-In понадобится настроить OAuth Client ID

### Шаг 3: Сборка KMP фреймворков

Из корня проекта QuizMate выполните:

```bash
# Соберите все KMP модули для iOS симулятора (Apple Silicon)
./gradlew linkDebugFrameworkIosSimulatorArm64

# Или для Intel Mac:
./gradlew linkDebugFrameworkIosX64

# Или для реального устройства:
./gradlew linkDebugFrameworkIosArm64
```

Это создаст `.framework` файлы в следующих директориях:
```
feature/auth/domain/build/bin/iosSimulatorArm64/debugFramework/
feature/auth/data/build/bin/iosSimulatorArm64/debugFramework/
feature/auth/presentation/build/bin/iosSimulatorArm64/debugFramework/
```

### Шаг 4: Установка iOS зависимостей

```bash
cd iosApp
pod install
```

Эта команда установит:
- Firebase/Auth
- Firebase/Firestore
- GoogleSignIn

### Шаг 5: Создание Xcode проекта

Так как Xcode проект еще не существует, вам нужно создать его вручную:

#### 5.1 Создайте новый проект в Xcode

1. Откройте Xcode
2. File → New → Project
3. Выберите **iOS** → **App**
4. Заполните:
   - **Product Name**: `iosApp`
   - **Organization Identifier**: `com.molyavin`
   - **Bundle Identifier**: `com.molyavin.quizmate`
   - **Interface**: SwiftUI
   - **Language**: Swift
   - **Storage**: None
5. Сохраните в директорию `QuizMate/iosApp/`

#### 5.2 Добавьте существующие файлы

1. Перетащите файлы из Finder в Xcode:
   - `QuizMateApp.swift`
   - `ContentView.swift`
   - `GoogleService-Info.plist`
2. Убедитесь, что они добавлены в target `iosApp`

#### 5.3 Настройте Build Phases

1. Выберите проект → target `iosApp`
2. **Build Phases** → нажмите "+" → **New Run Script Phase**
3. Добавьте скрипт:

```bash
cd "$SRCROOT/.."
./gradlew :feature:auth:domain:embedAndSignAppleFrameworkForXcode
./gradlew :feature:auth:data:embedAndSignAppleFrameworkForXcode
./gradlew :feature:auth:presentation:embedAndSignAppleFrameworkForXcode
```

4. Назовите фазу "Embed KMP Frameworks"
5. Переместите её ПЕРЕД "Compile Sources"

#### 5.4 Настройте Framework Search Paths

1. **Build Settings** → найдите "Framework Search Paths"
2. Добавьте (для Debug):

```
$(SRCROOT)/../feature/auth/domain/build/bin/iosSimulatorArm64/debugFramework
$(SRCROOT)/../feature/auth/data/build/bin/iosSimulatorArm64/debugFramework
$(SRCROOT)/../feature/auth/presentation/build/bin/iosSimulatorArm64/debugFramework
```

3. Для Release аналогично, но замените `debugFramework` на `releaseFramework`

#### 5.5 Свяжите KMP фреймворки

1. **General** → **Frameworks, Libraries, and Embedded Content**
2. Нажмите "+" → **Add Other** → **Add Files**
3. Найдите и добавьте `.framework` файлы:
   - `auth_domain.framework`
   - `auth_data.framework`
   - `auth_presentation.framework`
4. Для каждого установите "Embed & Sign"

### Шаг 6: Настройка Info.plist

Добавьте в `Info.plist`:

```xml
<key>CFBundleURLTypes</key>
<array>
    <dict>
        <key>CFBundleTypeRole</key>
        <string>Editor</string>
        <key>CFBundleURLSchemes</key>
        <array>
            <string>com.googleusercontent.apps.YOUR_REVERSED_CLIENT_ID</string>
        </array>
    </dict>
</array>
```

Замените `YOUR_REVERSED_CLIENT_ID` на значение из `GoogleService-Info.plist`.

### Шаг 7: Инициализация Firebase и Koin

Обновите `QuizMateApp.swift`:

```swift
import SwiftUI
import FirebaseCore
// import auth_domain // После того как фреймворки подключены

@main
struct QuizMateApp: App {
    init() {
        // Initialize Firebase
        FirebaseApp.configure()

        // Initialize Koin
        // KoinKt.doInitKoin() // Раскомментируйте после подключения KMP модулей
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
```

### Шаг 8: Запуск приложения

1. Откройте `iosApp.xcworkspace` (НЕ `.xcodeproj`!)
2. Выберите симулятор (например, iPhone 15)
3. Нажмите Cmd+R или кнопку Run

## 🎨 Следующие шаги: Создание UI

После успешного запуска базового приложения, создайте SwiftUI экраны:

### 1. LoginView

```swift
import SwiftUI

struct LoginView: View {
    @State private var email = ""
    @State private var password = ""

    var body: some View {
        VStack(spacing: 20) {
            Text("QuizMate")
                .font(.largeTitle)
                .fontWeight(.bold)

            TextField("Email", text: $email)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .autocapitalization(.none)

            SecureField("Password", text: $password)
                .textFieldStyle(RoundedBorderTextFieldStyle())

            Button("Sign In") {
                // viewModel.signIn(email: email, password: password)
            }
            .buttonStyle(.borderedProminent)
        }
        .padding()
    }
}
```

### 2. Интеграция с KMP ViewModel

```swift
import SwiftUI
import auth_presentation

class LoginViewModelWrapper: ObservableObject {
    let viewModel: AuthLoginViewModel

    init() {
        // Получить ViewModel через Koin
        // self.viewModel = KoinKt.getKoin().get(objCClass: AuthLoginViewModel.self) as! AuthLoginViewModel
    }

    @Published var state: AuthState = AuthState.Loading()

    func observeState() {
        // Подписаться на Flow из KMP
        // viewModel.authStateModel.collect { [weak self] newState in
        //     self?.state = newState
        // }
    }
}
```

## 🐛 Устранение проблем

### Проблема: "Framework not found"

**Решение**:
```bash
./gradlew clean
./gradlew linkDebugFrameworkIosSimulatorArm64
# Затем в Xcode: Product → Clean Build Folder (Cmd+Shift+K)
```

### Проблема: "No such module 'auth_domain'"

**Причины**:
1. Framework не собран
2. Framework Search Paths неправильно настроены
3. Framework не добавлен в "Frameworks, Libraries, and Embedded Content"

**Решение**: Повторите Шаги 5.4 и 5.5

### Проблема: CocoaPods ошибки

```bash
# Обновите CocoaPods
sudo gem install cocoapods
pod repo update

# Очистите кеш
pod cache clean --all
cd iosApp && pod install --repo-update
```

### Проблема: Firebase initialization failed

**Проверьте**:
1. `GoogleService-Info.plist` правильно добавлен в Xcode (должен быть синий, не белый)
2. Bundle ID совпадает с Firebase Console
3. `FirebaseApp.configure()` вызывается в `init()`

### Проблема: Google Sign-In не работает

1. Проверьте URL Schemes в Info.plist
2. Убедитесь, что OAuth Client ID настроен в Firebase Console
3. Проверьте Bundle ID

## 📊 Архитектура iOS приложения

```
iOS App (SwiftUI)
    ↓
KMP ViewModels (Compose Multiplatform)
    ↓
KMP Use Cases (Domain Layer)
    ↓
KMP Repositories (Data Layer)
    ↓
Firebase SDK (GitLive KMP wrapper)
```

**Преимущества**:
- ✅ Бизнес-логика общая для Android и iOS
- ✅ Единый источник истины (Single Source of Truth)
- ✅ MVI pattern работает на обеих платформах
- ✅ Firebase код переиспользуется
- ✅ Меньше дублирования кода

## 📚 Полезные ресурсы

- [Kotlin Multiplatform Docs](https://kotlinlang.org/docs/multiplatform.html)
- [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)
- [GitLive Firebase SDK](https://github.com/GitLiveApp/firebase-kotlin-sdk)
- [Koin Multiplatform](https://insert-koin.io/docs/reference/koin-mp/kmp)
- [SwiftUI Documentation](https://developer.apple.com/documentation/swiftui/)

## ✅ Чеклист готовности

- [ ] Xcode 15+ установлен
- [ ] CocoaPods установлен
- [ ] Firebase iOS app создан
- [ ] GoogleService-Info.plist скачан и добавлен
- [ ] KMP фреймворки собраны
- [ ] Xcode проект создан
- [ ] CocoaPods зависимости установлены
- [ ] Build Phases настроены
- [ ] Framework Search Paths настроены
- [ ] Приложение запускается на симуляторе
- [ ] Firebase инициализируется без ошибок

---

**Готовы начать разработку iOS UI!** 🎉

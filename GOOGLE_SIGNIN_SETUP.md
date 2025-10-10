# Налаштування Google Sign-In для QuizMate

Цей документ описує, як налаштувати Google Sign-In для аутентифікації в додатку QuizMate.

## Передумови

1. Проект вже налаштований з Firebase (файл `google-services.json` присутній в `app/`)
2. Gradle зависимости вже додані:
   - `firebase-auth`
   - `play-services-auth`

## Крок 1: Firebase Console Setup

### 1.1. Увімкніть Google Sign-In в Firebase

1. Відкрийте [Firebase Console](https://console.firebase.google.com/)
2. Виберіть ваш проект QuizMate
3. Перейдіть до **Authentication** → **Sign-in method**
4. Натисніть на **Google** в списку провайдерів
5. Увімкніть перемикач **Enable**
6. Вкажіть **Project support email**
7. Натисніть **Save**

### 1.2. Налаштуйте SHA-1 Fingerprints

**📄 Детальна інструкція з отримання та додавання SHA-1 fingerprints для debug та release версій:**

👉 **Див. файл [`FIREBASE_SHA1_SETUP.md`](./FIREBASE_SHA1_SETUP.md)**

Цей файл містить:
- Як отримати SHA-1 для debug версії (через Gradle або keytool)
- Як створити release keystore
- Як отримати SHA-1 для release версії
- Як додати обидва fingerprints в Firebase Console
- Як налаштувати signing configs в проекті
- Вирішення поширених проблем

**Коротко:**
```bash
# Отримати SHA-1 для debug і release:
./gradlew signingReport

# Додайте обидва SHA-1 в Firebase Console → Project Settings → Your apps → SHA certificate fingerprints
```

**Важливо**: Після додавання SHA-1 завантажте оновлений `google-services.json` та замініть файл в `app/google-services.json`

## Крок 2: Отримайте Web Client ID

1. В Firebase Console → **Project Settings** → **General**
2. Прокрутіть до розділу **Your apps**
3. Виберіть **Web app** (якщо немає, створіть його)
4. Скопіюйте **Web client ID** (формат: `XXXXXXXX-XXXXXXXX.apps.googleusercontent.com`)

## Крок 3: Додайте Web Client ID в код

Є два способи зберігання Web Client ID:

### Варіант A: Strings.xml (простіший, але менш безпечний)

Додайте в `app/src/main/res/values/strings.xml`:
```xml
<string name="default_web_client_id">ВАШ_WEB_CLIENT_ID</string>
```

### Варіант B: BuildConfig (рекомендовано)

1. Відкрийте `app/build.gradle.kts`
2. Додайте в блок `android { defaultConfig { } }`:
```kotlin
android {
    defaultConfig {
        buildConfigField("String", "WEB_CLIENT_ID", "\"ВАШ_WEB_CLIENT_ID\"")
    }
    buildFeatures {
        buildConfig = true
    }
}
```

3. Використовуйте в коді: `BuildConfig.WEB_CLIENT_ID`

## Крок 4: Реалізація Google Sign-In в Activity

### 4.1. Налаштування в MainActivity

Створіть `ActivityResultLauncher` в `MainActivity.kt`:

```kotlin
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var googleSignInLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ініціалізуйте launcher перед setContent
        setupGoogleSignIn()

        enableEdgeToEdge()
        setContent {
            // ... ваш UI код
        }
    }

    private fun setupGoogleSignIn() {
        googleSignInLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                handleGoogleSignInResult(result.data)
            }
        }
    }

    private fun handleGoogleSignInResult(data: Intent?) {
        try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            val idToken = account.idToken

            if (idToken != null) {
                // Передайте idToken в ViewModel
                // Це буде залежати від вашої навігації
                // Наприклад, через SharedViewModel або Event Bus
            }
        } catch (e: ApiException) {
            Log.e("MainActivity", "Google Sign-In failed", e)
            // Покажіть помилку користувачу
        }
    }

    fun startGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // або BuildConfig.WEB_CLIENT_ID
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        googleSignInLauncher.launch(googleSignInClient.signInIntent)
    }
}
```

### 4.2. Підключення UI до Google Sign-In

Модифікуйте LoginScreen, щоб передавати клік в Activity:

```kotlin
@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit,
    onGoogleSignInClick: () -> Unit, // Новий параметр
    viewModel: AuthLoginViewModel = hiltViewModel()
) {
    val state by viewModel.authStateModel.collectAsStateWithLifecycle()

    // ... UI код

    OutlinedButton(
        onClick = onGoogleSignInClick, // Викликає MainActivity.startGoogleSignIn()
        // ... інші параметри
    ) {
        // Google button UI
    }
}
```

І в MainActivity NavHost:

```kotlin
composable("login") {
    LoginScreen(
        onNavigateToRegister = { navController.navigate("register") },
        onNavigateToHome = {
            navController.navigate("main") {
                popUpTo("login") { inclusive = true }
            }
        },
        onGoogleSignInClick = { startGoogleSignIn() }
    )
}
```

## Крок 5: Тестування

### Debug Build
1. Переконайтеся, що SHA-1 debug keystore додано до Firebase
2. Збудуйте і запустіть debug версію
3. Натисніть "Увійти через Google"
4. Виберіть Google акаунт
5. Перевірте, що авторизація пройшла успішно

### Release Build
1. Додайте SHA-1 release keystore до Firebase
2. Завантажіть оновлений `google-services.json`
3. Збудуйте release APK
4. Встановіть і протестуйте

## Поширені проблеми

### Помилка: "Developer Error" або "API_NOT_ENABLED"
- Переконайтеся, що SHA-1 fingerprint додано в Firebase
- Завантажіть свіжий `google-services.json`
- Перевірте, що Google Sign-In увімкнено в Firebase Console

### Помилка: "INVALID_CLIENT"
- Переконайтеся, що використовується правильний Web Client ID
- Web Client ID має бути від Firebase проекту (не Google Cloud Console)

### Помилка: idToken = null
- Переконайтеся, що викликаєте `.requestIdToken()` з правильним Web Client ID
- Перевірте, що `google-services.json` актуальний

## Додаткова інформація

- [Firebase Authentication Documentation](https://firebase.google.com/docs/auth/android/google-signin)
- [Google Sign-In for Android](https://developers.google.com/identity/sign-in/android/start)

## Архітектура коду

Поточна реалізація в проекті:

```
feature:auth/
├── domain/
│   ├── usecase/
│   │   └── AuthSignInWithGoogleUseCase.kt  # Use case для Google Sign-In
│   └── repository/
│       └── AuthRepository.kt                # Інтерфейс репозиторію
├── data/
│   ├── remote/
│   │   └── FirebaseAuthDataSource.kt       # signInWithGoogle(idToken)
│   └── repository/
│       └── AuthRepositoryImpl.kt            # Реалізація репозиторію
└── presentation/
    └── ui/
        ├── login/
        │   └── AuthLoginViewModel.kt        # handleGoogleSignIn(idToken)
        └── register/
            └── AuthRegisterViewModel.kt     # handleGoogleSignIn(idToken)
```

ViewModel вже має метод `handleGoogleSignIn(idToken)`, який приймає Google ID Token і виконує аутентифікацію через Firebase.

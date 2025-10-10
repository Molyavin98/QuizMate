# Додавання SHA-1 Fingerprints для Debug та Release версій

## Крок 1: Отримання SHA-1 для Debug версії

### Варіант A: Через Gradle (найпростіший)

1. Відкрийте термінал в корені проекту QuizMate
2. Виконайте команду:

```bash
./gradlew signingReport
```

3. Знайдіть в output розділ **Variant: debug**, скопіюйте SHA-1:

```
Variant: debug
Config: debug
Store: /Users/molyavin/.android/debug.keystore
Alias: AndroidDebugKey
MD5: XX:XX:XX:XX:XX:XX:XX:XX:XX:XX:XX:XX:XX:XX:XX:XX
SHA1: AA:BB:CC:DD:EE:FF:00:11:22:33:44:55:66:77:88:99:AA:BB:CC:DD  <-- Це потрібно
SHA-256: ...
```

### Варіант B: Через keytool

```bash
keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android
```

Знайдіть рядок з SHA1:
```
Certificate fingerprints:
SHA1: AA:BB:CC:DD:EE:FF:00:11:22:33:44:55:66:77:88:99:AA:BB:CC:DD
```

## Крок 2: Отримання SHA-1 для Release версії

### Якщо у вас ще немає release keystore:

1. Створіть release keystore (виконайте в корені проекту):

```bash
keytool -genkey -v -keystore release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias quizmate-release
```

2. Введіть дані:
   - **Keystore password**: Придумайте і запам'ятайте пароль (наприклад: MySecurePass123)
   - **Key password**: Можна використати такий самий
   - **CN** (First and Last name): QuizMate
   - **OU** (Organizational Unit): Development
   - **O** (Organization): QuizMate
   - **L** (City): Kyiv
   - **ST** (State): Ukraine
   - **C** (Country): UA

3. Keystore буде створено: `release-key.jks`

**⚠️ ВАЖЛИВО**:
- Збережіть файл `release-key.jks` в безпечному місці
- Запам'ятайте паролі (краще запишіть в password manager)
- Додайте `release-key.jks` в `.gitignore`

### Отримання SHA-1 з release keystore:

```bash
keytool -list -v -keystore release-key.jks -alias quizmate-release
```

Введіть пароль keystore і скопіюйте SHA1.

## Крок 3: Додавання SHA-1 в Firebase Console

### 3.1. Відкрийте Firebase Console

1. Перейдіть на https://console.firebase.google.com/
2. Виберіть ваш проект **QuizMate**

### 3.2. Додайте Debug SHA-1

1. Натисніть на іконку **⚙️ (Settings)** → **Project settings**
2. Прокрутіть до розділу **Your apps**
3. Знайдіть ваш Android app (package: `com.molyavin.quizmate`)
4. Прокрутіть до розділу **SHA certificate fingerprints**
5. Натисніть **Add fingerprint**
6. Вставте SHA-1 для **debug** версії
7. Натисніть **Save**

### 3.3. Додайте Release SHA-1

1. В тому ж розділі **SHA certificate fingerprints**
2. Натисніть **Add fingerprint** ще раз
3. Вставте SHA-1 для **release** версії
4. Натисніть **Save**

**Результат**: Тепер у вас має бути 2 fingerprints (debug і release)

### 3.4. Завантажте оновлений google-services.json

1. В тому ж розділі **Your apps** натисніть кнопку **Download google-services.json**
2. Замініть старий файл:
   ```bash
   cp ~/Downloads/google-services.json /Users/molyavin/AndroidStudioProjects/QuizMate/app/
   ```

## Крок 4: Налаштування Release Build в проекті

### 4.1. Створіть keystore.properties

1. Створіть файл `keystore.properties` в корені проекту:

```bash
touch keystore.properties
```

2. Додайте в `keystore.properties` (замініть на свої дані):

```properties
storeFile=release-key.jks
storePassword=MySecurePass123
keyAlias=quizmate-release
keyPassword=MySecurePass123
```

3. Додайте в `.gitignore`:

```
keystore.properties
*.jks
*.keystore
```

### 4.2. Оновіть app/build.gradle.kts

Додайте перед блоком `android {}`:

```kotlin
// Завантаження keystore properties
val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
if (keystorePropertiesFile.exists()) {
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))
}

android {
    // ... існуючий код ...

    signingConfigs {
        create("release") {
            if (keystorePropertiesFile.exists()) {
                storeFile = file(keystoreProperties["storeFile"] as String)
                storePassword = keystoreProperties["storePassword"] as String
                keyAlias = keystoreProperties["keyAlias"] as String
                keyPassword = keystoreProperties["keyPassword"] as String
            }
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
```

Не забудьте додати імпорт на початку файлу:

```kotlin
import java.util.Properties
import java.io.FileInputStream
```

## Крок 5: Перевірка налаштувань

### 5.1. Перевірте debug build:

```bash
./gradlew assembleDebug
```

Встановіть APK на пристрій і спробуйте Google Sign-In.

### 5.2. Перевірте release build:

```bash
./gradlew assembleRelease
```

Release APK буде в `app/build/outputs/apk/release/app-release.apk`

## Крок 6: Тестування Google Sign-In

### Debug версія:
1. Збудуйте debug APK: `./gradlew assembleDebug`
2. Встановіть на пристрій/емулятор
3. Натисніть "Увійти через Google"
4. Якщо все налаштовано правильно, відкриється екран вибору Google акаунту

### Release версія:
1. Збудуйте release APK: `./gradlew assembleRelease`
2. Встановіть APK вручну на пристрій
3. Протестуйте Google Sign-In

## Поширені помилки

### Помилка: "Developer Error" або код 10

**Причина**: SHA-1 fingerprint не додано або невірний

**Рішення**:
1. Переконайтеся, що SHA-1 точно відповідає вашому keystore
2. Перевірте, що завантажили новий `google-services.json`
3. Почистіть проект: `./gradlew clean`
4. Повністю видаліть і перевстановіть додаток

### Помилка: "API not enabled"

**Причина**: Google Sign-In не увімкнено в Firebase

**Рішення**:
1. Firebase Console → Authentication → Sign-in method
2. Увімкніть Google провайдер
3. Збережіть зміни

### SHA-1 змінився після створення нового keystore

**Рішення**:
1. Видаліть старий SHA-1 з Firebase Console
2. Додайте новий SHA-1
3. Завантажте новий `google-services.json`

## Корисні команди

### Переглянути всі signing configs:
```bash
./gradlew signingReport
```

### Очистити проект:
```bash
./gradlew clean
```

### Збудувати debug APK:
```bash
./gradlew assembleDebug
```

### Збудувати release APK:
```bash
./gradlew assembleRelease
```

### Встановити debug на підключений пристрій:
```bash
./gradlew installDebug
```

## Безпека

**НЕ ПУБЛІКУЙТЕ В GIT:**
- `release-key.jks`
- `keystore.properties`
- Паролі від keystore

**Додайте в .gitignore:**
```gitignore
# Keystore files
*.jks
*.keystore
keystore.properties

# Local configuration
local.properties
```

## Що далі?

Після налаштування SHA-1 fingerprints:
1. Перейдіть до файлу `GOOGLE_SIGNIN_SETUP.md`
2. Виконайте кроки з отримання Web Client ID
3. Інтегруйте Google Sign-In в MainActivity

---

**Примітка**: Якщо ви плануєте публікувати додаток в Google Play, вам потрібно буде також додати SHA-1 fingerprint від Google Play signing key (він генерується автоматично Google Play Console).

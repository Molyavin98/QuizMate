plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "AuthDomain"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // Kotlin stdlib и Coroutines
            implementation(libs.kotlinx.coroutines.core)

            // Koin DI (KMP)
            api(libs.koin.core)
        }

        androidMain.dependencies {
            // Android-специфичные зависимости (если нужны)
        }

        iosMain.dependencies {
            // iOS-специфичные зависимости (если нужны)
        }
    }
}

android {
    namespace = "com.molyavin.quizmate.feature.auth.domain"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

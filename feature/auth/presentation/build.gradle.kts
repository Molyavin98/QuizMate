plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.compose")
    id("org.jetbrains.compose") version "1.7.1"
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
            baseName = "AuthPresentation"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // Domain модуль
            implementation(project(":feature:auth:domain"))
            implementation(project(":core"))

            // Compose Multiplatform
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            // Coroutines
            implementation(libs.kotlinx.coroutines.core)

            // Koin DI (KMP)
            implementation(libs.koin.core)
        }

        androidMain.dependencies {
            // Koin Android
            implementation(libs.koin.android)
        }

        iosMain.dependencies {
            // iOS специфичные зависимости
        }
    }
}

android {
    namespace = "com.molyavin.quizmate.feature.auth.presentation"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

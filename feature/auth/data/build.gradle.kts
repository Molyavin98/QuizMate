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
            baseName = "AuthData"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // Domain модуль
            implementation(project(":feature:auth:domain"))

            // Coroutines
            implementation(libs.kotlinx.coroutines.core)

            // GitLive Firebase (KMP)
            implementation(libs.gitlive.firebase.auth)
            implementation(libs.gitlive.firebase.common)

            // Koin DI (KMP)
            implementation(libs.koin.core)
        }

        androidMain.dependencies {
            // Android-специфичные Firebase зависимости (для GitLive)
            implementation("com.google.firebase:firebase-auth:23.1.0")

            // Google Sign-In (Android-only)
            implementation("com.google.android.gms:play-services-auth:21.2.0")

            // Koin Android
            implementation(libs.koin.android)
        }

        iosMain.dependencies {
            // iOS Firebase SDK используется через GitLive автоматически
        }
    }
}

android {
    namespace = "com.molyavin.quizmate.feature.auth.data"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

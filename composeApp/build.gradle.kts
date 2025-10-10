plugins {
    kotlin("multiplatform")
    id("com.android.application")
    kotlin("plugin.compose")
    id("org.jetbrains.compose") version "1.7.1"
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // Compose Multiplatform
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            // Navigation
            implementation(libs.androidx.navigation.compose)

            // Lifecycle
            implementation("androidx.lifecycle:lifecycle-viewmodel:2.8.7")
            implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")

            // Koin
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            // Coroutines
            implementation(libs.kotlinx.coroutines.core)

            // Project modules
            implementation(project(":core"))
            implementation(project(":feature:auth:domain"))
            implementation(project(":feature:auth:data"))
            implementation(project(":feature:auth:presentation"))
            // Добавьте остальные модули когда они будут готовы
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)

            // Firebase Android - использую прямые версии вместо BOM для KMP
            implementation("com.google.firebase:firebase-auth:23.1.0")
            implementation("com.google.firebase:firebase-firestore:25.1.1")

            // Google Sign-In
            implementation("com.google.android.gms:play-services-auth:21.2.0")
        }

        iosMain.dependencies {
            // iOS specific dependencies
        }
    }
}

android {
    namespace = "com.molyavin.quizmate"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.molyavin.quizmate"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

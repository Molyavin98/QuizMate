plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.google.services)
}

apply(from = "$rootDir/gradle/app-module.gradle")

android {
    namespace = "com.molyavin.quizmate"

    defaultConfig {
        applicationId = "com.molyavin.quizmate"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    // Feature modules
    implementation(project(":core"))
    implementation(project(":feature:home"))
    implementation(project(":feature:vocabulary"))
    implementation(project(":feature:quiz"))
    implementation(project(":feature:flashcards"))
    implementation(project(":feature:splash"))
    implementation(project(":feature:favorites"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:settings"))

    // App-specific dependencies
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.timber)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.analytics)

    // Google Sign-In
    implementation(libs.play.services.auth)
}
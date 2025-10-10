plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

apply(from = "$rootDir/gradle/feature-module.gradle")

android {
    namespace = "com.molyavin.quizmate.splash"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":feature:vocabulary"))

    // Splash Screen API
    implementation(libs.androidx.core.splashscreen)

    // Timber for logging
    implementation(libs.timber)
}

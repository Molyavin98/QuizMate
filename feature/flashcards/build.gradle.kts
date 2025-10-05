plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

apply(from = "$rootDir/gradle/feature-module.gradle")

android {
    namespace = "com.molyavin.quizmate.flashcards"
}

dependencies {
    implementation(project(":feature:vocabulary"))
    implementation(libs.coil.compose)
}

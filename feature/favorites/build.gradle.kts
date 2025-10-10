plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

apply(from = "$rootDir/gradle/feature-module.gradle")

android {
    namespace = "com.molyavin.quizmate.favorites"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":feature:vocabulary"))
    implementation(libs.coil.compose)
}

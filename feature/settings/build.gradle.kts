plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

apply(from = "$rootDir/gradle/feature-module.gradle")

android {
    namespace = "com.molyavin.quizmate.settings"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":feature:auth"))

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
}

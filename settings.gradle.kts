pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "QuizMate"
include(":app")
include(":core")
include(":feature:home")
include(":feature:vocabulary")
include(":feature:quiz")
include(":feature:flashcards")
include(":feature:splash")
include(":feature:favorites")
include(":feature:auth")
include(":feature:settings")
 
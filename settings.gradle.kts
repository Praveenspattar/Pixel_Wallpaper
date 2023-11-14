pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        /*maven { url "https://www.jitpack.io" }*/
        jcenter()
    }
}

rootProject.name = "Wallapers_App"
include(":app")

pluginManagement {
    val kotlinVersion: String by settings
    val composeVersion: String by settings

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://jitpack.io")
    }

    plugins {
        id("org.jetbrains.kotlin.multiplatform") version kotlinVersion
        id("org.jetbrains.compose") version composeVersion
    }
}
rootProject.name = "kliz"
include("kliz-common")
include("kliz-compose")

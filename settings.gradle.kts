pluginManagement {
    val kotlinVersion: String by settings
    val composeVersion: String by settings

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://jitpack.io")
        maven("https://packages.jetbrains.team/maven/p/skija/maven")
    }

    plugins {
        id("org.jetbrains.kotlin.multiplatform") version kotlinVersion
        id("org.jetbrains.compose") version composeVersion
        id("com.android.application") version "7.3.0"
        id("org.jetbrains.kotlin.android") version "1.7.0"
        id("com.android.library") version "7.3.0"
    }
}
rootProject.name = "kliz"
include("kliz-common")
include("kliz-compose")
include("example-desktop")
include(":example-android")
include("kliz-android")
include("kliz-cmp")

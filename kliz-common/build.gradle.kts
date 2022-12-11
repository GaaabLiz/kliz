
val composeVersion: String by properties
val coroutinesVersion: String by properties
val atomicfuVersion: String by properties

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("maven-publish")
}

group = project.properties["project.group"].toString()
version = project.properties["project.version"].toString()

kotlin {
    android {
        publishLibraryVariants("release")
    }
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = project.properties["jvm.target"].toString()
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.compose.runtime:runtime:${composeVersion}")
                implementation("org.jetbrains.compose.foundation:foundation:${composeVersion}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")
                implementation("org.jetbrains.kotlinx:atomicfu:${atomicfuVersion}")
                implementation("ch.qos.logback:logback-classic:1.4.5")
            }
        }
        val androidMain by getting {
            dependencies {
                //implementation("androidx.activity:activity-compose:1.6.0")
                implementation("androidx.compose.ui:ui:$composeVersion")
            }
        }
    }
}

android {
    lintOptions {
        baseline(file("lint-baseline.xml"))
    }
    compileSdk = project.properties["android.targetSdk"].toString().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = project.properties["android.minSdk"].toString().toInt()
        targetSdk = project.properties["android.targetSdk"].toString().toInt()
    }
}
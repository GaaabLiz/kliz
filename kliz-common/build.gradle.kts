
val composeVersion: String by properties
val coroutinesVersion: String by properties

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("maven-publish")
}

group = project.properties["project.group"].toString()
version = project.properties["project.common.version"].toString()

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
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")
                implementation("org.jetbrains.kotlinx:atomicfu:0.18.4")
                implementation("ch.qos.logback:logback-classic:1.4.5")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(compose.ui)
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
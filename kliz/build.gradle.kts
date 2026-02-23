import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.konan.target.HostManager

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
}

group = "io.github.gaaabliz"
version = "2.1.2"

kotlin {
    jvm()
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    //if (HostManager.hostIsMac) {
      //  iosSimulatorArm64()
      //  iosX64()
    //}

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.apache.commons)
                implementation(libs.gson)
                implementation(libs.json)
                implementation(libs.kotlinx.coroutines.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.timber)
                implementation(libs.androidx.core)
                implementation(libs.androidx.activity.ktx)
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.lifecycle.service)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.logback.classic)
            }
        }
    }
}

android {
    namespace = "io.github.gaaabliz.kliz"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(group.toString(), "kliz", version.toString())

    pom {
        name = "Kliz"
        description = "My personal Kotlin Multiplatform library."
        inceptionYear = "2025"
        url = "https://github.com/GaaabLiz/kliz"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "Gaaabliz"
                name = "Gabliz"
                url = "https://github.com/GaaabLiz"
            }
        }
        scm {
            url = "https://github.com/GaaabLiz/kliz"
            connection = "scm:git:git://github.com/GaaabLiz/kliz.git"
            developerConnection = "scm:git:ssh://github.com:GaaabLiz/kliz.git"
        }
    }
}

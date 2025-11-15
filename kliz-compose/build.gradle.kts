import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.konan.target.HostManager

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlin.compose)
}

group = "io.github.gaaabliz"
version = "2.1.0"

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
                api(project(":kliz"))
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.ui)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                api(compose.components.resources)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "io.github.gaaabliz.kliz.compose"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(group.toString(), "kliz-compose", version.toString())

    pom {
        name = "Kliz-compose"
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

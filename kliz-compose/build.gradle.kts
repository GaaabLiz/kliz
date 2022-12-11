import com.android.build.api.dsl.LintOptions


plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("maven-publish")
}

group = project.properties["project.group"].toString()
version = project.properties["project.compose.version"].toString()

kotlin {
    explicitApi()
    android()
    android {
        publishLibraryVariants("release")
    }
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = project.properties["jvm.target"].toString()
        }
    }
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                implementation(compose.animation)
            }
        }
        named("androidMain") {
            //kotlin.srcDirs("src/jvmMain/kotlin")
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                implementation(compose.animation)
                api("androidx.appcompat:appcompat:1.5.1")
                api("androidx.core:core-ktx:1.8.0")
            }
        }
        named("desktopMain") {
            dependencies {
                implementation(compose.preview)
                implementation(compose.material)
                implementation(compose.desktop.currentOs)
                implementation(compose.desktop.common)
            }
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn"
        )
        jvmTarget = project.properties["jvm.target"].toString()
    }
}

android {
    lintOptions {
        baseline("lint-baseline.xml")
        isAbortOnError = false
        isCheckReleaseBuilds = false
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

afterEvaluate {
    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = JavaVersion.VERSION_11.toString()
        targetCompatibility = JavaVersion.VERSION_11.toString()
    }
}
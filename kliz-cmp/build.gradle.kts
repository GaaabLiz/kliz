import com.android.build.api.dsl.LintOptions


plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("maven-publish")
}

group = project.properties["project.group"].toString()
version = project.properties["project.version"].toString()

repositories {
    maven("https://packages.jetbrains.team/maven/p/skija/maven")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

kotlin {
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
                implementation(compose.materialIconsExtended)
                implementation("ch.qos.logback:logback-classic:1.4.5")
            }
        }
        named("androidMain") {
            dependencies {
                implementation(compose.preview)
                api("androidx.appcompat:appcompat:1.5.1")
                api("androidx.core:core-ktx:1.9.0")
                implementation("androidx.compose.ui:ui-text-google-fonts:1.3.2")
                implementation("com.jakewharton.timber:timber:5.0.1")
            }
        }
        named("desktopMain") {
            dependencies {
                implementation(compose.preview)
                implementation(compose.desktop.currentOs)
                implementation(compose.desktop.common)
                api("moe.tlaster:precompose:1.3.13")
                api("org.jetbrains.skija:skija-windows:0.93.6")
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
    packagingOptions {
        resources.excludes.add("META-INF/*")
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
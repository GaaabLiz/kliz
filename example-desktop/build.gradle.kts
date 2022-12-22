import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = project.properties["project.group"].toString()
version = project.properties["project.version"].toString()

repositories {
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://packages.jetbrains.team/maven/p/skija/maven")
    maven("https://plugins.gradle.org/m2/")
    maven("https://jitpack.io")
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(project(":kliz-compose"))
    implementation(project(":kliz-common"))
    implementation("cafe.adriel.bonsai:bonsai-core:1.2.0")
    implementation("cafe.adriel.bonsai:bonsai-file-system:1.2.0")
    implementation("cafe.adriel.bonsai:bonsai-json:1.2.0")
    api("org.jetbrains.skija:skija-windows:0.93.6")
    runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:1.6.4")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
    val args = listOf("-opt-in=kotlin.RequiresOptIn", "-Xskip-prerelease-check")
    kotlinOptions.freeCompilerArgs += args
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "TS4SM"
            packageVersion = "1.0.0"
        }
    }
}
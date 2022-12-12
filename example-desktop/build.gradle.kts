import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = project.properties["project.group"].toString()
version = project.properties["project.example.desktop.version"].toString()

repositories {
    maven("https://packages.jetbrains.team/maven/p/skija/maven")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
}


kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(project(":kliz-common"))
                implementation(project(":kliz-compose"))
                //implementation("it.gabliz:kliz-desktop:1.0.1")
                //implementation("it.gabliz:kliz-common:0.0.1")
                //implementation("it.gabliz:kliz-compose:1.0.3")
                //implementation("io.github.koalaplot:koalaplot-core:0.1.0")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "tstcomposedesktop"
            packageVersion = "1.0.0"
        }
    }
}
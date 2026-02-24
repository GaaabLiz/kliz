pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "kliz"
include(":kliz")
include(":kliz-compose")
include(":kliz-firebase")
include(":kliz-agps")

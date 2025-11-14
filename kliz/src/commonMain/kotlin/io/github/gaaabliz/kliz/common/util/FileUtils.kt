package io.github.gaaabliz.kliz.common.util

object FileUtils {

    fun readFromResources(fileName: String): String {
        return FileUtils::class.java.getResource(fileName)?.readText() ?: ""
    }
}
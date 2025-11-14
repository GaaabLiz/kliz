package io.github.gaaabliz.kliz.common.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL

/**
 * A utility object for performing network-related operations.
 */
object NetworkUtils {

    /**
     * Reads the entire content from a given URL and returns it as a single string.
     * This is a blocking network operation.
     *
     * @param url The [URL] to read from.
     * @return The content of the URL as a [String].
     * @throws java.io.IOException if an I/O error occurs.
     */
    fun getStringFromUrl(url: URL): String {
        val sb = StringBuilder()
        var line: String?
        val `in`: InputStream = url.openStream()
        try {
            val reader = BufferedReader(InputStreamReader(`in`))
            while (reader.readLine().also { line = it } != null) {
                sb.append(line).append(System.lineSeparator())
            }
        } finally {
            `in`.close()
        }
        return sb.toString()
    }

    /**
     * Asynchronously downloads a file from a URL and returns its [InputStream].
     * This function uses [Dispatchers.IO] to perform the network operation off the main thread.
     *
     * @param fileUrl The URL of the file to download as a [String].
     * @return An [InputStream] of the file content, or `null` if an exception occurs (e.g., invalid URL, connection error).
     */
    suspend fun downloadFileFromWeb(fileUrl: String): InputStream? {
        return try {
            val url = URL(fileUrl)
            return withContext(Dispatchers.IO) {
                val connection = url.openConnection()
                connection.connect()
                connection.getInputStream()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
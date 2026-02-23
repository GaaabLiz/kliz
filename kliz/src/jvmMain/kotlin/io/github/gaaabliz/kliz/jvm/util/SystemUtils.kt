package io.github.gaaabliz.kliz.jvm.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import java.awt.Desktop
import java.io.File
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.net.URLConnection


object SystemUtils {

    fun checkForSafeClose(
        operationActive : Boolean,
        logger : Logger? = null,
        close: () -> Unit
    ) {
        if(operationActive) {
            logger?.warn("Operation in progress, cannot close the software!")
            return
        } else {
            close()
        }
    }

    private fun openSystemDirectory(f : File) = Desktop.getDesktop().open(f)
    fun getUserDocumentFolder() : String = System.getProperty("user.home") + "\\Documents"

    fun openAndCheckSystemDirectory(f : File, logger: Logger? = null, onError : ( (String) -> Unit )? = null) {
        try {
            logger?.debug("Opening system directory: ${f.absolutePath}...")
            openSystemDirectory(f)
        }catch (e : Exception) {
            logger?.error("An error occurred while trying to open system directory: ${f.absolutePath}. Error: ${e.message}.")
            if(onError != null) onError(e.message ?: "Unknown error occurred.")
        }
    }

    fun isInternetAvailable(logger: Logger? = null) : Boolean {
        return try {
            val url = URL("http://www.google.com")
            val connection: URLConnection = url.openConnection()
            connection.connect()
            logger?.debug("Internet connection available!")
            true
        } catch (e: MalformedURLException) {
            logger?.warn("Internet connection not available! Error: ${e.message}")
            false
        } catch (e: IOException) {
            logger?.warn("Internet connection not available! Error: ${e.message}")
            false
        }
    }

    suspend fun isInternetAvaiablePing(logger: Logger? = null) : Boolean {
        val process = withContext(Dispatchers.IO) {
            Runtime.getRuntime().exec("ping www.geeksforgeeks.org")
        }
        val x = withContext(Dispatchers.IO) {
            process.waitFor()
        }
        return if (x == 0) {
            logger?.debug("Internet connection available!")
            true
        } else {
            logger?.warn("Internet connection not available!")
            false
        }
    }



}
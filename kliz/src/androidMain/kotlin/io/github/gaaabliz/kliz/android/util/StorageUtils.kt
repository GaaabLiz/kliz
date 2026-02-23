@file:Suppress("DEPRECATION")

package io.github.gaaabliz.kliz.android.util

import android.content.Context
import android.os.Environment
import android.os.StatFs
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * Classe contenente funzioni di utilità per quanto riguarda la memoria.
 *
 * @author Gabriele Lizzos
 * @author Farrauto Loris
 */
class UtilityStorage {

    companion object {


        /**
         * Metodo per scrivere in modalità append una stringa di
         * @param context Context
         * @param fileName String
         * @param fileArray ArrayList<String>
         */
        fun writeInternalArrayFile(context: Context, fileName: String, fileArray: ArrayList<String>) {
            val outputStream: FileOutputStream
            try {
                outputStream = context.applicationContext.openFileOutput(fileName, Context.MODE_APPEND)
                for (i in fileArray) {
                    outputStream.write(i.toByteArray())
                }
                outputStream.close()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }

        fun writeInternalFile(context: Context, fileName: String, fileContents: String) {
            context.applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                try {
                    it.write(fileContents.toByteArray())
                }catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

        fun deleteInternalFile(context: Context, fileName: String) {
            val dir: File = context.filesDir
            val file = File(dir, fileName)
            try {
                val deleted = file.delete()
            }catch (e : Exception) {
                e.printStackTrace()
            }
        }

        fun getFreeInternalMemoryStorage():Long {
            var availableSpace = -1L
            try {
                val stat = StatFs(Environment.getDataDirectory().path)
                stat.restat(Environment.getDataDirectory().path)
                availableSpace = stat.availableBlocks.toLong() * stat.blockSize.toLong()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return availableSpace
        }
    }


}
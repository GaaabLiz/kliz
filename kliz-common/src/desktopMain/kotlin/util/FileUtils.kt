package util

import java.awt.FileDialog
import java.awt.Frame
import java.awt.HeadlessException
import java.io.File
import java.io.IOException
import javax.swing.JFileChooser


object FileUtils {

    private fun canReadWrite(f : File) : Boolean = f.canRead() && f.canWrite()
    fun easyTextWrite(fileToWrite : File, textToWrite : String) = fileToWrite.writeText(textToWrite)
    fun easyReadFile(path : File) = path.readText()
    fun check(directory: File) : Boolean = if(!canReadWrite(directory)) throw IOException("Cannot read or write in selected directory.") else true
    fun easyDeleteFile(fileToDelete : File) = fileToDelete.delete()



    @Deprecated(message = "", level = DeprecationLevel.WARNING)
    fun openSwingFolderSelector() : File? {
        try {
            val fileChooser = JFileChooser("/").apply {
                fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
                dialogTitle = "Select a folder"
                approveButtonText = "Select"
                approveButtonToolTipText = "Select current directory as save destination"
            }
            fileChooser.showOpenDialog(null/* OR null */)
            return fileChooser.selectedFile
        }catch (e : IOException) {
            return null
        }catch (e : HeadlessException) {
            return null
        }
    }

    fun openFileAwtFileDialog() : File? {
        val dialog = FileDialog(null as Frame?, "Select TS4SM folder destination")
        dialog.mode = FileDialog.LOAD
        dialog.isMultipleMode = false
        dialog.isVisible = true
        try {
            return File(dialog.directory + "/" + dialog.file)
        }catch (e : NoSuchElementException) {
            return null
        }
    }



    /**
     *
     *
     * @param directory
     * @param createIfNotExist
     * @throws IOException
     */
    fun checkAndCreateDir(
        directory : File,
        createIfNotExist : Boolean = true
    ) {
        try {
            if(!directory.exists() && createIfNotExist) {
                directory.mkdir()
            }
            if(!canReadWrite(directory)) throw IOException("Cannot read/write in selected directory.")
        } catch (e : SecurityException) {
            throw IOException("${e.message}")
        }
    }

    fun checkAndCreateFile(
        file : File,
        createIfNotExist : Boolean = true
    ) {
        try {
            if(!file.exists() && createIfNotExist) {
                if(!file.createNewFile()) throw IOException("Cannot create file \"$file\".")
            }
            if(!canReadWrite(file)) throw IOException("Cannot read/write file $file.")
        } catch (e : SecurityException) {
            throw IOException("${e.message}")
        }
    }

    fun dirExists(dir : File, isDirectory : Boolean = false, isFile : Boolean = false) : Boolean {
        try {
            if(!dir.exists()) return false
            if(dir.exists() && dir.isDirectory && isDirectory) return true
            if(dir.exists() && dir.isFile && isFile) return true
            if(dir.exists() && (dir.isFile != isFile)) throw IOException("Path $dir is not a file.")
            if(dir.exists() && (dir.isDirectory != isDirectory)) throw IOException("Path is not a directory.")
            return false
        } catch (e: SecurityException) {
            throw IOException("${e.message}")
        }
    }
}
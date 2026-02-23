package io.github.gaaabliz.kliz.jvm.util

import org.slf4j.Logger
import java.io.File
import java.io.IOException
import javax.swing.JFileChooser
import javax.swing.UIManager



object SwingHelper {

    fun setSwingNativeSystemUI() = UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    fun getUserDocumentFolder(fileChooser: JFileChooser) : String = fileChooser.fileSystemView.defaultDirectory.absolutePath

    /**
     *
     * NOTE: must be initialized in a variable at the start of the program, and then passed to the function.
     *
     * @return file chooser object
     */
    fun getDefaultSwingFolderChooser(
        title : String = "Select a folder",
        approveBtnText : String = "Select",
    ) : JFileChooser {
        return JFileChooser("/").apply {
            fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
            dialogTitle = title
            approveButtonText = approveBtnText
            approveButtonToolTipText = "Select current directory as destination"
        }
    }

    fun openFolderChooserDialog(
        directoryName : String,
        fileChooser: JFileChooser,
        logger : Logger? = null,
        pathToAdd : String? = null,
        onSuccess : (f : File) -> Unit,
        onError : (e : IOException) -> Unit
    ){
        try {
            /* setting window title */
            logger?.debug("Starting chooser dialog for $directoryName")
            val dialogTitle = fileChooser.dialogTitle + " ($directoryName)"
            fileChooser.dialogTitle = dialogTitle
            /* opening dialog and getting path */
            fileChooser.showOpenDialog(null)
            val f = fileChooser.selectedFile ?: throw IOException("No file selected.")
            logger?.debug("User has selected directory: ${f.absolutePath}. Checking...")
            FileUtils.check(f)
            /* check if needing to add path */
            var finalPath : File = f
            if(pathToAdd != null) {
                logger?.debug("Adding path \"$pathToAdd\" to selected directory.")
                finalPath = File(f.absolutePath + pathToAdd)
                FileUtils.checkAndCreateDir(finalPath)
            }
            /* calling success function */
            logger?.debug("Final path for $directoryName: ${f.absolutePath}.")
            onSuccess(finalPath)
        } catch (e: IOException) {
            logger?.error("Error occurred while selecting directory: ${e.message}")
            onError(e)
        }
    }



}
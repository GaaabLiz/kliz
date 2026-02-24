package io.github.gaaabliz.kliz.compose.jvm.utils

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.*
import org.slf4j.Logger

public object ComposeDesktopUtils {

    @OptIn(ExperimentalComposeUiApi::class)
    public fun handleCtrlAltShiftDShortcut(
        event : KeyEvent,
        logger: Logger? = null,
        onAction : () -> Unit
    ) : Boolean {
        if(
            event.isCtrlPressed &&
            event.isAltPressed &&
            event.isShiftPressed &&
            event.key == Key.D &&
            event.type == KeyEventType.KeyUp
        ) {
            logger?.debug("Ctrl+Alt+Shift+D shortcut pressed")
            onAction()
            return true
        }
        return false
    }

    public fun checkForExit(
        operationActive : Boolean,
        logger: Logger,
        exitApplication : () -> Unit
    ) {
        if(operationActive) {
            logger.warn("Operation in progress, cannot close the software!")
            return
        } else {
            logger.warn("Closing the software...")
            exitApplication()
        }
    }
}
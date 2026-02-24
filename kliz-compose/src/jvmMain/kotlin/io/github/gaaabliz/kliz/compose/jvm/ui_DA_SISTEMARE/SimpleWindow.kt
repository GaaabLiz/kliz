package io.github.gaaabliz.kliz.compose.jvm.ui_DA_SISTEMARE

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import java.awt.Dimension
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent

@Composable
public fun SimpleWindow(
    isWindowOpen : Boolean,
    interactionSourceMain: MutableInteractionSource? = null,
    onCloseClicked : () -> Unit
) {
    val icon = painterResource("logo.png")
    val scrollState = rememberScrollState()

    Window(
        create = {
            ComposeWindow().apply {
                size = Dimension(600, 400)
                minimumSize = Dimension(400, 400)
                addWindowListener(object : WindowAdapter() {
                    override fun windowClosing(e: WindowEvent?) {
                        onCloseClicked()
                    }
                })
            }
        },
        dispose = ComposeWindow::dispose
    ) {

    }
}
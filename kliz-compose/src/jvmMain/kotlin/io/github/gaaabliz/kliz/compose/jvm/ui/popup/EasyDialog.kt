package io.github.gaaabliz.kliz.compose.jvm.ui.popup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeDialog
import androidx.compose.ui.window.Dialog
import java.awt.Dialog
import java.awt.Dimension
import java.awt.GraphicsEnvironment
import java.awt.Toolkit
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent

@Composable
fun SimpleAlertDialog(
    dialogTitle : String,
    dialogMessage : String,
    onOkClicked : () -> Unit
) {
    Dialog(
        create = {
            ComposeDialog(null, Dialog.ModalityType.APPLICATION_MODAL).apply {
                size = Dimension(400, 200)
                isAlwaysOnTop = true
                isResizable = false
                title = dialogTitle
                minimumSize = Dimension(400, 200)
                setLocation(
                    GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice.defaultConfiguration.bounds.width / 2 - 200,
                    GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice.defaultConfiguration.bounds.height / 2 - 100
                )
                iconImages = listOf(Toolkit.getDefaultToolkit().getImage(javaClass.getResource("/logo.png")))
                addWindowListener(object : WindowAdapter() {
                    override fun windowClosing(e: WindowEvent?) {
                        onOkClicked()
                    }
                })
            }
        },
        update = { dialog ->

        },
        dispose = ComposeDialog::dispose
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row {
                Text(dialogMessage)
            }
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = { onOkClicked() }) {
                    Text("Ok")
                }
            }

        }
    }
}
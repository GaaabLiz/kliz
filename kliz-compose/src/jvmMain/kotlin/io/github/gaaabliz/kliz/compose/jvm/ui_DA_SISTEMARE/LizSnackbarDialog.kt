package io.github.gaaabliz.kliz.compose.jvm.ui_DA_SISTEMARE

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeDialog
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import io.github.gaaabliz.kliz.compose.common.model.SnacklizData
import io.github.gaaabliz.kliz.compose.common.model.SnacklizType
import java.awt.Dialog
import java.awt.Dimension
import java.awt.GraphicsEnvironment
import java.awt.Toolkit
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent

@Composable
public fun SnackbarDialog(
    dialogTitle : String,
    dialogMessage : String,
    data : SnacklizData,
    type : SnacklizType.CustomSnackbar,
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
            modifier = Modifier.padding(15.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.weight(weight = 1F, fill = false),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(imageVector = type.leadingIcon, contentDescription = "", tint = type.colorPrimary)
                }
                Spacer(Modifier.width(10.dp))
                Column(
                    modifier = Modifier.weight(weight = 2F, fill = false),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = data.title,
                        color = Color.Black,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Justify
                    )
                    Text(
                        text = data.title,
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Justify
                    )

                }/*
                if(data.type.hasCloseButton) {
                    Spacer(Modifier.width(40.dp))
                    IconButton(onClick = {

                    }) {
                        Icon(tint = data.type.colorPrimary, imageVector = Icons.Filled.Close, contentDescription = "",)
                    }
                }*/
            }
        }
    }
}
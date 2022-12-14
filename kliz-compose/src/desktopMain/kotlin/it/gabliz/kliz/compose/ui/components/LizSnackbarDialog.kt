package it.gabliz.kliz.compose.ui.components

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
import it.gabliz.kliz.compose.model.SnackbarInnerBoxType
import it.gabliz.kliz.compose.model.SnackbarData
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
    data : SnackbarData,
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
                    when(data.type.innerBoxType) {
                        SnackbarInnerBoxType.ICON -> {
                            if(data.type.icon != null)
                                Icon(imageVector = data.type.icon, contentDescription = "", tint = data.type.colorPrimary)
                        }
                        SnackbarInnerBoxType.CIRCULAR_PROGRESS_INDEF -> {
                            CircularProgressIndicator(
                                color = data.type.colorPrimary,
                                modifier = Modifier.size(30.dp),
                                strokeWidth = 3.dp
                            )
                        }
                    }
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
                    if(data.type.hasInnerProgressBar) {
                        Spacer(Modifier.height(10.dp))
                        LinearProgressIndicator(
                            color = data.type.colorPrimary,
                            modifier = Modifier.fillMaxWidth(),
                            backgroundColor = Color(0xFFB0D0FB),
                        )
                    }
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
package io.github.gaaabliz.kliz.compose.android.ui.popup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import io.github.gaaabliz.kliz.compose.common.ui.btn.BtnLiz
import io.github.gaaabliz.kliz.compose.common.ui.utils.MODIFIER_ZERO_WIDTH_HEIGHT
import org.jetbrains.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun AlertDialogYesNo(
    isVisible : Boolean = true,
    title : String = "",
    text : String = "",
    onDismissRequest : () -> Unit = {},
    onYes: () -> Unit = {},
    onNo: () -> Unit = {},

    ) {
    if(isVisible) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(text = title, style = MaterialTheme.typography.h6) },
            text = { Text(text = text, style = MaterialTheme.typography.body1) },
            confirmButton = {
                BtnLiz(text = "Yes", onClick = onYes, buttonModifier = Modifier)
            },
            dismissButton = {
                BtnLiz(text = "No", onClick = onNo, buttonModifier = Modifier)
            },
            shape = RoundedCornerShape(8.dp),
            properties = DialogProperties(dismissOnClickOutside = false),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }

}
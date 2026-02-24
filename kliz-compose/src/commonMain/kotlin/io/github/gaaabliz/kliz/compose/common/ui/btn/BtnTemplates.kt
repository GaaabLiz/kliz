package io.github.gaaabliz.kliz.compose.common.ui.btn

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun BtnDebug(
    text: String = "Testo",
    imageVectorIcon: ImageVector? = Icons.Default.Adb,
    onClick: () -> Unit = {}
) {
    BtnLiz(
        text = text,
        imageVectorIcon = imageVectorIcon,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Blue,
            contentColor = Color.White,
            disabledBackgroundColor = Color.LightGray,
            disabledContentColor = Color.DarkGray,
        ),
        onClick = onClick
    )
}
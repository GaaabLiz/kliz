package ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
public fun BtnLiz(
    text: String,
    imageVectorIcon: ImageVector? = null,
    enabled: Boolean = true,
    paddingH: Double = 0.0,
    paddingV: Double = 0.0,
    size : Dp = 200.dp,
    color : Color = MaterialTheme.colors.primary,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.width(size),
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(backgroundColor = color, contentColor = Color.White),
    ) {
        if(imageVectorIcon != null) {
            Icon(
                imageVectorIcon,
                contentDescription = "Button :\"$text\"",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        }
        Text(text = text, modifier = Modifier.padding(paddingH.dp, paddingV.dp), fontWeight = FontWeight.Bold)
    }
}


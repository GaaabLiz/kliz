package io.github.gaaabliz.kliz.compose.common.ui.card

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import io.github.gaaabliz.kliz.compose.common.theme.titanWhite
import io.github.gaaabliz.kliz.compose.common.theme.uniupo
import io.github.gaaabliz.kliz.compose.common.ui.utils.CircleBoxIcon


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CircleIconCard(
    icon : ImageVector = Icons.Default.Lightbulb,
    text : String = "Card",
    backgroundColor : Color = uniupo,
    textColor : Color = titanWhite,
    onClick : () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick,
        backgroundColor = backgroundColor,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CircleBoxIcon(circleBackgroundColor = titanWhite, iconColor = backgroundColor , icon = icon )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.body1,
                color = textColor,
            )
        }
    }
}
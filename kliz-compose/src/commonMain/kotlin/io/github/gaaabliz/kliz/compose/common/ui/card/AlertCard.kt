package io.github.gaaabliz.kliz.compose.common.ui.card



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.gaaabliz.kliz.compose.common.model.AlertType

@Composable
fun AlertCard(
    text : String = "Testo di prova ".repeat(10),
    alertType: AlertType = AlertType.entries.toTypedArray().random()
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.background,
        border = BorderStroke(2.dp, alertType.color)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier.padding(start = 12.dp, top = 12.dp, bottom = 12.dp),
                imageVector = alertType.icon,
                contentDescription = "",
                tint = alertType.color
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = text,
                    color = alertType.color,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start,
                )
            }
        }
    }
}

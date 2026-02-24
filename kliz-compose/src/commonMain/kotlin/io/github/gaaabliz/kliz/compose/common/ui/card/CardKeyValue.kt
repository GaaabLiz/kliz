package io.github.gaaabliz.kliz.compose.common.ui.card


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.gaaabliz.kliz.compose.common.theme.doveGray
import io.github.gaaabliz.kliz.compose.common.theme.silver
import io.github.gaaabliz.kliz.compose.common.theme.titanWhite



@Composable
fun CardKeyValue(
    key : String = "Chiave",
    value : String = "Valore",
    backgroundColor : Color = MaterialTheme.colors.background,
    borderStroke: BorderStroke = BorderStroke(1.dp, silver)
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = backgroundColor,
        border = borderStroke,
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = key, style = MaterialTheme.typography.subtitle1, color = doveGray)
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp, start = 5.dp, end = 5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = value, style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}
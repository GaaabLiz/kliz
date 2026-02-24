package io.github.gaaabliz.kliz.compose.common.ui.card


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.gaaabliz.kliz.compose.common.theme.titanWhite
import io.github.gaaabliz.kliz.compose.common.theme.uniupo


@Composable
fun PersonCard(
    onEmailSend : (String) -> Unit = {},
    name : String = "Giovanni Gabriele",
    email : String = "giovammi@ggg.com",
    mansion : String = "CEO",
    photoDrawable : Int? = null,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = titanWhite,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onEmailSend(email)
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = name, color = Color.Black, style = MaterialTheme.typography.h6)
                Text(text = mansion, color = uniupo, style = MaterialTheme.typography.subtitle2)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = email, color = Color.Blue, style = MaterialTheme.typography.body2)
            }
        }
    }
}
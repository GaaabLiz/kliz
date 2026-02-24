@file:Suppress("DuplicatedCode")

package io.github.gaaabliz.kliz.compose.common.ui_DA_SISTEMARE

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.gaaabliz.kliz.compose.common.model.SnacklizController
import io.github.gaaabliz.kliz.compose.common.model.SnacklizData
import io.github.gaaabliz.kliz.compose.common.model.SnacklizType

@Composable
fun rememberSnacklizController(scaffoldState: ScaffoldState) = remember{ SnacklizController(scaffoldState) }


@Composable
fun SnacklizCustomSnackbar(
    data: SnacklizData,
    type: SnacklizType.CustomSnackbar,
    showTitle : Boolean = true,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(3.dp, type.colorPrimary),
        modifier = Modifier.wrapContentSize(),
        elevation = 5.dp,
        backgroundColor = type.colorBackground
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
                    Icon(imageVector = type.leadingIcon, contentDescription = data.title, tint = type.colorPrimary)
                }
                Spacer(Modifier.width(10.dp))
                Column(
                    modifier = Modifier.weight(weight = 2F, fill = false),
                    verticalArrangement = Arrangement.Center
                ) {
                    if(showTitle) {
                        Text(
                            text = data.title,
                            color = Color.Black,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Justify
                        )
                    }
                    Text(
                        text = data.description,
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}




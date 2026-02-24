package io.github.gaaabliz.kliz.compose.common.ui.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.gaaabliz.kliz.compose.common.theme.doveGray
import io.github.gaaabliz.kliz.compose.common.theme.titanWhite
import io.github.gaaabliz.kliz.compose.common.theme.uniupo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RadioAnswerCard(
    text: String = "Card",
    mainColor : Color = uniupo,
    secondaryColor : Color = doveGray,
    backgroundColor: Color = titanWhite,
    isEnabled : Boolean = true,
    isSelected : Boolean = false,
    onClick: () -> Unit = {},
    onLongClick : () -> Unit = {},
) {

    val border = if(isSelected) BorderStroke(4.dp, mainColor) else BorderStroke(1.dp, secondaryColor)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(onClick = onClick, onLongClick = onLongClick)
            .padding(8.dp),
        backgroundColor = titanWhite,
        shape = RoundedCornerShape(8.dp),
        border = border,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = isSelected,
                onClick = onClick,
                enabled = isEnabled,
                colors = RadioButtonDefaults.colors(
                    selectedColor = mainColor,
                    unselectedColor = secondaryColor,
                    disabledColor = backgroundColor,
                )
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.body1,
                color = secondaryColor,
            )
        }
    }
}
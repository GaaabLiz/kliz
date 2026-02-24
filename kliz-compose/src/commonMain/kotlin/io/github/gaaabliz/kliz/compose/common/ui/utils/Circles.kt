package io.github.gaaabliz.kliz.compose.common.ui.utils

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.gaaabliz.kliz.compose.common.theme.silverChalice
import io.github.gaaabliz.kliz.compose.common.theme.uniupo


@Composable
fun CircleBoxIcon(
    circleBackgroundColor : Color = Color.Red,
    iconColor : Color = Color.White,
    icon : ImageVector = Icons.Default.House,
    circleSize : Dp = 32.dp,
    iconSize : Dp = 24.dp
) {
    Box(
        contentAlignment= Alignment.Center,
        modifier = Modifier
            .size(circleSize)
            .border(
                width = 2.dp,
                color = circleBackgroundColor,
                shape = CircleShape
            ),
    ){
        Icon(
            imageVector = icon,
            contentDescription = "contentDescription",
            modifier = Modifier
                .size(iconSize)
                .background(circleBackgroundColor, CircleShape)
                .padding(2.dp),
            tint = iconColor
        )
    }
}


@Composable
fun CircleIcon(
    iconColor : Color = Color.White,
    icon : ImageVector = Icons.Default.House,
    iconSize : Dp = 24.dp,
    iconPadding : Dp = 2.dp,
    backgroundColor: Color = Color.Red
) {
    Icon(
        imageVector = icon,
        contentDescription = "contentDescription",
        modifier = Modifier
            .size(iconSize)
            .background(backgroundColor, CircleShape)
            .padding(iconPadding),
        tint = iconColor
    )
}


@Composable
fun CircleText() {
    Box(
        contentAlignment= Alignment.Center,
        modifier = Modifier
            .size(32.dp)
            .border(
                width = 2.dp,
                color = uniupo,
                shape = CircleShape
            ),
    ){
        Text(
            text = "A",
            color = uniupo,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
        )
    }
}


@Composable
fun CircleTextNoSize(
    text: String = "A",
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        color = Color.White,
        modifier = Modifier
            .background(silverChalice, shape = CircleShape)
            .circleLayout()
            .padding(8.dp)
    )
}

fun Modifier.circleLayout() =
    layout { measurable, constraints ->
        // Measure the composable
        val placeable = measurable.measure(constraints)

        //get the current max dimension to assign width=height
        val currentHeight = placeable.height
        val currentWidth = placeable.width
        val newDiameter = maxOf(currentHeight, currentWidth)

        //assign the dimension and the center position
        layout(newDiameter, newDiameter) {
            // Where the composable gets placed
            placeable.placeRelative((newDiameter-currentWidth)/2, (newDiameter-currentHeight)/2)
        }
    }
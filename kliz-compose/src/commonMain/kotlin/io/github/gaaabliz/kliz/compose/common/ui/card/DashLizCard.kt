package io.github.gaaabliz.kliz.compose.common.ui.card

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.gaaabliz.kliz.common.util.GenUtils


@Composable
public fun DashLizCard(
    cardTitle:String = GenUtils.generateRandomStringApache(10),
    expandedState : Boolean = true,
    backgroundColor : Color = Color.White,
    titleColor: Color = Color.Black,
    textTile : @Composable() () -> Unit = { Text(cardTitle, style = MaterialTheme.typography.body1, ) },
    content: @Composable() () -> Unit = {}
) {
    val expanded = rememberSaveable { mutableStateOf(expandedState) }
    Card (
        modifier = Modifier.padding(all = 15.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier.background(backgroundColor).padding(20.dp).animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                textTile()
                IconButton(onClick = { expanded.value = !expanded.value }) {
                    Icon(
                        imageVector = if (expanded.value) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowLeft,
                        contentDescription = if (expanded.value) { "Card aperta" } else { "Card chiusa" }
                    )
                }
            }
            if(expanded.value) {
                Row { Spacer(Modifier.height(4.dp)) }
                Row {
                    Divider(
                        color = Color.LightGray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                    )
                }
                Row { Spacer(Modifier.height(20.dp)) }
                Row {
                    content()
                }
            }

        }
    }
}
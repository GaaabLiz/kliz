package io.github.gaaabliz.kliz.compose.jvm.ui_DA_SISTEMARE

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.gaaabliz.kliz.compose.common.theme.doveGray
import io.github.gaaabliz.kliz.compose.common.theme.silverChalice

@Composable
public fun <T, S>DropdownEnum(
    isOpen: Boolean,
    entriesNames : List<String>,
    entriesIcons : List<ImageVector>,
    list : Map<T, S>,
    iconTint : Color = doveGray,
    operationActive : Boolean = false,
    getDisplayName : (String) -> String = { it },
    onDismiss : () -> Unit,
    onItemClicked : (name : String) -> Unit,
) {
    Box(
        modifier = Modifier.padding(top = 40.dp).wrapContentSize(Alignment.TopEnd)
    ) {
        MaterialTheme(
            shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(10.dp))
        ) {
            DropdownMenu(
                expanded = isOpen,
                onDismissRequest = { onDismiss() },
                modifier = Modifier.border(border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.primary), shape = RoundedCornerShape(10.dp))
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp).clipToBounds(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    list.forEach { (name, icon) ->
                        DropdownMenuItem(
                            enabled = !operationActive,
                            onClick = { onItemClicked(name.toString()) },
                        ) {
                            val displayName = getDisplayName(name.toString())
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 1.dp).clipToBounds(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(icon as ImageVector, contentDescription = null, tint = iconTint)
                                Spacer(Modifier.width(10.dp))
                                Text(displayName)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
public fun <E : Enum<E>> DropdownEnum(
    isOpen: Boolean,
    operationActive : Boolean = false,
    disabledColor : Color = silverChalice,
    allowedItems : () -> List<E>,
    getVisualizedName : (E) -> String,
    getColor : (E) -> Color,
    getIcon : (E) -> ImageVector,
    onDismiss : () -> Unit,
    onItemClicked : (item : E) -> Unit,
) {
    val items = allowedItems()
    Box(
        modifier = Modifier.padding(top = 40.dp).wrapContentSize(Alignment.TopEnd)
    ) {
        MaterialTheme(
            shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(10.dp))
        ) {
            DropdownMenu(
                expanded = isOpen,
                onDismissRequest = { onDismiss() },
                modifier = Modifier.border(border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.primary), shape = RoundedCornerShape(10.dp))
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp).clipToBounds(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    items.forEach { enum ->
                        DropdownMenuItem(
                            enabled = !operationActive,
                            onClick = { onItemClicked(enum) },
                        ) {
                            val displayName = getVisualizedName(enum)
                            val icon = getIcon(enum)
                            val enabledColor = getColor(enum)
                            val actualColor = if(!operationActive) enabledColor else disabledColor
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 1.dp).clipToBounds(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(icon, contentDescription = null, tint = actualColor)
                                Spacer(Modifier.width(10.dp))
                                Text(
                                    text = displayName,
                                    color = actualColor,
                                    style = MaterialTheme.typography.subtitle1,
                                    textAlign = TextAlign.Justify,
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun <E : Enum<E>> DropdownEnum(
    isOpen: Boolean,
    operationActive : Boolean = false,
    allowedItems : () -> List<E>,
    getVisualizedName : (E) -> String,
    getColor : (E) -> Color,
    getIcon : (E) -> ImageVector,
    onDismiss : () -> Unit,
    onItemClicked : (item : E) -> Unit,
) {
    val items = allowedItems()
    Box(
        modifier = Modifier.padding(top = 40.dp).wrapContentSize(Alignment.TopEnd)
    ) {
        MaterialTheme(
            shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(10.dp))
        ) {
            DropdownMenu(
                expanded = isOpen,
                onDismissRequest = { onDismiss() },
                modifier = Modifier.border(border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.primary), shape = RoundedCornerShape(10.dp))
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp).clipToBounds(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    items.forEach { enum ->
                        DropdownMenuItem(
                            enabled = !operationActive,
                            onClick = { onItemClicked(enum) },
                        ) {
                            val displayName = getVisualizedName(enum)
                            val icon = getIcon(enum)
                            val enabledColor = getColor(enum)
                            val disabledColor = silverChalice
                            val actualColor = if(!operationActive) enabledColor else disabledColor
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 1.dp).clipToBounds(),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(icon, contentDescription = null, tint = actualColor)
                                Spacer(Modifier.width(10.dp))
                                Text(
                                    text = displayName,
                                    color = actualColor,
                                    style = MaterialTheme.typography.body1,
                                    textAlign = TextAlign.Justify,
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}
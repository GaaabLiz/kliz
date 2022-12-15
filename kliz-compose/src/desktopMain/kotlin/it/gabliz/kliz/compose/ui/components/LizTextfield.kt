package it.gabliz.kliz.compose.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.rounded.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import it.gabliz.kliz.compose.theme.athensGray
import it.gabliz.kliz.compose.theme.doveGray
import it.gabliz.kliz.compose.theme.silverChalice


@OptIn(ExperimentalFoundationApi::class)
@Composable
public fun LizTextfield(
    value: String,
    isEnabled : Boolean = true,
    isError : Boolean = false,
    isNumber: Boolean = false,
    outsideLabel: String? = null,
    innerLabel: String? = null,
    placeholderText: String? = null,
    tooltipHelpText: String? = null,
    errorText: String? = null,
    leadingIcon: ImageVector? = null,
    maxChar: Int? = null,
    maxLines : Int = 1,
    readOnly : Boolean = false,
    actionButtonIcon : ImageVector? = null,
    actionButton2Icon : ImageVector? = null,
    onExternalIconClicked : (() -> Unit)? = null,
    onExternal2IconClicked : (() -> Unit)? = null,
    onImeiAction: (() -> Unit)? = null,
    onValueChange: (String, Boolean) -> Unit
) {

    val bottomRowPaddingRight = if(actionButtonIcon != null) 55.dp else 20.dp
    val bottomRowPaddingLeft = if(outsideLabel == null) 20.dp else 0.dp
    val iconColor = if(isEnabled) MaterialTheme.colors.primary else athensGray

    Column(
        modifier = Modifier.fillMaxWidth().padding(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if(outsideLabel != null) {
                Text(
                    text = "$outsideLabel: ",
                    style = MaterialTheme.typography.subtitle1,
                    color = if(isEnabled) doveGray else silverChalice
                )
                Spacer(Modifier.width(10.dp))
            }
            OutlinedTextField(
                value = value,
                onValueChange = {
                    if(maxChar != null) {
                        onValueChange(it, value.length <= maxChar)
                    } else {
                        onValueChange(it, false)
                    }
                },
                enabled = isEnabled,
                readOnly = readOnly,
                textStyle = MaterialTheme.typography.body2,
                isError = isError,
                singleLine = maxLines == 1,
                label = {
                    if(innerLabel != null) {
                        Text(
                            text = innerLabel,
                            style = MaterialTheme.typography.caption,
                            color = doveGray,
                        )
                    }
                },
                placeholder = {
                    if(placeholderText != null) {
                        Text(
                            text = placeholderText,
                            style = MaterialTheme.typography.caption,
                            color = doveGray,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                    }
                },
                leadingIcon = {
                    if(leadingIcon != null) {
                        Icon(leadingIcon, contentDescription = "", tint = iconColor)
                    }
                },
                trailingIcon = {
                    if(tooltipHelpText != null && !isError) {
                        TooltipArea(
                            tooltip = { Tooltip1(tooltipHelpText) },
                            delayMillis = 600,
                            tooltipPlacement = TooltipPlacement.CursorPoint(
                                alignment = Alignment.BottomEnd,
                                offset = DpOffset(x = 0.dp, y = 35.dp)
                            )
                        ) {
                            Spacer(Modifier.width(50.dp))
                            Icon(Icons.Filled.Help, "", tint = athensGray)
                        }
                    }
                    if(isError && errorText != null) {
                        TooltipArea(
                            tooltip = { Tooltip1(errorText) },
                            delayMillis = 600, // In millisecondi
                            tooltipPlacement = TooltipPlacement.CursorPoint(
                                alignment = Alignment.BottomEnd,
                                offset = DpOffset(x = 0.dp, y = 35.dp) // Tooltip offset
                            )
                        ) {
                            Spacer(Modifier.width(50.dp))
                            Icon(Icons.Rounded.Error, "", tint = MaterialTheme.colors.error)
                        }
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = doveGray,
                    focusedIndicatorColor = MaterialTheme.colors.primary,
                    backgroundColor = Color.White
                ),
                modifier = Modifier.weight(1F),
                keyboardActions = KeyboardActions{ if (onImeiAction != null) { onImeiAction() } },
                keyboardOptions = if(isNumber) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            if(actionButtonIcon != null) {
                Spacer(Modifier.width(10.dp))
                IconButton(
                    enabled = isEnabled,
                    onClick = { if (onExternalIconClicked != null) { onExternalIconClicked() } },
                ) {
                    Icon(actionButtonIcon, contentDescription = "", tint = iconColor)
                }
            }
            if(actionButton2Icon != null) {
                Spacer(Modifier.width(10.dp))
                IconButton(
                    enabled = isEnabled,
                    onClick = { if (onExternal2IconClicked != null) { onExternal2IconClicked() } },
                ) {
                    Icon(actionButton2Icon, contentDescription = "", tint = iconColor)
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = bottomRowPaddingLeft, end = bottomRowPaddingRight),
            horizontalArrangement = Arrangement.End
        ) {
            if(maxChar != null) {
                Text(
                    text = "${value.length}/${maxChar}",
                    style = MaterialTheme.typography.caption,
                    color = doveGray,
                )
            }
        }
    }
}
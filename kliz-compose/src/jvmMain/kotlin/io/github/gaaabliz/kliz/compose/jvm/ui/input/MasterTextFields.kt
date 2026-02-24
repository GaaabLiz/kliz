package io.github.gaaabliz.kliz.compose.jvm.ui.input

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import io.github.gaaabliz.kliz.compose.common.theme.athensGray
import io.github.gaaabliz.kliz.compose.common.theme.doveGray
import io.github.gaaabliz.kliz.compose.jvm.ui.popup.Tooltip1

enum class TextfieldType(
    val hasActionButton : Boolean = false,
    val hasTrailingHelpIcon : Boolean = false,
    val hasMaxChar : Boolean = false,
    val maxChar : Int = 0,
    val isReadOnly : Boolean = false,
    val isSingleLine : Boolean = true,
    val leadingIcon: ImageVector? = null,

) {
    PATH_SELECTOR(
        hasActionButton = true,
        leadingIcon = Icons.Filled.Source,
        hasTrailingHelpIcon = true
    ),
    SETTING_SHOW_DIR(
        isReadOnly = true
    ),
    SETTING_SHOW_TEXT(
        isReadOnly = true
    ),
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
@Deprecated(message = "", level = DeprecationLevel.WARNING)
fun MasterTextfield(
    value: String,
    type : TextfieldType,
    isEnabled : Boolean = true,
    isError : Boolean = false,
    isNumber: Boolean = false,
    outsideLabel: String? = null,
    innerLabel: String? = null,
    placeholderText: String? = null,
    tooltipHelpText: String? = null,
    errorText: String? = null,
    onExternalIconClicked : (() -> Unit)? = null,
    onImeiAction: (() -> Unit)? = null,
    onValueChange: (String, Boolean) -> Unit
) {

    val bottomRowPaddingRight = if(type.hasActionButton) 55.dp else 20.dp
    val bottomRowPaddingLeft = if(outsideLabel == null) 20.dp else 0.dp

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
                )
                Spacer(Modifier.width(10.dp))
            }
            OutlinedTextField(
                value = value,
                onValueChange = {
                    if(type.hasMaxChar) {
                        onValueChange(it, value.length <= type.maxChar)
                    } else {
                        onValueChange(it, false)
                    }
                },
                enabled = isEnabled,
                readOnly = type.isReadOnly,
                textStyle = MaterialTheme.typography.body2,
                isError = isError,
                singleLine = type.isSingleLine,
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
                    if(type.leadingIcon != null) {
                        Icon(type.leadingIcon, contentDescription = "", tint = MaterialTheme.colors.primary)
                    }
                },
                trailingIcon = {
                    if(type.hasTrailingHelpIcon && tooltipHelpText != null && !isError) {
                        TooltipArea(
                            tooltip = { Tooltip1(tooltipHelpText) },
                            delayMillis = 600, // In millisecondi
                            tooltipPlacement = TooltipPlacement.CursorPoint(
                                alignment = Alignment.BottomEnd,
                                offset = DpOffset(x = 0.dp, y = 35.dp) // Tooltip offset
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
                            Icon(Icons.Filled.Error, "", tint = MaterialTheme.colors.error)
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
            Spacer(Modifier.width(10.dp))
            when(type) {
                TextfieldType.PATH_SELECTOR -> {
                    IconButton(
                        enabled = isEnabled,
                        onClick = { if (onExternalIconClicked != null) { onExternalIconClicked() } },
                    ) {
                        Icon(
                            Icons.Filled.OpenInBrowser,
                            contentDescription = "",
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
                else -> {}
            }

        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = bottomRowPaddingLeft, end = bottomRowPaddingRight),
            horizontalArrangement = Arrangement.End
        ) {
            if(type.hasMaxChar) {
                Text(
                    text = "${value.length}/${type.maxChar}",
                    style = MaterialTheme.typography.caption,
                    color = doveGray,
                )
            }
        }
    }
}
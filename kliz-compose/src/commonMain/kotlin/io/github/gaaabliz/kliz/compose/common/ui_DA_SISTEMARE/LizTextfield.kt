package io.github.gaaabliz.kliz.compose.common.ui_DA_SISTEMARE

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.gaaabliz.kliz.compose.common.theme.athensGray
import io.github.gaaabliz.kliz.compose.common.theme.doveGray
import io.github.gaaabliz.kliz.compose.common.theme.silverChalice


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LizTextfieldBasic(
    value : String,
    onValueChange : (String, Boolean) -> Unit,
    label : String,
    isEnabled: Boolean = true,
    isError: Boolean = false,
    errorText: String? = null,
    maxLines: Int = 1,
    maxLength : Int? = null,
    leadingIcon: ImageVector,
    inputType : KeyboardType = KeyboardType.Text,
) {

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val inputService = LocalTextInputService.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focus = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                if(maxLength != null) {
                    onValueChange(it, value.length <= maxLength)
                } else {
                    onValueChange(it, true)
                }
            },
            label = { Text(label) },
            keyboardOptions = KeyboardOptions(
                keyboardType = inputType,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                },
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged {
                    if (focus.value != it.isFocused) {
                        focus.value = it.isFocused
                        if (!it.isFocused) {
                            inputService?.hideSoftwareKeyboard()
                        }
                    }
                },
            enabled = isEnabled,
            maxLines = maxLines,
            isError = isError,
            singleLine = maxLines == 1,
            leadingIcon = { Icon(leadingIcon, contentDescription = label, tint = doveGray) },
            trailingIcon = { if (isError) Icon(Icons.Filled.Error,"error", tint = MaterialTheme.colors.error) },
            colors = TextFieldDefaults.textFieldColors(
                textColor = doveGray,
                focusedIndicatorColor = Color.Black,
                backgroundColor = Color.White
            ),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            if(maxLength != null) {
                Text(
                    text = "${value.length} / $maxLength",
                    style = MaterialTheme.typography.caption,
                    color = if(value.length > maxLength) MaterialTheme.colors.error else doveGray
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp),
            horizontalArrangement = Arrangement.End
        ) {
            if(isError) {
                Text(
                    text = errorText ?: "Errore",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.error
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LizTextAreaBasic(
    value : String,
    onValueChange : (String) -> Unit,
    height : Dp = 100.dp,
    label : String,
    isEnabled: Boolean = true,
    isError: Boolean = false,
    maxLines: Int = 10,
    maxLength : Int? = null,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Default
            ),
            keyboardActions = KeyboardActions(

            ),
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = height)
                .focusRequester(focusRequester),
            enabled = isEnabled,
            maxLines = maxLines,
            isError = isError,
            singleLine = maxLines == 1,
            trailingIcon = { if (isError) Icon(Icons.Filled.Error,"error", tint = MaterialTheme.colors.error) },
            colors = TextFieldDefaults.textFieldColors(
                textColor = doveGray,
                focusedIndicatorColor = Color.Black,
                backgroundColor = Color.White
            ),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            if(maxLength != null) {
                Text(
                    text = "${value.length} / $maxLength",
                    style = MaterialTheme.typography.caption,
                    color = if(value.length > maxLength) MaterialTheme.colors.error else doveGray
                )
            }
        }
    }
}


@Composable
fun LizTextfield(
    value: String,
    isEnabled : Boolean = true,
    isError : Boolean = false,
    isNumber: Boolean = false,
    outsideLabel: String? = null,
    label: String? = null,
    placeholderText: String? = null,
    errorText: String? = null,
    leadingIcon: ImageVector? = null,
    maxChar: Int? = null,
    maxLines : Int = 1,
    textAreaHeight : Dp = 100.dp,
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

    val modifier = if(maxLines != 1) Modifier.height(textAreaHeight) else Modifier

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
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
                        onValueChange(it, true)
                    }
                },
                enabled = isEnabled,
                readOnly = readOnly,
                textStyle = MaterialTheme.typography.body2,
                isError = isError,
                singleLine = maxLines == 1,
                label = {
                    if(label != null) {
                        Text(
                            text = label,
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
                    if (isError)
                        Icon(Icons.Filled.Error,"error", tint = MaterialTheme.colors.error)
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = bottomRowPaddingLeft, end = bottomRowPaddingRight),
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


package io.github.gaaabliz.kliz.compose.android.ui.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import io.github.gaaabliz.kliz.compose.common.theme.doveGray

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
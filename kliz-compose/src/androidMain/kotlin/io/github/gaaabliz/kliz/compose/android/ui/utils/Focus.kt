package io.github.gaaabliz.kliz.compose.android.ui.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max


/*

// TODO: BISOGNA CERCARE UNA VERSIONE AGGIORNATA

@OptIn(ExperimentalLayoutApi::class)
@SuppressLint("ModifierFactoryUnreferencedReceiver", "UnnecessaryComposedModifier")
fun Modifier.clearFocusOnKeyboardDismiss(): Modifier = composed(
    inspectorInfo = {},
    factory = {
        val isFocused = remember { mutableStateOf(false) }
        val keyboardAppearedSinceLastFocused = remember { mutableStateOf(false) }
        val focusManager = LocalFocusManager.current
        val imeIsVisible by rememberSaveable { derivedStateOf { WindowInsets.isImeVisible } }

        LaunchedEffect(imeIsVisible) {
            if (imeIsVisible) {
                keyboardAppearedSinceLastFocused.value = true
            } else if (keyboardAppearedSinceLastFocused.value && isFocused.value) {
                focusManager.clearFocus()
                keyboardAppearedSinceLastFocused.value = false
            }
        }

        onFocusEvent {
            isFocused.value = it.isFocused
        }

        this
    }
)*/
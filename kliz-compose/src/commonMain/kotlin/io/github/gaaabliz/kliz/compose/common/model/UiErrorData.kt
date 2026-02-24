package io.github.gaaabliz.kliz.compose.common.model

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Stable
class UiErrorData {
    var errorText by mutableStateOf("")
    var errorStatus by mutableStateOf(false)
}

package io.github.gaaabliz.kliz.compose.common.model

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SnacklizController(
    private var scaffoldState: ScaffoldState
) {

    var snackbarActive by mutableStateOf(false)
    var currentSnackbarData by mutableStateOf<SnacklizData>(SnacklizData.getDefault())
    var currentCustomSnackbarType by mutableStateOf<SnacklizType.CustomSnackbar>(SnacklizType.CustomSnackbar.UNKNOWN())

    suspend fun showVanillaSnackbar(
        data : SnackbarData,
        onAction : (() -> Unit)? = null,
        onDismissed: (() -> Unit)? = null
    ) {
        val result = scaffoldState.snackbarHostState.showSnackbar(
            message = data.message,
            actionLabel = data.actionLabel,
            duration = data.duration
        )
        when(result) {
            SnackbarResult.Dismissed -> {
                onDismissed?.invoke()
            }
            SnackbarResult.ActionPerformed -> onAction?.invoke()
        }
    }

    fun showVanillaSnackbar(
        scope : CoroutineScope,
        data : SnackbarData,
        onAction : (() -> Unit)? = null,
        onDismissed: (() -> Unit)? = null
    ) {
        scope.launch { showVanillaSnackbar(data, onAction, onDismissed) }
    }


    suspend fun showCustomSnackbar(
        data: SnacklizData,
        type : SnacklizType.CustomSnackbar,
        onDismissed: (() -> Unit)? = null
    ) {
        if(snackbarActive) hideSnackbar()
        delay(500)
        snackbarActive = true
        currentSnackbarData = data
        currentCustomSnackbarType = type
        val result = scaffoldState.snackbarHostState.showSnackbar(
            message = data.title,
            actionLabel = data.description,
            duration = type.duration
        )
        when(result) {
            SnackbarResult.Dismissed -> {
                snackbarActive = false
                onDismissed?.invoke()
            }
            else -> {}
        }
    }

    fun showCustomSnackbar(
        scope : CoroutineScope,
        data: SnacklizData,
        type : SnacklizType.CustomSnackbar,
        onDismissed: (() -> Unit)? = null
    ) {
        scope.launch { showCustomSnackbar(data, type,onDismissed) }
    }
    suspend fun hideSnackbar() {
        scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
    }
}
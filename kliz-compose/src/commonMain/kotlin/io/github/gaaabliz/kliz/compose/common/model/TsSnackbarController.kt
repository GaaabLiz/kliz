package io.github.gaaabliz.kliz.compose.common.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.gaaabliz.kliz.common.base.Operation
import kotlinx.coroutines.delay


class SnackbarController (

) {
    private var currentData : SnackbarData? = null

    var currentStateData by mutableStateOf<SnackbarData>(SnackbarData.getDefault())
    var snackVisible by mutableStateOf<Boolean>(false)

    fun <T> execSnackbarJob(
        operationTitle : String,
        workToDo : () -> Operation<T>,
        onOperationSuccess : ( data : T? ) -> Unit,
        onOperationError : ( errorMessage : String ) -> Unit
    ) {
        val status = workToDo()
    }

    fun updateLoadingSnackbar() {

    }

    suspend fun showSuccess(
        title : String = "null",
        description : String = "null",
        onEnd : ( () -> Unit )? = null
    ) {
        val data = SnackbarData(
            title = title,
            description = description,
            type = SnackbarType.SUCCESS
        )
        show(data)
    }

    private suspend fun show(data: SnackbarData) {
        if(snackVisible) {
            setInvisible()
            delay(500)
            setData(data)
            setVisible()
        }
        setData(data)
        setVisible()
        val mSecDelay = data.type.durationDelay
        if(mSecDelay != null) {
            delay(mSecDelay)
            setInvisible()
        }
    }


    private fun setData(data : SnackbarData) {
        currentStateData = data
        currentData = data
    }

    private fun getData() = currentData

    private fun setVisible() { snackVisible = true }
    private fun setInvisible() { snackVisible = false }
    fun close() { setInvisible() }
}
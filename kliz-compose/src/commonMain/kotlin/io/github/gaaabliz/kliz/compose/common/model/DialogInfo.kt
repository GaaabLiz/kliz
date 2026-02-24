package io.github.gaaabliz.kliz.compose.common.model

data class DialogInfo(
    var isVisible : Boolean = false,
    val dialogTitle: String = "",
    val dialogMessage: String = "",
    val onOkClicked: () -> Unit = {},
)

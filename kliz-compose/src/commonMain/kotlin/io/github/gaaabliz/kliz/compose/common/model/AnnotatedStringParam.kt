package io.github.gaaabliz.kliz.compose.common.model

import androidx.compose.ui.text.SpanStyle

data class AnnotatedStringParam(
    val text : String,
    val style : SpanStyle? = null,
)

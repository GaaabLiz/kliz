package io.github.gaaabliz.kliz.compose.common.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class DropdownEnumItem <E : Enum<E>>(
    val enum: E,
    val icon : ImageVector,
    val textColorEnabled : Color,
    val textColorDisabled : Color,
)

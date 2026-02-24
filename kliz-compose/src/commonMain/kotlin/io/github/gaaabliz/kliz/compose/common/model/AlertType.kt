package io.github.gaaabliz.kliz.compose.common.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.gaaabliz.kliz.compose.common.theme.uniupo

enum class AlertType(
    val color : Color,
    val icon : ImageVector
) {
    SUCCESS(Color.Green, Icons.Default.Check),
    WARNING(Color.Yellow, Icons.Default.Warning),
    ERROR(uniupo, Icons.Default.Error)
}
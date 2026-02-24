package io.github.gaaabliz.kliz.compose.common.model

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class SnackbarData(
    val title : String,
    val description : String,
    val type : SnackbarType,
    val progress : Int = 0
) {
    companion object {
        fun getDefault() = SnackbarData(
            title = "null",
            description = "null",
            type = SnackbarType.UNKNOWN
        )
    }
}

enum class SnackbarType(
    val innerBoxType: SnackbarInnerBoxType = SnackbarInnerBoxType.ICON,
    val icon : ImageVector? = null,
    val duration : SnackbarDuration = SnackbarDuration.Indefinite,
    val durationDelay : Long? = null,
    val hasInnerProgressBar : Boolean = false,
    val colorPrimary : Color,
    val colorBackground : Color,
) {
    INFO(
        icon = Icons.Default.Info,
        duration = SnackbarDuration.Long,
        colorBackground = Color(0xFFE4E4E4),
        colorPrimary = Color.Black,
    ),
    SUCCESS(
        icon = Icons.Filled.Check,
        durationDelay = 2000L,
        colorBackground = Color(0xFFE3F3DC),
        colorPrimary = Color(0xFF2C7E2D),
    ),
    ERROR(
        icon = Icons.Filled.Error,
        colorBackground = Color(0xFFFCF1F1),
        colorPrimary = Color.Red,
    ),
    LOADING(
        innerBoxType = SnackbarInnerBoxType.CIRCULAR_PROGRESS_INDEF,
        hasInnerProgressBar = true,
        colorBackground = Color(0xFFE3F3DC),
        colorPrimary = Color.Green,
    ),
    QUESTION(
        icon = Icons.Filled.QuestionMark,
        colorBackground = Color(0xFFECECFD),
        colorPrimary = Color(0xFF5352EC),
    ),
    INPUT(
        icon = Icons.Filled.Input,
        colorBackground = Color(0xFFE3F3DC),
        colorPrimary = Color.Green,
    ),
    UNKNOWN(
        icon = Icons.Filled.Emergency,
        colorBackground = Color(0xFFE3F3DC),
        colorPrimary = Color.Green,
    ),
}

enum class SnackbarInnerBoxType {ICON, CIRCULAR_PROGRESS_INDEF}
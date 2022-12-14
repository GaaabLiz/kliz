package it.gabliz.kliz.compose.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

public data class SnackbarData(
    val title : String,
    val description : String,
    val type : SnackbarType,
    val progress : Int = 0
) {
    public companion object {
        public fun getDefault(): SnackbarData = SnackbarData(
            title = "null",
            description = "null",
            type = SnackbarType.UNKNOWN
        )
    }
}

public enum class SnackbarType(
    public val innerBoxType: SnackbarInnerBoxType = SnackbarInnerBoxType.ICON,
    public val icon : ImageVector? = null,
    public val durationDelay : Long? = null,
    public val hasInnerProgressBar : Boolean = false,
    public val colorPrimary : Color,
    public val colorBackground : Color,
) {
    INFO(
        icon = Icons.Default.Info,
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
        durationDelay = 5000L,
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

public enum class SnackbarInnerBoxType {ICON, CIRCULAR_PROGRESS_INDEF}
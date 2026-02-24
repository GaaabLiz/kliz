package io.github.gaaabliz.kliz.compose.common.model

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.gaaabliz.kliz.compose.common.theme.linen
import io.github.gaaabliz.kliz.compose.common.theme.silverChalice
import io.github.gaaabliz.kliz.compose.common.theme.titanWhite


public data class SnacklizData(
    val title : String,
    val description : String,
    val progress : Int? = null
) {
    public companion object {
        public fun getDefault(): SnacklizData = SnacklizData(
            title = "null",
            description = "null",
        )
    }
}

sealed class SnacklizPayload {
    sealed class Snackbar(
        open val icon : ImageVector,
        open val text : String? = null,
    ) {
        class IconButton(
            override val icon : ImageVector,
        ) : Snackbar(icon)
        class Button(override val icon : ImageVector, override val text : String) : Snackbar(icon, text)
    }
}

sealed class SnacklizType() {

    sealed class CustomSnackbar(
        open val leadingIcon : ImageVector,
        open val colorPrimary : Color,
        open val colorBackground : Color,
        open val duration : SnackbarDuration = SnackbarDuration.Long,
    ) : SnacklizType() {

        class UNKNOWN(
            override val leadingIcon: ImageVector = Icons.Default.DeviceUnknown,
            override val colorPrimary: Color = silverChalice,
            override val colorBackground: Color = Color.Black,
            override val duration: SnackbarDuration = SnackbarDuration.Short,
        ) : CustomSnackbar(leadingIcon, colorPrimary, colorBackground, duration)

        class INFO(
            override val leadingIcon: ImageVector = Icons.Default.Info,
            override val colorPrimary: Color = Color.Black,
            override val colorBackground: Color = titanWhite,
            override val duration: SnackbarDuration = SnackbarDuration.Short,
        ) : CustomSnackbar(leadingIcon, colorPrimary, colorBackground, duration)

        class ERROR(
            override val leadingIcon: ImageVector = Icons.Filled.Error,
            override val colorPrimary: Color = Color.Red,
            override val colorBackground: Color = linen,
            override val duration: SnackbarDuration = SnackbarDuration.Long,
        ) : CustomSnackbar(leadingIcon, colorPrimary, colorBackground, duration)

        class WARNING(
            override val leadingIcon: ImageVector = Icons.Filled.Warning,
            override val colorPrimary: Color = Color.Yellow,
            override val colorBackground: Color = titanWhite,
            override val duration: SnackbarDuration = SnackbarDuration.Long,
        ) : CustomSnackbar(leadingIcon, colorPrimary, colorBackground, duration)

        class SUCCESS(
            override val leadingIcon: ImageVector = Icons.Filled.Check,
            override val colorPrimary: Color = Color.Green,
            override val colorBackground: Color = titanWhite,
            override val duration: SnackbarDuration = SnackbarDuration.Short,
        ) : CustomSnackbar(leadingIcon, colorPrimary, colorBackground, duration)
    }

    sealed class SnackOperationCard() : SnacklizType()
    sealed class SnackQuestionCard() : SnacklizType()
    sealed class SnackInputCard() : SnacklizType()
    sealed class VanillaSnackbar() : SnacklizType()
}

/*
public enum class SnackbarType(
    public val innerBoxType: SnackbarInnerBoxType = SnackbarInnerBoxType.ICON,
    public val icon : ImageVector,
    public val durationDelay : Long? = null,
    public val duration : SnackbarDuration = SnackbarDuration.Long,
    public val hasInnerProgressBar : Boolean = false,
    public val colorPrimary : Color,
    public val colorBackground : Color,
) {
    INFO(
        icon = Icons.Default.Info,
        colorBackground = silverChalice,
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
    /*LOADING(
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
    ),*/
}*/


/** Tipologia di card della snackbar */
enum class OperationCardType { DEFAULT, INFO, ERROR, SUCCESS }

/** Tipologia di azione della snackbar */
enum class DashCardActionType { CLOSE, RETRY, CUSTOM, NONE }

data class DashSnackbarInfo(
    val title: String? = null,
    val message: String? = null,
    val type: OperationCardType? = null,
    val duration: SnackbarDuration? = null,
    val actionType: DashCardActionType? = null,
    val onActionClick: (() -> Unit)? = null
)
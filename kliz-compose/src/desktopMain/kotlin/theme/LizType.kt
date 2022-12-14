package theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font

public val robotoFont : FontFamily = FontFamily(
    Font(resource = "font/roboto/Roboto-Thin.ttf", FontWeight.Thin),
    Font(resource = "font/roboto/Roboto-Regular.ttf", FontWeight.Normal),
    Font(resource = "font/roboto/Roboto-Medium.ttf", FontWeight.Medium),
    Font(resource = "font/roboto/Roboto-Light.ttf", FontWeight.Light),
    Font(resource = "font/roboto/Roboto-Bold.ttf", FontWeight.Bold),
    Font(resource = "font/roboto/Roboto-Black.ttf", FontWeight.Black),
)

public val gothamRoundedFont : FontFamily = FontFamily(
    Font(resource = "GothamRoundedMedium_21022.ttf", FontWeight.Normal),
    Font(resource = "GothamRoundedBold_21016.ttf", FontWeight.Bold),
    Font(resource = "GothamRoundedBook_21018.ttf", FontWeight.Light),
    Font(resource = "GothamRoundedLight_21020.ttf", FontWeight.Thin),
)
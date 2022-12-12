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
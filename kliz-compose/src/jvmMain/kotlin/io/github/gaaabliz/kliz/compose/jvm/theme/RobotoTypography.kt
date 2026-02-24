package io.github.gaaabliz.kliz.compose.jvm.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.sp

public val robotoFont : FontFamily = FontFamily(
    Font(resource = "font/roboto/Roboto-Thin.ttf", FontWeight.Thin),
    Font(resource = "font/roboto/Roboto-Regular.ttf", FontWeight.Normal),
    Font(resource = "font/roboto/Roboto-Medium.ttf", FontWeight.Medium),
    Font(resource = "font/roboto/Roboto-Light.ttf", FontWeight.Light),
    Font(resource = "font/roboto/Roboto-Bold.ttf", FontWeight.Bold),
    Font(resource = "font/roboto/Roboto-Black.ttf", FontWeight.Black),
)



public val robotoTypography : Typography = Typography(
    defaultFontFamily = robotoFont,
    h1 = TextStyle(
        fontFamily = robotoFont,
        fontWeight = FontWeight.W300,
        fontSize = 96.sp
    ),
    h2 = TextStyle(
        fontFamily = robotoFont,
        fontWeight = FontWeight.W300,
        fontSize = 60.sp
    ),
    h3 = TextStyle(
        fontFamily = robotoFont,
        fontWeight = FontWeight.W400,
        fontSize = 48.sp
    ),
    h4 = TextStyle(
        fontFamily = robotoFont,
        fontWeight = FontWeight.W400,
        fontSize = 34.sp
    ),
    h5 = TextStyle(
        fontFamily = robotoFont,
        fontWeight = FontWeight.W400,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = robotoFont,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = robotoFont,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = robotoFont,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = robotoFont,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = robotoFont,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = robotoFont,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = robotoFont,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = robotoFont,
        fontWeight = FontWeight.W400,
        fontSize = 10.sp
    )
)


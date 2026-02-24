package io.github.gaaabliz.kliz.compose.jvm.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.sp

public val gothamRoundedFont : FontFamily = FontFamily(
    Font(resource = "GothamRoundedMedium_21022.ttf", FontWeight.Normal),
    Font(resource = "GothamRoundedBold_21016.ttf", FontWeight.Bold),
    Font(resource = "GothamRoundedBook_21018.ttf", FontWeight.Light),
    Font(resource = "GothamRoundedLight_21020.ttf", FontWeight.Thin),
)

val gothamTypography = Typography(
    body1 = TextStyle(
        fontFamily = gothamRoundedFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    body2 = TextStyle(
        fontFamily = gothamRoundedFont,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = gothamRoundedFont,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    h2 = TextStyle(
        fontFamily = gothamRoundedFont,
        fontWeight = FontWeight.Bold,
        fontSize = 19.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = gothamRoundedFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    caption = TextStyle(
        fontFamily = gothamRoundedFont,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    ),
)

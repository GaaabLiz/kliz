package io.github.gaaabliz.kliz.compose.android.ui.image

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun LottieArea(
    jsonFile : Int = 0,
    size : Dp = 100.dp,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(jsonFile))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier
            .width(size)
            .height(size)
    )
}
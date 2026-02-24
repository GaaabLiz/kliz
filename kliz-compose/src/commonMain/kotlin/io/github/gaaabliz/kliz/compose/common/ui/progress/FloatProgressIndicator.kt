package io.github.gaaabliz.kliz.compose.common.ui.progress

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.gaaabliz.kliz.compose.common.theme.uniupo

@Composable
fun FloatProgressIndicator(
    visible : Boolean = true,
    progress : Float = 0.5f,
    color : Color = uniupo
) {
    if(visible) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp),
            color = color,
            progress = progress,
        )
    } else {
        Spacer(modifier = Modifier.height(4.dp))
    }
}
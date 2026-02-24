package io.github.gaaabliz.kliz.compose.jvm.ui.popup

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
public fun Tooltip1(text : String) {
    Surface(
        modifier = Modifier.shadow(4.dp),
        color = Color(255, 255, 210),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Composable
public fun Tooltip2(text : String) {
    Surface(
        modifier = Modifier.shadow(4.dp),
        color = Color(255, 255, 210),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = text.toString(),
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface
        )
    }
}
package it.gabliz.kliz.compose.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import it.gabliz.kliz.compose.theme.dodgerBlue

public object LizColorUtils {
    public fun getDefaultDebugIcon(): ImageVector = Icons.Filled.Code
    public fun getDefaultDebugColor() : Color = dodgerBlue
    public fun getDefaultOnSurfaceColor() : Color = Color.White
}
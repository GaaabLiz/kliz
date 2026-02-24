package io.github.gaaabliz.kliz.compose.common.ui.scaffold

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun LizTopAppBar(
    title: String,
    scrollState : ScrollState = rememberScrollState(),
    scrollingEnabled : Boolean = true,
    hasBackIcon : Boolean = false,
    elevation : Dp = 5.dp,
    iconForBack : ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
    actions: @Composable() (RowScope.() -> Unit) = {},
    onBackPressed : () -> Unit = {}
) {

    if(hasBackIcon) {
        TopAppBar(
            title = { Text(text = title, style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold) },
            modifier = Modifier
                .zIndex(2F)
                .scrollable(state = scrollState, orientation = Orientation.Vertical, enabled = scrollingEnabled)
                .focusable(true),
            navigationIcon = {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(iconForBack, contentDescription = "Back")
                }
            },
            actions = actions,
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            elevation = elevation
        )
    } else {
        TopAppBar(
            title = { Text(text = title, style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold) },
            modifier = Modifier
                .zIndex(2F)
                .scrollable(state = scrollState, orientation = Orientation.Vertical, enabled = scrollingEnabled)
                .focusable(true),
            actions = actions,
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            elevation = elevation
        )
    }
}
package io.github.gaaabliz.kliz.compose.common.ui_DA_SISTEMARE

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
public fun LizTopAppBar(
    title : String,
    interactionSource : MutableInteractionSource,
    focusRequester: FocusRequester = FocusRequester(),
    onKeyEvent : (KeyEvent) -> Boolean,
    onPointerInput : () -> Unit = {},
    onTopBarClick : () -> Unit = {},
    onNavIconClick : () -> Unit = {},
    navigationIcon : ImageVector? = null
) {
    val scrollState = rememberScrollState()
    TopAppBar(
        title = { Text(title) },
        modifier = Modifier
            .zIndex(2F)
            .scrollable(state = scrollState, orientation = Orientation.Vertical, enabled = false)
            .focusable(true)
            .focusRequester(focusRequester)
            .focusTarget()
            .onPreviewKeyEvent { onKeyEvent(it) }
            .pointerInput(Unit) { detectTapGestures { onPointerInput() } }
            .clickable(interactionSource = interactionSource, indication = null) { onTopBarClick() },
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 10.dp,
        navigationIcon = {
            if(navigationIcon != null) {
                IconButton(
                    onClick = { onNavIconClick() },
                    content = {
                        Icon(
                            imageVector = navigationIcon,
                            contentDescription = "Open Navigation Drawer"
                        )
                    }
                )
            }
        }
    )
}
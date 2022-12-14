import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import it.gabliz.kliz.compose.literal.Resolutions
import moe.tlaster.precompose.PreComposeWindow

fun main() = application {
    val icon = painterResource("logo.png")
    val interactionSource = remember { MutableInteractionSource() }


    Tray(
        icon = icon,
        menu = {
            Item("Help", onClick = {/* TODO */ })
            Item("Close", onClick = {   })
        }
    )

    PreComposeWindow(
        state = WindowState(position = WindowPosition(Alignment.Center)),
        resizable = true,
        onCloseRequest = { exitApplication() },
        title = "KLIZE EXAMPLE APP",
        //icon = icon
    ) {
        window.minimumSize = Resolutions.p720
    }
}

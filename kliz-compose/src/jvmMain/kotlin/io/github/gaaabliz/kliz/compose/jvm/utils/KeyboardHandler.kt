package io.github.gaaabliz.kliz.compose.jvm.utils

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.*
import org.apache.commons.lang3.time.DateUtils
import io.github.gaaabliz.kliz.common.util.TimeUtils
import java.text.ParseException

/**
 * Classe per la gestione degli shortcut da tastiera.
 * @author Gabriele Lizzos
 */
object KeyboardHandler  {

    fun handleDateFilterShortcut(
        event : KeyEvent,
        currentValue : String,
        onAction : (String) -> Unit
    ) : Boolean {

        return when {
            event.isCtrlPressed &&
            event.isShiftPressed &&
            event.key == Key.T &&
            event.type == KeyEventType.KeyUp -> {
                onAction(TimeUtils.localDateToString())
                true
            }
            event.isCtrlPressed &&
            event.isShiftPressed &&
            event.key == Key.Plus &&
            event.type == KeyEventType.KeyUp -> {
                try {
                    val currentDate = TimeUtils.AMERICAN_DATE_FORMAT.parse(currentValue)
                    val newDate = DateUtils.addDays(currentDate, 1)
                    onAction(TimeUtils.getAmericanStringDateFromDate(newDate))
                    true
                }catch (e : ParseException) {
                    //logger.warn("Unable to add day to current date because date text = '$currentValue'")
                    false
                }
            }
            event.isCtrlPressed &&
            event.isShiftPressed &&
            event.key == Key.Minus &&
            event.type == KeyEventType.KeyUp -> {
                try {
                    val currentDate = TimeUtils.AMERICAN_DATE_FORMAT.parse(currentValue)
                    val newDate = TimeUtils.subtractDaysFromDate(currentDate, 1)
                    onAction(TimeUtils.getAmericanStringDateFromDate(newDate))
                    true
                }catch (e : ParseException) {
                    //logger.warn("Unable to add day to current date because date text = '$currentValue'")
                    false
                }
            }
            else -> false
        }
    }
}
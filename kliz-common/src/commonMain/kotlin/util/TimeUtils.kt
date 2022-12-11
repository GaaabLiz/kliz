package util

import java.time.LocalDate
import java.time.LocalTime

object TimeUtils {

    fun getCurrentDate() : String {
        val now = LocalDate.now()
        return "${now.year}-${now.monthValue}-${now.dayOfMonth}"
    }

    fun getCurrentTime() : String {
        val now = LocalTime.now()
        return "${now.hour}:${now.minute}:${now.second}"
    }
}
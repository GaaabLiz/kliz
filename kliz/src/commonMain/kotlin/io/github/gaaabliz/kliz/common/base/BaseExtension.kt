@file:Suppress("NOTHING_TO_INLINE")
package io.github.gaaabliz.kliz.common.base

import java.io.File
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.regex.Pattern
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * @return A random UUID as a [String].
 */
inline val randomUUID: String get() = UUID.randomUUID().toString()

/**
 * @return The current time in milliseconds from the system clock.
 */
inline val currentTimeMillis: Long get() = System.currentTimeMillis()

/**
 * @return The name of the current executing [Thread] as a [String].
 */
inline val currentThreadName: String get() = Thread.currentThread().name

/**
 * @return The identifier of the current executing [Thread] as a [Long].
 */
inline val currentThreadID: Long get() = Thread.currentThread().id

/**
 * @return An empty [String].
 */
inline val emptyString : String get() = ""

/**
 * Safely converts a nullable [String] to a [Boolean].
 * @param default The default value to return if the conversion fails or the string is null. Defaults to `false`.
 * @return The converted [Boolean] or the default value.
 */
inline fun String?.safeToBoolean(default: Boolean = false): Boolean {
    return when {
        this.equals("true", ignoreCase = true) -> true
        this.equals("false", ignoreCase = true) -> false
        else -> default
    }
}

/**
 * Safely converts a nullable [String] to an [Int].
 * @param default The default value to return if the conversion fails. Defaults to `0`.
 * @return The converted [Int] or the default value.
 */
inline fun String?.safeToInt(default: Int = 0): Int = orEmpty().runCatching(String::toInt).getOrDefault(default)

/**
 * Safely converts a nullable [String] to a [Long].
 * @param default The default value to return if the conversion fails. Defaults to `0L`.
 * @return The converted [Long] or the default value.
 */
inline fun String?.safeToLong(default: Long = 0L): Long = orEmpty().runCatching(String::toLong).getOrDefault(default)

/**
 * Safely converts a nullable [String] to a [Float].
 * @param default The default value to return if the conversion fails. Defaults to `0f`.
 * @return The converted [Float] or the default value.
 */
inline fun String?.safeToFloat(default: Float = 0f): Float = orEmpty().runCatching(String::toFloat).getOrDefault(default)

/**
 * Safely converts a nullable [String] to a [Double].
 * @param default The default value to return if the conversion fails. Defaults to `0.0`.
 * @return The converted [Double] or the default value.
 */
inline fun String?.safeToDouble(default: Double = 0.0): Double = orEmpty().runCatching(String::toDouble).getOrDefault(default)

/**
 * Checks if the file does not exist.
 * @return `true` if the file does not exist, `false` otherwise.
 */
fun File.notExists(): Boolean = exists().not()


/**
 * Checks if the character sequence is a valid filename.
 * It verifies that the name does not contain any forbidden characters like `\ / : * ? " < > |`
 * and is not `.` or `..`.
 * @return `true` if the filename is valid, `false` otherwise.
 */
fun CharSequence.isValidFilename(): Boolean {
    val filenameRegex = Pattern.compile("[\\\\/:*?\"<>|\\x01-\\x1F\\x7F]", Pattern.CASE_INSENSITIVE)
    return !filenameRegex.matcher(this).find() && "." != this && ".." != this
}

/**
 * Checks if the character sequence is a valid URL.
 * It verifies that the sequence starts with `http://`, `https://`, or `ftp://`.
 * @return `true` if the URL is valid, `false` otherwise.
 */
fun CharSequence.isValidUrl(): Boolean {
    val urlRegex = Pattern.compile("^(https?|ftp)://.*$", Pattern.CASE_INSENSITIVE)
    return urlRegex.matcher(this).find()
}


/**
 * Checks if the character sequence is not null and not empty.
 * This function uses contracts to smart-cast the receiver to a non-null type on a `true` return.
 * @return `true` if the character sequence is not null and not empty, `false` otherwise.
 */
@OptIn(ExperimentalContracts::class)
inline fun CharSequence?.isNotNullOrEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrEmpty != null)
    }
    return this.isNullOrEmpty().not()
}

/**
 * Checks if all the provided booleans are `true`.
 * @param booleans A variable number of [Boolean] arguments.
 * @return `true` if all booleans are `true`, `false` as soon as one `false` is found.
 */
inline fun checkAllTrue (vararg booleans : Boolean) : Boolean {
    for (boolean in booleans) {
        if(!boolean) { return false }
    }
    return true
}

/**
 * Checks if any of the provided elements is `null`.
 * @param elements A variable number of elements of any type.
 * @return `true` if at least one element is `null`, `false` otherwise.
 */
inline fun <T> checkIfNullExist(vararg elements: T): Boolean {
    elements.forEach {
        if(it == null) return true
    }
    return false
}



/**
 * Formats the time part of a [LocalDateTime] into a "HH:mm:ss" string.
 * Ensures that each component (hour, minute, second) is zero-padded if less than 10.
 * @return The formatted time string.
 */
inline fun LocalDateTime.toProjectTime() : String {
    val hour = if(this.hour < 10) "0${this.hour}" else "${this.hour}"
    val minute = if(this.minute < 10) "0${this.minute}" else "${this.minute}"
    val second = if(this.second < 10) "0${this.second}" else "${this.second}"
    return "$hour:$minute:$second"
}

/**
 * Formats a [LocalTime] into a "HH:mm" string.
 * Ensures that hour and minute are zero-padded if less than 10.
 * Note: Seconds are ignored.
 * @return The formatted time string.
 */
inline fun LocalTime.toStringTime(): String {
    val hour = if(this.hour < 10) "0${this.hour}" else "${this.hour}"
    val minute = if(this.minute < 10) "0${this.minute}" else "${this.minute}"
    val second = if(this.second < 10) "0${this.second}" else "${this.second}"
    return "$hour:$minute"
}

/**
 * Parses a string in "HH:mm" format into a [LocalTime] object.
 * @receiver The string to parse, expected to be in "HH:mm" format.
 * @return The corresponding [LocalTime] object.
 * @throws NumberFormatException if the parts are not valid integers.
 * @throws IndexOutOfBoundsException if the string does not contain a ':'.
 */
inline fun String.toLocalTime(): LocalTime {
    val array = this.split(":")
    return LocalTime.of(array[0].toInt(), array[1].toInt())
}

/**
 * Formats the date part of a [LocalDateTime] into a "dd/MM/yyyy" string.
 * @return The formatted date string.
 */
inline fun LocalDateTime.toProjectDate(): String {
    return "${this.dayOfMonth}/${this.monthValue}/${this.year}"
}

/**
 * Formats a [LocalDateTime] into a "dd/MM/yyyy HH:mm:ss" string.
 * @param sep The separator to use between the date and time parts. Defaults to a single space.
 * @return The formatted date-time string.
 */
inline fun LocalDateTime.toProjectDateTime(sep: String = " ") : String {
    return "${this.toProjectDate()}$sep${this.toProjectTime()}"
}

/**
 * Formats a [LocalDateTime] into a string that includes the day of the week, date, and time.
 * Example format: "MONDAY 14/11/2025 15:30:00"
 * @param sep The separator to use between the parts. Defaults to a single space.
 * @return The formatted string with day of the week, date, and time.
 */
inline fun LocalDateTime.toDateTimeAndDayWeek(sep: String = " ") : String {
    return "${this.dayOfWeek}$sep${this.toProjectDate()}$sep${this.toProjectTime()}"
}

/**
 * Calculates the elapsed time from this [LocalDateTime] to the present moment and returns
 * a human-readable string representation in Italian.
 *
 * The output format varies based on the duration:
 * - More than a day: "X gg, Y ore e Z min. fa" (or variations if hours/minutes are zero)
 * - More than an hour: "X ore e Y min. fa"
 * - More than a minute: "X min. fa"
 * - Less than a minute: "Meno di un min. fa"
 *
 * @return A [String] describing the elapsed time in Italian.
 */
inline fun LocalDateTime.toElapsedTimeStringItaFromNow() : String {
    val currentTime = LocalDateTime.now()
    val hoursDiff = ChronoUnit.HOURS.between(this, currentTime)
    val minutesDiff = ChronoUnit.MINUTES.between(this, currentTime) % 60
    val daysDiff = ChronoUnit.DAYS.between(this, currentTime)

    return when {
        daysDiff > 0 -> {
            val remainingHours = hoursDiff - (daysDiff * 24)
            when {
                remainingHours > 0 && minutesDiff > 0 ->
                    "$daysDiff gg, $remainingHours ore e $minutesDiff min. fa"
                remainingHours > 0 ->
                    "$daysDiff gg e $remainingHours ore fa"
                minutesDiff > 0 ->
                    "$daysDiff gg e $minutesDiff min. fa"
                else ->
                    "$daysDiff gg fa"
            }
        }
        hoursDiff > 0 -> "$hoursDiff ore e $minutesDiff min. fa"
        minutesDiff > 0 -> "$minutesDiff min. fa"
        else -> "Meno di un min. fa"
    }
}



@file:Suppress("MemberVisibilityCanBePrivate")

package io.github.gaaabliz.kliz.common.util

import io.github.gaaabliz.kliz.common.util.DataUtils.toLowerAndCap
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.format.TextStyle
import java.util.*
import java.util.concurrent.ThreadLocalRandom

/**
 * Utility object for handling time and date operations.
 */
object TimeUtils {

    /** SimpleDateFormat for Italian date format (dd-MM-yyyy). */
    val ITALIAN_DATE_FORMAT = SimpleDateFormat("dd-MM-yyyy")
    /** SimpleDateFormat for American date format (yyyy-MM-dd). */
    val AMERICAN_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd")
    /** SimpleDateFormat for basic time format (HH:mm). */
    val BASIC_TIME_FORMAT = SimpleDateFormat("HH:mm")


    /** DateTimeFormatter for date format "dd-MM-yyyy". */
    val DATE_FORMATTER_LEFT_TO_RIGHT : DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    /** DateTimeFormatter for date format "yyyy-MM-dd". */
    val DATE_FORMATTER_RIGHT_TO_LEFT: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    /** DateTimeFormatter for time format "HH:mm". */
    val TIME_FORMATTER_TWO_SECTION : DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    /** DateTimeFormatter for time format "hh:mm a" (12-hour format with AM/PM). */
    val TIME_FORMATTER_TWO_SECTION_AM_PM : DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
    /** DateTimeFormatter for time format "HH:mm:ss". */
    val TIME_FORMATTER_THREE_SECTION : DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    /** DateTimeFormatter for LocalDateTime format "dd-MM-yyyy HH:mm:ss". */
    val LOCAL_DATE_TIME_FORMATTER : DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")

    /**
     * Gets the current date and time with the "Europe/Paris" time zone.
     * @return The current [ZonedDateTime] in "Europe/Paris".
     */
    fun getZonedNowTime(): ZonedDateTime {
        val z = ZoneId.of("Europe/Paris")
        return ZonedDateTime.now(z)
    }

    /**
     * Converts a [LocalDate] object to a string using a specified formatter.
     * @param date The [LocalDate] to convert. Defaults to [LocalDate.now()].
     * @param formatter The [DateTimeFormatter] to use. Defaults to [DATE_FORMATTER_LEFT_TO_RIGHT].
     * @return The formatted date as a [String].
     */
    fun localDateToString(
        date: LocalDate = LocalDate.now(),
        formatter: DateTimeFormatter = DATE_FORMATTER_LEFT_TO_RIGHT
    ): String {
        return date.format(formatter)
    }

    /**
     * Converts a [LocalDate] object to a string with day, month (capitalized), and year, using a specified separator and locale.
     * Example: `15 Gennaio 2023`
     * @param localDate The [LocalDate] to convert.
     * @param sep The separator to use between day, month, and year. Defaults to a space.
     * @param local The [Locale] to use for month name. Defaults to [Locale.ITALY].
     * @return The formatted date as a [String].
     */
    fun localDateToSepString(localDate: LocalDate, sep : String = " ", local : Locale = Locale.ITALY): String {
        val dd = localDate.dayOfMonth
        val mmTemp = localDate.month.getDisplayName(TextStyle.FULL, local)
        val mm = toLowerAndCap(mmTemp)
        val yyyy = localDate.year
        return "$dd$sep$mm$sep$yyyy"
    }

    /**
     * Converts a [LocalTime] object to a string using a specified formatter.
     * @param time The [LocalTime] to convert. Defaults to [LocalTime.now()].
     * @param formatter The [DateTimeFormatter] to use. Defaults to [TIME_FORMATTER_TWO_SECTION].
     * @return The formatted time as a [String].
     */
    fun localTimeToString(
        time: LocalTime = LocalTime.now(),
        formatter: DateTimeFormatter = TIME_FORMATTER_TWO_SECTION
    ): String {
        return time.format(formatter)
    }

    /**
     * Converts a [LocalDate] and [LocalTime] combination to a string using a specified formatter.
     * @param date The [LocalDate] part. Defaults to [LocalDate.now()].
     * @param time The [LocalTime] part. Defaults to [LocalTime.now()].
     * @param formatter The [DateTimeFormatter] to use. Defaults to [LOCAL_DATE_TIME_FORMATTER].
     * @return The formatted date and time as a [String].
     */
    fun localDateTimeToString(
        date: LocalDate = LocalDate.now(),
        time: LocalTime = LocalTime.now(),
        formatter: DateTimeFormatter = LOCAL_DATE_TIME_FORMATTER
    ): String {
        return LocalDateTime.of(date, time).format(formatter)
    }

    /**
     * Converts a [LocalDateTime] object to a string using a specified formatter.
     * @param localDateTime The [LocalDateTime] to convert.
     * @param formatter The [DateTimeFormatter] to use. Defaults to [LOCAL_DATE_TIME_FORMATTER].
     * @return The formatted date and time as a [String].
     */
    fun localDateTimeToString(
        localDateTime: LocalDateTime,
        formatter: DateTimeFormatter = LOCAL_DATE_TIME_FORMATTER
    ): String {
        return localDateTime.format(formatter)
    }

    /**
     * Converts a [LocalDate] object to a `java.util.Date` object.
     * @param dateToConvert The [LocalDate] to convert.
     * @return The converted `java.util.Date` object.
     */
    fun localDateToJavaDate(dateToConvert: LocalDate): Date {
        return Date.from(
            dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()
        )
    }

    /**
     * Converts a `java.util.Date` object to a [LocalDate] object.
     * @param dateToConvert The `java.util.Date` to convert.
     * @return The converted [LocalDate] object.
     */
    fun javaDateToLocalDate(dateToConvert: Date): LocalDate {
        return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }

    /**
     * Extracts the hour part from a time string in "HH:mm:ss" format.
     * @param time The time string.
     * @return The hour as a [String].
     * @throws IllegalArgumentException if the time string is not in "HH:mm:ss" format.
     */
    fun getHourFromTripleStringTime(time : String) : String {
        if(!time.contains(":")) {
            throw IllegalArgumentException("The time must be in the format HH:mm:ss")
        }
        val temp = time.split(":")
        return temp[0]
    }

    /**
     * Extracts the minute part from a time string in "HH:mm:ss" format.
     * @param time The time string.
     * @return The minute as a [String].
     * @throws IllegalArgumentException if the time string is not in "HH:mm:ss" format.
     */
    fun getMinuteFromTripleStringTime(time : String) : String {
        if(!time.contains(":")) {
            throw IllegalArgumentException("The time must be in the format HH:mm:ss")
        }
        val temp = time.split(":")
        return temp[1]
    }

    /**
     * Extracts the second part from a time string in "HH:mm:ss" format.
     * @param time The time string.
     * @return The second as a [String].
     * @throws IllegalArgumentException if the time string is not in "HH:mm:ss" format.
     */
    fun getSecondFromTripleStringTime(time : String) : String {
        if (!time.contains(":")) {
            throw IllegalArgumentException("The time must be in the format HH:mm:ss")
        }
        val temp = time.split(":")
        return temp[2]
    }

    /**
     * Formats a `java.util.Date` object into a two-section time string (HH:mm).
     * This method is deprecated.
     * @param date The `java.util.Date` to format.
     * @return The formatted time as a [String].
     */
    @Deprecated("This is the old method.")
    fun getSimpleStringTimeFromDate(date : Date) : String = BASIC_TIME_FORMAT.format(date)

    /**
     * Formats a `java.util.Date` object into an American date string (yyyy-MM-dd).
     * This method is deprecated.
     * @param date The `java.util.Date` to format.
     * @return The formatted date as a [String].
     */
    @Deprecated("This is the old method.")
    fun getAmericanStringDateFromDate(date : Date) : String = AMERICAN_DATE_FORMAT.format(date)

    /**
     * Subtracts a specified number of days from a `java.util.Date` object.
     * @param inputDate The original `java.util.Date`.
     * @param days The number of days to subtract.
     * @return A new `java.util.Date` object representing the date after subtraction.
     */
    fun subtractDaysFromDate(inputDate : Date, days : Int) : Date {
        val date = javaDateToLocalDate(inputDate)
        val newLocalDate = date.minusDays(days.toLong())
        return localDateToJavaDate(newLocalDate)
    }

    /**
     * Converts an American date string ("yyyy-MM-dd") to an Italian date string ("dd-MM-yyyy").
     * This method is deprecated.
     * @param string The American date string.
     * @return The converted Italian date string.
     */
    @Deprecated("This is the old method.")
    fun convertAmericanDateStringToItalian(string : String) : String {
        val arr = string.split("-")
        return arr[2] + "-" + arr[1] + "-" + arr[0]
    }

    /**
     * Converts an Italian date string ("dd-MM-yyyy") to an American date string ("yyyy-MM-dd").
     * This method is deprecated.
     * @param string The Italian date string.
     * @return The converted American date string.
     */
    @Deprecated("This is the old method.")
    fun convertItalianDateStringToAmerican(string : String) : String {
        val arr = string.split("-")
        return arr[2] + "-" + arr[1] + "-" + arr[0]
    }

    /**
     * Generates a random `java.util.Date` object within a specified range.
     * @param startInclusive The inclusive start date. Defaults to 1970-01-01.
     * @param endExclusive The exclusive end date. Defaults to 2022-01-01.
     * @return A randomly generated `java.util.Date`.
     */
    fun genRandomJavaDate(
        startInclusive: Date = localDateToJavaDate(LocalDate.of(1970, 1, 1)),
        endExclusive: Date = localDateToJavaDate(LocalDate.of(2022, 1, 1))
    ): Date {
        val startMillis = startInclusive.time
        val endMillis = endExclusive.time
        val randomMillisSinceEpoch: Long = ThreadLocalRandom
            .current()
            .nextLong(startMillis, endMillis)
        return Date(randomMillisSinceEpoch)
    }

    /**
     * Parses an Italian date string ("dd-MM-yyyy") into a `java.util.Date` object.
     * @param date The Italian date string.
     * @return The parsed `java.util.Date` object.
     */
    fun getDateObjectFromItaString(date: String): Date = ITALIAN_DATE_FORMAT.parse(date)

    /**
     * Parses an American date string ("yyyy-MM-dd") into a `java.util.Date` object.
     * @param date The American date string.
     * @return The parsed `java.util.Date` object.
     */
    fun getDateObjectFromUsaString(date: String): Date = AMERICAN_DATE_FORMAT.parse(date)

    /**
     * Checks if a string represents a valid [LocalTime].
     * @param string The string to check.
     * @return `true` if the string is a valid [LocalTime], `false` otherwise.
     */
    @Suppress("CheckResult")
    fun isValidLocalTimeString(string : String) : Boolean {
        return try {
            LocalTime.parse(string)
            true
        } catch (e: DateTimeParseException) {
            false
        } catch (e: NullPointerException) {
            false
        }
    }

    /**
     * Converts an ISO formatted date string (e.g., "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") to a [LocalDateTime] object.
     * Note: [LocalDateTime] is timezone-naive. The 'Z' (Zulu time) in the format implies UTC,
     * but this function parses it into a local date-time without timezone information.
     * @param isoDate The ISO formatted date string.
     * @return The parsed [LocalDateTime] object.
     */
    fun convertIsoToLocalDateTime(isoDate: String): LocalDateTime {
        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        return LocalDateTime.parse(isoDate, format)
    }

    /**
     * Converts a [LocalDateTime] object to an ISO formatted date string (e.g., "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").
     * @param localDateTime The [LocalDateTime] to convert.
     * @return The formatted ISO date string.
     */
    fun convertLocalDateTimeToIso(localDateTime: LocalDateTime): String {
        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        return localDateTime.format(format)
    }

    /**
     * Checks if a [LocalDateTime] is strictly between two other [LocalDateTime]s (exclusive).
     * @param dateTimeToCheck The date-time to check.
     * @param startDateTime The start date-time.
     * @param endDateTime The end date-time.
     * @return `true` if [dateTimeToCheck] is after [startDateTime] and before [endDateTime], `false` otherwise.
     */
    fun isLocalDateTimeBetween(dateTimeToCheck: LocalDateTime, startDateTime: LocalDateTime, endDateTime: LocalDateTime): Boolean {
        return dateTimeToCheck.isAfter(startDateTime) && dateTimeToCheck.isBefore(endDateTime)
    }

    /**
     * Checks if a [LocalTime] is strictly between two other [LocalTime]s (exclusive).
     * @param dateTimeToCheck The time to check.
     * @param startTime The start time.
     * @param endDateTime The end time.
     * @return `true` if [dateTimeToCheck] is after [startTime] and before [endDateTime], `false` otherwise.
     */
    fun isLocalTimeBetween(dateTimeToCheck: LocalTime, startTime: LocalTime, endDateTime: LocalTime): Boolean {
        return dateTimeToCheck.isAfter(startTime) && dateTimeToCheck.isBefore(endDateTime)
    }

}



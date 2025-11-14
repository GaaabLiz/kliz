package io.github.gaaabliz.kliz.common.util

import org.apache.commons.lang3.RandomStringUtils
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.ThreadLocalRandom

/**
 * A utility object for generating various types of random data.
 */
object GenUtils {

    /**
     * Generates a random alphanumeric string of a specified length.
     * @param length The desired length of the string.
     * @return A random alphanumeric [String].
     */
    fun generateRandomId(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return(1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    /**
     * Generates a random alphanumeric string using Apache Commons Lang.
     * @param length The desired length of the string.
     * @return A random alphanumeric [String].
     */
    fun generateRandomStringApache( length: Int ) : String = RandomStringUtils.randomAlphanumeric(length)

    /**
     * Generates a random boolean value.
     * @return A random [Boolean] (`true` or `false`).
     */
    fun generateRandomBoolean() : Boolean = (0..1).random() == 1

    /**
     * Generates a random integer within a specified range (inclusive).
     * @param min The minimum value.
     * @param max The maximum value.
     * @return A random [Int] between min and max.
     */
    fun generateRandomInt(min: Int, max: Int) : Int = (min..max).random()

    /**
     * Generates a random long within a specified range (inclusive).
     * @param min The minimum value.
     * @param max The maximum value.
     * @return A random [Long] between min and max.
     */
    fun generateRandomLong(min: Long, max: Long) : Long = (min..max).random()

    /**
     * Generates a random double within a specified range.
     * @param min The minimum value.
     * @param max The maximum value.
     * @return A random [Double] between min and max.
     */
    fun generateRandomDouble(min: Double, max: Double) : Double = min + (Math.random() * (max - min))

    private fun generateRandomDoubleBetween(start: Double, end: Double): Double {
        return ThreadLocalRandom.current().nextDouble(start, end)
    }

    /**
     * Generates a random image URL from Unsplash.
     * @param imageWidth The desired width of the image.
     * @param imageHeight The desired height of the image.
     * @return A [String] containing the URL to a random image.
     */
    fun generateRandomImageUrl(imageWidth : Int , imageHeight : Int) : String {
        val randomInt = generateRandomInt(1, 100)
        return "https://source.unsplash.com/random/${imageWidth}x${imageHeight}?sig=${randomInt}"
    }

    /**
     * Generates a random [LocalDate] between 1900-01-01 and 2021-01-01.
     * @return A random [LocalDate].
     */
    fun generateRandomLocalDate() : LocalDate {
        val minDay = LocalDate.of(1900, 1, 1).toEpochDay()
        val maxDay = LocalDate.of(2021, 1, 1).toEpochDay()
        val randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay)
        return LocalDate.ofEpochDay(randomDay)
    }

    /**
     * Generates a random [LocalDateTime] between 1900-01-01 and 2021-01-01.
     * @return A random [LocalDateTime].
     */
    fun generateRandomLocalDateTime() : LocalDateTime {
        val minDay = LocalDateTime.of(1900, 1, 1, 0, 0, 0).toEpochSecond(ZoneOffset.UTC)
        val maxDay = LocalDateTime.of(2021, 1, 1, 0, 0, 0).toEpochSecond(ZoneOffset.UTC)
        val randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay)
        return LocalDateTime.ofEpochSecond(randomDay, 0, ZoneOffset.UTC)
    }

    /**
     * Generates a random [LocalDate] that occurs after today.
     * The generated date's day of the week may not match the input [dayOfWeek].
     * @param dayOfWeek The target day of the week to calculate the initial offset.
     * @return A random [LocalDate] in the near future.
     */
    fun genRandomLocalDateFromWeekDay(dayOfWeek: DayOfWeek) : LocalDate {
        val currentDayOfWeek = LocalDate.now().dayOfWeek
        val daysToAdd = (dayOfWeek.value - currentDayOfWeek.value + 7) % 7
        val randomDays = ThreadLocalRandom.current().nextInt(0, 7)
        val totalDaysToAdd = daysToAdd + randomDays
        return LocalDate.now().plusDays(totalDaysToAdd.toLong())
    }
}
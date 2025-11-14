package io.github.gaaabliz.kliz.util

import io.github.gaaabliz.kliz.common.util.GenUtils
import org.apache.commons.lang3.StringUtils
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.test.*

class GenUtilsTest {

    @Test
    fun `generateRandomId returns string of correct length and content`() {
        val length = 10
        val randomId = GenUtils.generateRandomId(length)
        assertEquals(length, randomId.length)
        assertTrue(randomId.all { it.isLetterOrDigit() })
    }

    @Test
    fun `generateRandomStringApache returns string of correct length and content`() {
        val length = 12
        val randomString = GenUtils.generateRandomStringApache(length)
        assertEquals(length, randomString.length)
        assertTrue(StringUtils.isAlphanumeric(randomString))
    }

    @Test
    fun `generateRandomInt returns value within range`() {
        val min = 10
        val max = 20
        repeat(100) {
            val randomInt = GenUtils.generateRandomInt(min, max)
            assertTrue(randomInt in min..max, "Generated int $randomInt was not in range $min..$max")
        }
    }

    @Test
    fun `generateRandomLong returns value within range`() {
        val min = 100L
        val max = 200L
        repeat(100) {
            val randomLong = GenUtils.generateRandomLong(min, max)
            assertTrue(randomLong in min..max, "Generated long $randomLong was not in range $min..$max")
        }
    }

    @Test
    fun `generateRandomDouble returns value within range`() {
        val min = 10.0
        val max = 20.0
        repeat(100) {
            val randomDouble = GenUtils.generateRandomDouble(min, max)
            assertTrue(randomDouble >= min && randomDouble < max, "Generated double $randomDouble was not in range [$min, $max)")
        }
    }

    @Test
    fun `generateRandomImageUrl returns a valid URL format`() {
        val width = 800
        val height = 600
        val imageUrl = GenUtils.generateRandomImageUrl(width, height)
        assertTrue(imageUrl.startsWith("https://source.unsplash.com/random/"), "URL should start with the Unsplash base URL")
        assertTrue(imageUrl.contains("${width}x${height}"), "URL should contain the correct dimensions")
        assertTrue(imageUrl.contains("?sig="), "URL should contain the sig parameter")
    }

    @Test
    fun `generateRandomLocalDate returns a date within the specified range`() {
        val minDate = LocalDate.of(1900, 1, 1)
        val maxDate = LocalDate.of(2021, 1, 1)
        repeat(100) {
            val randomDate = GenUtils.generateRandomLocalDate()
            assertFalse(randomDate.isBefore(minDate), "Date $randomDate should not be before $minDate")
            assertTrue(randomDate.isBefore(maxDate), "Date $randomDate should be before $maxDate")
        }
    }

    @Test
    fun `generateRandomLocalDateTime returns a datetime within the specified range`() {
        val minDateTime = LocalDateTime.of(1900, 1, 1, 0, 0, 0)
        val maxDateTime = LocalDateTime.of(2021, 1, 1, 0, 0, 0)
        repeat(100) {
            val randomDateTime = GenUtils.generateRandomLocalDateTime()
            assertFalse(randomDateTime.isBefore(minDateTime), "DateTime $randomDateTime should not be before $minDateTime")
            assertTrue(randomDateTime.isBefore(maxDateTime), "DateTime $randomDateTime should be before $maxDateTime")
        }
    }

    @Test
    fun `genRandomLocalDateFromWeekDay returns a date in the near future`() {
        val today = LocalDate.now()
        val randomDate = GenUtils.genRandomLocalDateFromWeekDay(DayOfWeek.FRIDAY)
        assertTrue(randomDate.isAfter(today) || randomDate.isEqual(today), "Generated date should be today or in the future")
        // The logic is a bit complex to test the exact day of week without duplicating it,
        // so we just check if it's within the next 13 days, which is the max possible offset.
        val maxPossibleDate = today.plusDays(13)
        assertTrue(randomDate.isBefore(maxPossibleDate) || randomDate.isEqual(maxPossibleDate), "Generated date is too far in the future")
    }
}

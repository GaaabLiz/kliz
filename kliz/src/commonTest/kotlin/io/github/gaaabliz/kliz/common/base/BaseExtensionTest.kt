package io.github.gaaabliz.kliz.base

import io.github.gaaabliz.kliz.common.base.checkAllTrue
import io.github.gaaabliz.kliz.common.base.checkIfNullExist
import io.github.gaaabliz.kliz.common.base.emptyString
import io.github.gaaabliz.kliz.common.base.isNotNullOrEmpty
import io.github.gaaabliz.kliz.common.base.isValidFilename
import io.github.gaaabliz.kliz.common.base.isValidUrl
import io.github.gaaabliz.kliz.common.base.notExists
import io.github.gaaabliz.kliz.common.base.randomUUID
import io.github.gaaabliz.kliz.common.base.safeToBoolean
import io.github.gaaabliz.kliz.common.base.safeToDouble
import io.github.gaaabliz.kliz.common.base.safeToFloat
import io.github.gaaabliz.kliz.common.base.safeToInt
import io.github.gaaabliz.kliz.common.base.safeToLong
import io.github.gaaabliz.kliz.common.base.toDateTimeAndDayWeek
import io.github.gaaabliz.kliz.common.base.toLocalTime
import io.github.gaaabliz.kliz.common.base.toProjectDate
import io.github.gaaabliz.kliz.common.base.toProjectDateTime
import io.github.gaaabliz.kliz.common.base.toProjectTime
import io.github.gaaabliz.kliz.common.base.toStringTime
import java.io.File
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.test.*

class BaseExtensionTest {

    @Test
    fun `randomUUID must return a valid UUID string`() {
        val uuid = randomUUID
        val uuidRegex = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"
        assertTrue(uuid.matches(Regex(uuidRegex)), "Generated string '$uuid' is not a valid UUID.")
    }

    @Test
    fun `emptyString must be an empty string`() {
        assertEquals("", emptyString)
    }

    @Test
    fun `safeToBoolean should correctly convert strings or return default`() {
        assertEquals(true, "true".safeToBoolean())
        assertEquals(false, "false".safeToBoolean())
        assertEquals(false, "invalid".safeToBoolean())
        assertEquals(false, null.safeToBoolean())
        assertEquals(true, "invalid".safeToBoolean(default = true))
        assertEquals(true, null.safeToBoolean(default = true))
    }

    @Test
    fun `safeToInt should correctly convert strings or return default`() {
        assertEquals(123, "123".safeToInt())
        assertEquals(-45, "-45".safeToInt())
        assertEquals(0, "abc".safeToInt())
        assertEquals(0, null.safeToInt())
        assertEquals(99, "abc".safeToInt(default = 99))
        assertEquals(99, null.safeToInt(default = 99))
    }

    @Test
    fun `safeToLong should correctly convert strings or return default`() {
        assertEquals(123L, "123".safeToLong())
        assertEquals(-45L, "-45".safeToLong())
        assertEquals(0L, "abc".safeToLong())
        assertEquals(0L, null.safeToLong())
        assertEquals(99L, "abc".safeToLong(default = 99L))
        assertEquals(99L, null.safeToLong(default = 99L))
    }

    @Test
    fun `safeToFloat should correctly convert strings or return default`() {
        assertEquals(123.45f, "123.45".safeToFloat())
        assertEquals(-45.5f, "-45.5".safeToFloat())
        assertEquals(0f, "abc".safeToFloat())
        assertEquals(0f, null.safeToFloat())
        assertEquals(99.9f, "abc".safeToFloat(default = 99.9f))
        assertEquals(99.9f, null.safeToFloat(default = 99.9f))
    }

    @Test
    fun `safeToDouble should correctly convert strings or return default`() {
        assertEquals(123.45, "123.45".safeToDouble())
        assertEquals(-45.5, "-45.5".safeToDouble())
        assertEquals(0.0, "abc".safeToDouble())
        assertEquals(0.0, null.safeToDouble())
        assertEquals(99.9, "abc".safeToDouble(default = 99.9))
        assertEquals(99.9, null.safeToDouble(default = 99.9))
    }

    @Test
    fun `notExists should be true for non-existent file and false for existing one`() {
        val nonExistentFile = File("this_file_definitely_does_not_exist_12345.tmp")
        val existingFile = File.createTempFile("kliz_test_", ".tmp")
        try {
            assertTrue(nonExistentFile.notExists(), "notExists() should be true for a non-existent file.")
            assertFalse(existingFile.notExists(), "notExists() should be false for an existing file.")
        } finally {
            existingFile.delete()
        }
    }

    @Test
    fun `isValidFilename should validate filenames correctly`() {
        assertTrue("file.txt".isValidFilename())
        assertTrue("document-1_final".isValidFilename())
        assertFalse("file/path".isValidFilename(), "Should be invalid due to '/'")
        assertFalse("file:path".isValidFilename(), "Should be invalid due to ':'")
        assertFalse("file*path".isValidFilename(), "Should be invalid due to '*'")
        assertFalse("file?path".isValidFilename(), "Should be invalid due to '?'")
        assertFalse("file\"path".isValidFilename(), "Should be invalid due to '\"'")
        assertFalse("file<path".isValidFilename(), "Should be invalid due to '<'")
        assertFalse("file>path".isValidFilename(), "Should be invalid due to '>'")
        assertFalse("file|path".isValidFilename(), "Should be invalid due to '|'")
        assertFalse(".".isValidFilename(), "Should be invalid for '.'")
        assertFalse("..".isValidFilename(), "Should be invalid for '..'")
    }

    @Test
    fun `isValidUrl should validate URLs correctly`() {
        assertTrue("http://example.com".isValidUrl())
        assertTrue("https://www.example.com/path?query=1".isValidUrl())
        assertTrue("ftp://user:password@host.com:21/resource".isValidUrl())
        assertFalse("www.example.com".isValidUrl(), "Should be invalid without protocol")
        assertFalse("just some text".isValidUrl())
        assertFalse("".isValidUrl())
    }

    @Test
    fun `isNotNullOrEmpty should work for non-empty, empty, and null CharSequence`() {
        assertTrue("hello".isNotNullOrEmpty())
        val nonNullString: CharSequence = "I am not null"
        if (nonNullString.isNotNullOrEmpty()) {
            assertEquals(13, nonNullString.length) // Smart cast check
        }
        assertFalse("".isNotNullOrEmpty())
        assertFalse(null.isNotNullOrEmpty())
    }

    @Test
    fun `checkAllTrue should return true only if all arguments are true`() {
        assertTrue(checkAllTrue(true, true, true))
        assertFalse(checkAllTrue(true, false, true))
        assertFalse(checkAllTrue(false, false))
        assertTrue(checkAllTrue(), "Should be true for no arguments")
    }

    @Test
    fun `checkIfNullExist should return true if any element is null`() {
        assertFalse(checkIfNullExist("a", 1, true), "Should be false when no nulls are present")
        assertTrue(checkIfNullExist("a", null, true), "Should be true when one null is present")
        assertTrue(checkIfNullExist(null, null), "Should be true when multiple nulls are present")
        assertFalse(checkIfNullExist<Any?>(), "Should be false for no arguments")
    }

    @Test
    fun `toProjectTime should format LocalDateTime correctly`() {
        val dateTime1 = LocalDateTime.of(2023, 5, 1, 8, 9, 7)
        assertEquals("08:09:07", dateTime1.toProjectTime())

        val dateTime2 = LocalDateTime.of(2023, 12, 20, 18, 19, 27)
        assertEquals("18:19:27", dateTime2.toProjectTime())
    }

    @Test
    fun `toStringTime should format LocalTime correctly ignoring seconds`() {
        val time1 = LocalTime.of(8, 9, 59)
        assertEquals("08:09", time1.toStringTime())

        val time2 = LocalTime.of(18, 19, 27)
        assertEquals("18:19", time2.toStringTime())
    }

    @Test
    fun `toLocalTime should parse a valid time string`() {
        val expectedTime = LocalTime.of(14, 30)
        assertEquals(expectedTime, "14:30".toLocalTime())
        assertFails { "invalid-time".toLocalTime() }
    }

    @Test
    fun `toProjectDate should format LocalDateTime correctly`() {
        val dateTime = LocalDateTime.of(2024, 7, 21, 10, 0)
        assertEquals("21/7/2024", dateTime.toProjectDate())
    }

    @Test
    fun `toProjectDateTime should format LocalDateTime with default and custom separator`() {
        val dateTime = LocalDateTime.of(2024, 1, 2, 3, 4, 5)
        assertEquals("2/1/2024 03:04:05", dateTime.toProjectDateTime())
        assertEquals("2/1/2024_-_03:04:05", dateTime.toProjectDateTime(sep = "_-_" ))
    }

    @Test
    fun `toDateTimeAndDayWeek should include the day of the week`() {
        // This date, 2025-11-14, is a Friday
        val dateTime = LocalDateTime.of(2025, 11, 14, 10, 30, 0)
        assertEquals("FRIDAY 14/11/2025 10:30:00", dateTime.toDateTimeAndDayWeek())
    }
}

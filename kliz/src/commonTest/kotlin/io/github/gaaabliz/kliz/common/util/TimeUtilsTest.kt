package io.github.gaaabliz.kliz.common.util

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.test.*

class TimeUtilsTest {

    @Test
    fun `getZonedNowTime should return current time in Europe_Paris zone`() {
        val zonedDateTime = TimeUtils.getZonedNowTime()
        assertEquals(ZoneId.of("Europe/Paris"), zonedDateTime.zone)
        // Cannot assert exact time, but can check it's recent
        assertTrue(zonedDateTime.toEpochSecond() <= ZonedDateTime.now(ZoneId.of("Europe/Paris")).toEpochSecond())
        assertTrue(zonedDateTime.toEpochSecond() >= ZonedDateTime.now(ZoneId.of("Europe/Paris")).minusSeconds(5).toEpochSecond())
    }

    @Test
    fun `localDateToString should format LocalDate correctly with default and custom formatter`() {
        val date = LocalDate.of(2023, 1, 15)
        assertEquals("15-01-2023", TimeUtils.localDateToString(date))
        assertEquals("2023-01-15", TimeUtils.localDateToString(date, TimeUtils.DATE_FORMATTER_RIGHT_TO_LEFT))
    }

    @Test
    fun `localDateToSepString should format LocalDate with custom separator and locale`() {
        val date = LocalDate.of(2023, 1, 15)
        assertEquals("15 Gennaio 2023", TimeUtils.localDateToSepString(date))
        assertEquals("15-Gennaio-2023", TimeUtils.localDateToSepString(date, sep = "-"))
        assertEquals("15 January 2023", TimeUtils.localDateToSepString(date, local = Locale.ENGLISH))
    }

    @Test
    fun `localTimeToString should format LocalTime correctly with default and custom formatter`() {
        val time = LocalTime.of(14, 30, 5)
        assertEquals("14:30", TimeUtils.localTimeToString(time))
        assertEquals("14:30:05", TimeUtils.localTimeToString(time, TimeUtils.TIME_FORMATTER_THREE_SECTION))
        assertEquals("02:30 PM", TimeUtils.localTimeToString(time, TimeUtils.TIME_FORMATTER_TWO_SECTION_AM_PM))
    }

    @Test
    fun `localDateTimeToString (LocalDate, LocalTime) should format correctly with default and custom formatter`() {
        val date = LocalDate.of(2023, 1, 15)
        val time = LocalTime.of(14, 30, 5)
        assertEquals("15-01-2023 14:30:05", TimeUtils.localDateTimeToString(date, time))
        val customFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")
        assertEquals("2023/01/15 14:30", TimeUtils.localDateTimeToString(date, time, customFormatter))
    }

    @Test
    fun `localDateTimeToString (LocalDateTime) should format correctly with default and custom formatter`() {
        val dateTime = LocalDateTime.of(2023, 1, 15, 14, 30, 5)
        assertEquals("15-01-2023 14:30:05", TimeUtils.localDateTimeToString(dateTime))
        val customFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")
        assertEquals("2023/01/15 14:30", TimeUtils.localDateTimeToString(dateTime, customFormatter))
    }

    @Test
    fun `localDateToJavaDate and javaDateToLocalDate should convert correctly`() {
        val localDate = LocalDate.of(2023, 1, 15)
        val javaDate = TimeUtils.localDateToJavaDate(localDate)
        val convertedLocalDate = TimeUtils.javaDateToLocalDate(javaDate)
        assertEquals(localDate, convertedLocalDate)
    }

    @Test
    fun `getHourFromTripleStringTime should extract hour`() {
        assertEquals("14", TimeUtils.getHourFromTripleStringTime("14:30:05"))
        assertFailsWith<IllegalArgumentException> { TimeUtils.getHourFromTripleStringTime("143005") }
    }

    @Test
    fun `getMinuteFromTripleStringTime should extract minute`() {
        assertEquals("30", TimeUtils.getMinuteFromTripleStringTime("14:30:05"))
        assertFailsWith<IllegalArgumentException> { TimeUtils.getMinuteFromTripleStringTime("143005") }
    }

    @Test
    fun `getSecondFromTripleStringTime should extract second`() {
        assertEquals("05", TimeUtils.getSecondFromTripleStringTime("14:30:05"))
        assertFailsWith<IllegalArgumentException> { TimeUtils.getSecondFromTripleStringTime("143005") }
    }

    @Test
    fun `getSimpleStringTimeFromDate should format Date to HH_mm`() {
        val calendar = Calendar.getInstance().apply {
            set(2023, Calendar.JANUARY, 15, 14, 30, 0)
        }
        assertEquals("14:30", TimeUtils.getSimpleStringTimeFromDate(calendar.time))
    }

    @Test
    fun `getAmericanStringDateFromDate should format Date to yyyy_MM_dd`() {
        val calendar = Calendar.getInstance().apply {
            set(2023, Calendar.JANUARY, 15, 14, 30, 0)
        }
        assertEquals("2023-01-15", TimeUtils.getAmericanStringDateFromDate(calendar.time))
    }

    @Test
    fun `subtractDaysFromDate should subtract days correctly`() {
        val originalDate = TimeUtils.localDateToJavaDate(LocalDate.of(2023, 1, 15))
        val newDate = TimeUtils.subtractDaysFromDate(originalDate, 5)
        val expectedDate = LocalDate.of(2023, 1, 10)
        assertEquals(expectedDate, TimeUtils.javaDateToLocalDate(newDate))

        val addDaysDate = TimeUtils.subtractDaysFromDate(originalDate, -5)
        val expectedAddDate = LocalDate.of(2023, 1, 20)
        assertEquals(expectedAddDate, TimeUtils.javaDateToLocalDate(addDaysDate))
    }

    @Test
    fun `convertAmericanDateStringToItalian should convert date string`() {
        assertEquals("15-01-2023", TimeUtils.convertAmericanDateStringToItalian("2023-01-15"))
    }

    @Test
    fun `convertItalianDateStringToAmerican should convert date string`() {
        assertEquals("2023-01-15", TimeUtils.convertItalianDateStringToAmerican("15-01-2023"))
    }

    @Test
    fun `genRandomJavaDate should generate date within range`() {
        val start = TimeUtils.localDateToJavaDate(LocalDate.of(2000, 1, 1))
        val end = TimeUtils.localDateToJavaDate(LocalDate.of(2000, 1, 3)) // Exclusive
        repeat(100) {
            val randomDate = TimeUtils.genRandomJavaDate(start, end)
            assertTrue(randomDate.after(start) || randomDate.equals(start))
            assertTrue(randomDate.before(end))
        }
    }

    @Test
    fun `getDateObjectFromItaString should parse Italian date string`() {
        val date = TimeUtils.getDateObjectFromItaString("15-01-2023")
        val calendar = Calendar.getInstance().apply {
            set(2023, Calendar.JANUARY, 15, 0, 0, 0)
            set(Calendar.MILLISECOND, 0)
        }
        assertEquals(calendar.time, date)
    }

    @Test
    fun `getDateObjectFromUsaString should parse American date string`() {
        val date = TimeUtils.getDateObjectFromUsaString("2023-01-15")
        val calendar = Calendar.getInstance().apply {
            set(2023, Calendar.JANUARY, 15, 0, 0, 0)
            set(Calendar.MILLISECOND, 0)
        }
        assertEquals(calendar.time, date)
    }

    @Test
    fun `isValidLocalTimeString should validate time string`() {
        assertTrue(TimeUtils.isValidLocalTimeString("14:30"))
        assertTrue(TimeUtils.isValidLocalTimeString("00:00"))
        assertFalse(TimeUtils.isValidLocalTimeString("25:00"))
        assertFalse(TimeUtils.isValidLocalTimeString("14:65"))
        assertFalse(TimeUtils.isValidLocalTimeString("invalid"))
    }

    @Test
    fun `convertIsoToLocalDateTime and convertLocalDateTimeToIso should convert correctly`() {
        val isoString = "2023-01-15T14:30:00.000Z"
        val localDateTime = TimeUtils.convertIsoToLocalDateTime(isoString)
        val convertedIsoString = TimeUtils.convertLocalDateTimeToIso(localDateTime)
        assertEquals(isoString, convertedIsoString)
    }

    @Test
    fun `isLocalDateTimeBetween should check correctly`() {
        val start = LocalDateTime.of(2023, 1, 1, 10, 0)
        val end = LocalDateTime.of(2023, 1, 1, 12, 0)

        assertTrue(TimeUtils.isLocalDateTimeBetween(LocalDateTime.of(2023, 1, 1, 11, 0), start, end))
        assertFalse(TimeUtils.isLocalDateTimeBetween(start, start, end))
        assertFalse(TimeUtils.isLocalDateTimeBetween(end, start, end))
        assertFalse(TimeUtils.isLocalDateTimeBetween(LocalDateTime.of(2023, 1, 1, 9, 0), start, end))
    }

    @Test
    fun `isLocalTimeBetween should check correctly`() {
        val start = LocalTime.of(10, 0)
        val end = LocalTime.of(12, 0)

        assertTrue(TimeUtils.isLocalTimeBetween(LocalTime.of(11, 0), start, end))
        assertFalse(TimeUtils.isLocalTimeBetween(start, start, end))
        assertFalse(TimeUtils.isLocalTimeBetween(end, start, end))
        assertFalse(TimeUtils.isLocalTimeBetween(LocalTime.of(9, 0), start, end))
    }
}

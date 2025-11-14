package io.github.gaaabliz.kliz.common.model

import io.github.gaaabliz.kliz.common.util.GenUtils
import java.time.DayOfWeek
import kotlin.test.*

class ModelTest {

    @Test
    fun `DayOfWork enum properties should be correct`() {
        assertEquals("L", DayOfWork.LUNEDI.code)
        assertEquals("Lunedì", DayOfWork.LUNEDI.string)
        assertEquals(DayOfWeek.MONDAY, DayOfWork.LUNEDI.dayOfTheWeek)

        assertEquals("END", DayOfWork.WEEKEND.code)
        assertEquals("Week-end", DayOfWork.WEEKEND.string)
        assertEquals(DayOfWeek.SUNDAY, DayOfWork.WEEKEND.dayOfTheWeek)
    }

    @Test
    fun `Log data class should generate random ID of length 20`() {
        val log = Log(date = "2023-01-01", time = "12:00", type = LogType.INFO, message = "Test log")
        assertEquals(20, log.id.length)
        assertTrue(log.id.all { it.isLetterOrDigit() })
    }

    @Test
    fun `TextFileSuffix enum properties should be correct`() {
        assertEquals(".json", TextFileSuffix.JSON.suffix)
        assertEquals(".txt", TextFileSuffix.TXT.suffix)
        assertEquals(".csv", TextFileSuffix.CSV.suffix)
        assertEquals(".md", TextFileSuffix.MARKDOWN.suffix)
    }

    @Test
    fun `LoginDetails someDetailsEmpty should return true for empty username`() {
        val login = LoginDetails("", "password")
        assertTrue(login.someDetailsEmpty())
    }

    @Test
    fun `LoginDetails someDetailsEmpty should return true for empty password`() {
        val login = LoginDetails("username", "")
        assertTrue(login.someDetailsEmpty())
    }

    @Test
    fun `LoginDetails someDetailsEmpty should return true for both empty`() {
        val login = LoginDetails("", "")
        assertTrue(login.someDetailsEmpty())
    }

    @Test
    fun `LoginDetails someDetailsEmpty should return false for non-empty details`() {
        val login = LoginDetails("username", "password")
        assertFalse(login.someDetailsEmpty())
    }

    @Test
    fun `Developer random should return a Developer with correct static and random values`() {
        val developer = Developer.random()
        assertEquals("Mario", developer.name)
        assertEquals("Rossi", developer.surname)
        assertEquals("", developer.email)
        assertTrue(developer.avatarId in 1..10)
    }
}
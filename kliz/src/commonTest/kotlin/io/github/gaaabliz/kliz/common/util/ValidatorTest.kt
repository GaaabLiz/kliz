package io.github.gaaabliz.kliz.common.util

import io.github.gaaabliz.kliz.common.model.ValidationResult
import kotlin.test.*

class ValidatorTest {

    @Test
    fun `email validation`() {
        assertTrue(Validator.email("test@example.com").successful)
        assertTrue(Validator.email("test.name@example.co.uk").successful)
        assertFalse(Validator.email(null).successful)
        assertFalse(Validator.email("").successful)
        assertFalse(Validator.email(" ").successful)
        assertFalse(Validator.email("test@").successful)
        assertFalse(Validator.email("test@domain").successful)
        assertFalse(Validator.email("test@.com").successful)
        assertFalse(Validator.email("test.com").successful)
    }

    @Test
    fun `checkNullObj validation`() {
        assertTrue(Validator.checkNullObj("not null").successful)
        assertFalse(Validator.checkNullObj(null).successful)
        assertEquals("Campo non inserito", Validator.checkNullObj(null).errorMessage)
    }

    @Test
    fun `nullable validation`() {
        assertTrue(Validator.nullable("not null").successful)
        assertFalse(Validator.nullable(null).successful)
        assertEquals("Errore campo", Validator.nullable(null).errorMessage)
    }

    @Test
    fun `message validation`() {
        assertTrue(Validator.message("A valid message").successful)
        assertFalse(Validator.message(null).successful)
        assertFalse(Validator.message("").successful)
        assertFalse(Validator.message(" ").successful)
        assertFalse(Validator.message("Too long", maxLength = 5).successful)
        assertEquals("Messaggio troppo lungo", Validator.message("Too long", maxLength = 5).errorMessage)
    }

    @Test
    fun `password validation`() {
        // Basic checks
        assertFalse(Validator.password(null).successful)
        assertFalse(Validator.password("").successful)
        assertFalse(Validator.password("short").successful, "Should fail on minLength")

        // With letter and digit requirement
        assertTrue(Validator.password("valid123").successful)
        assertFalse(Validator.password("onlyletters").successful, "Should fail without digits")
        assertFalse(Validator.password("1234567").successful, "Should fail without letters")

        // Without letter and digit requirement
        assertTrue(Validator.password("onlyletters", mustContainLetterAndDigits = false).successful)
        assertTrue(Validator.password("1234567", mustContainLetterAndDigits = false).successful)
    }

    @Test
    fun `repeatedPassword validation`() {
        assertTrue(Validator.repeatedPassword("pass123", "pass123").successful)
        assertFalse(Validator.repeatedPassword("pass123", "pass456").successful)
        assertFalse(Validator.repeatedPassword("pass123", null).successful)
        assertFalse(Validator.repeatedPassword(null, "pass123").successful)
        assertFalse(Validator.repeatedPassword("", "").successful)
    }

    @Test
    fun `code validation`() {
        assertTrue(Validator.code("123456", length = 6).successful)
        assertFalse(Validator.code(null).successful)
        assertFalse(Validator.code("").successful)
        assertFalse(Validator.code("123", length = 6).successful)
        assertEquals("Codice non valido", Validator.code("123", length = 6).errorMessage)
    }

    @Test
    fun `nameSurname validation`() {
        assertTrue(Validator.nameSurname("Valid Name").successful)
        assertFalse(Validator.nameSurname(null).successful)
        assertFalse(Validator.nameSurname("").successful)
        assertFalse(Validator.nameSurname("A", minLength = 2).successful)
        assertFalse(Validator.nameSurname("A very very long name that exceeds the max length", maxLength = 20).successful)
    }

    @Test
    fun `latitude validation`() {
        assertTrue(Validator.isValidNonEmptyLatitude("0"))
        assertTrue(Validator.isValidNonEmptyLatitude("90"))
        assertTrue(Validator.isValidNonEmptyLatitude("-90"))
        assertTrue(Validator.isValidNonEmptyLatitude("45.123"))
        assertFalse(Validator.isValidNonEmptyLatitude(""))
        assertFalse(Validator.isValidNonEmptyLatitude("90.1"))
        assertFalse(Validator.isValidNonEmptyLatitude("-91"))
        assertFalse(Validator.isValidNonEmptyLatitude("abc"))
    }

    @Test
    fun `longitude validation`() {
        assertTrue(Validator.isValidNonEmptyLongitude("0"))
        assertTrue(Validator.isValidNonEmptyLongitude("180"))
        assertTrue(Validator.isValidNonEmptyLongitude("-180"))
        assertTrue(Validator.isValidNonEmptyLongitude("123.456"))
        assertFalse(Validator.isValidNonEmptyLongitude(""))
        assertFalse(Validator.isValidNonEmptyLongitude("180.1"))
        assertFalse(Validator.isValidNonEmptyLongitude("-181"))
        assertFalse(Validator.isValidNonEmptyLongitude("abc"))
    }

    @Test
    fun `phone validation`() {
        assertTrue(Validator.isValidPhone("1234567890").successful)
        assertTrue(Validator.isValidPhone("+391234567890").successful)
        assertFalse(Validator.isValidPhone(null).successful)
        assertFalse(Validator.isValidPhone("").successful)
        assertFalse(Validator.isValidPhone("123-456").successful)
        assertFalse(Validator.isValidPhone("123 456").successful)
        assertFalse(Validator.isValidPhone("abc").successful)
    }
}

package io.github.gaaabliz.kliz.common.util

import kotlin.test.*

class RegexUtilsTest {

    // --- Email Validation ---
    @Test
    fun `isValidEmail should return true for valid emails`() {
        assertTrue(RegexUtils.isValidEmail("test@example.com"))
        assertTrue(RegexUtils.isValidEmail("user.name@sub.domain.co.uk"))
        assertTrue(RegexUtils.isValidEmail("test+alias@example.com"))
    }

    @Test
    fun `isValidEmail should return false for invalid emails`() {
        assertFalse(RegexUtils.isValidEmail("invalid-email"))
        assertFalse(RegexUtils.isValidEmail("test@.com"))
        assertFalse(RegexUtils.isValidEmail("test@domain"))
        assertFalse(RegexUtils.isValidEmail("test@domain."))
        assertFalse(RegexUtils.isValidEmail("@example.com"))
        assertFalse(RegexUtils.isValidEmail("test@example..com"))
    }

    // --- Password Validation ---
    @Test
    fun `isValidPassword should return true for valid passwords`() {
        assertTrue(RegexUtils.isValidPassword("Passw0rd!"))
        assertTrue(RegexUtils.isValidPassword("MyStrongP@ss1"))
    }

    @Test
    fun `isValidPassword should return false for invalid passwords`() {
        assertFalse(RegexUtils.isValidPassword("short")) // Too short
        assertFalse(RegexUtils.isValidPassword("password!")) // No uppercase
        assertFalse(RegexUtils.isValidPassword("PASSWORD!")) // No lowercase
        assertFalse(RegexUtils.isValidPassword("Passwrd!")) // No digit
        assertFalse(RegexUtils.isValidPassword("Passw0rd")) // No special char
        assertFalse(RegexUtils.isValidPassword("Passw0rd! ")) // Contains whitespace
    }

    // --- Phone Number Validation ---
    @Test
    fun `isValidPhoneNumber should return true for valid 10-digit phone numbers`() {
        assertTrue(RegexUtils.isValidPhoneNumber("1234567890"))
        assertTrue(RegexUtils.isValidPhoneNumber("0987654321"))
    }

    @Test
    fun `isValidPhoneNumber should return false for invalid phone numbers`() {
        assertFalse(RegexUtils.isValidPhoneNumber("123456789")) // Too short
        assertFalse(RegexUtils.isValidPhoneNumber("12345678901")) // Too long
        assertFalse(RegexUtils.isValidPhoneNumber("123-456-7890")) // Contains non-digits
        assertFalse(RegexUtils.isValidPhoneNumber("abcde12345")) // Contains letters
    }

    // --- Name Validation ---
    @Test
    fun `isValidName should return true for valid names`() {
        assertTrue(RegexUtils.isValidName("John Doe"))
        assertTrue(RegexUtils.isValidName("Alice"))
        assertTrue(RegexUtils.isValidName("A B"))
    }

    @Test
    fun `isValidName should return false for invalid names`() {
        assertFalse(RegexUtils.isValidName("J")) // Too short
        assertFalse(RegexUtils.isValidName("John123")) // Contains digits
        assertFalse(RegexUtils.isValidName("John-Doe")) // Contains special chars
    }

    // --- Address Validation ---
    @Test
    fun `isValidAddress should return true for valid addresses`() {
        assertTrue(RegexUtils.isValidAddress("123 Main St"))
        assertTrue(RegexUtils.isValidAddress("Apt 4B"))
        assertTrue(RegexUtils.isValidAddress("Via Roma 10"))
    }

    @Test
    fun `isValidAddress should return false for invalid addresses`() {
        assertFalse(RegexUtils.isValidAddress("1")) // Too short
        assertFalse(RegexUtils.isValidAddress("123 Main St!")) // Contains special chars (not allowed by regex)
    }

    // --- City Validation ---
    @Test
    fun `isValidCity should return true for valid cities`() {
        assertTrue(RegexUtils.isValidCity("New York"))
        assertTrue(RegexUtils.isValidCity("Paris"))
    }

    @Test
    fun `isValidCity should return false for invalid cities`() {
        assertFalse(RegexUtils.isValidCity("N")) // Too short
        assertFalse(RegexUtils.isValidCity("London1")) // Contains digits
    }

    // --- State Validation ---
    @Test
    fun `isValidState should return true for valid states`() {
        assertTrue(RegexUtils.isValidState("California"))
        assertTrue(RegexUtils.isValidState("NY"))
    }

    @Test
    fun `isValidState should return false for invalid states`() {
        assertFalse(RegexUtils.isValidState("C")) // Too short
        assertFalse(RegexUtils.isValidState("Texas!")) // Contains special chars
    }

    // --- Zip Code Validation ---
    @Test
    fun `isValidZip should return true for valid 5-digit zip codes`() {
        assertTrue(RegexUtils.isValidZip("12345"))
        assertTrue(RegexUtils.isValidZip("00000"))
    }

    @Test
    fun `isValidZip should return false for invalid zip codes`() {
        assertFalse(RegexUtils.isValidZip("1234")) // Too short
        assertFalse(RegexUtils.isValidZip("123456")) // Too long
        assertFalse(RegexUtils.isValidZip("abcde")) // Contains letters
    }

    // --- Country Validation ---
    @Test
    fun `isValidCountry should return true for valid countries`() {
        assertTrue(RegexUtils.isValidCountry("United States"))
        assertTrue(RegexUtils.isValidCountry("Italy"))
    }

    @Test
    fun `isValidCountry should return false for invalid countries`() {
        assertFalse(RegexUtils.isValidCountry("U")) // Too short
        assertFalse(RegexUtils.isValidCountry("France1")) // Contains digits
    }

    // --- Card Number Validation ---
    @Test
    fun `isValidCardNumber should return true for valid 16-digit card numbers`() {
        assertTrue(RegexUtils.isValidCardNumber("1234567890123456"))
    }

    @Test
    fun `isValidCardNumber should return false for invalid card numbers`() {
        assertFalse(RegexUtils.isValidCardNumber("123456789012345")) // Too short
        assertFalse(RegexUtils.isValidCardNumber("12345678901234567")) // Too long
        assertFalse(RegexUtils.isValidCardNumber("abcde12345678901")) // Contains letters
    }

    // --- Card Expiry Validation ---
    @Test
    fun `isValidCardExpiry should return true for valid 4-digit expiry`() {
        assertTrue(RegexUtils.isValidCardExpiry("1234"))
    }

    @Test
    fun `isValidCardExpiry should return false for invalid expiry`() {
        assertFalse(RegexUtils.isValidCardExpiry("123")) // Too short
        assertFalse(RegexUtils.isValidCardExpiry("12345")) // Too long
        assertFalse(RegexUtils.isValidCardExpiry("abcd")) // Contains letters
    }

    // --- Card CVV Validation ---
    @Test
    fun `isValidCardCVV should return true for valid 3-digit CVV`() {
        assertTrue(RegexUtils.isValidCardCVV("123"))
    }

    @Test
    fun `isValidCardCVV should return false for invalid CVV`() {
        assertFalse(RegexUtils.isValidCardCVV("12")) // Too short
        assertFalse(RegexUtils.isValidCardCVV("1234")) // Too long
        assertFalse(RegexUtils.isValidCardCVV("abc")) // Contains letters
    }

    // --- Card Holder Name Validation ---
    @Test
    fun `isValidCardHolderName should return true for valid names`() {
        assertTrue(RegexUtils.isValidCardHolderName("John Doe"))
    }

    @Test
    fun `isValidCardHolderName should return false for invalid names`() {
        assertFalse(RegexUtils.isValidCardHolderName("J")) // Too short
        assertFalse(RegexUtils.isValidCardHolderName("John123")) // Contains digits
    }

    // --- Card Type Validation ---
    @Test
    fun `isValidCardType should return true for valid types`() {
        assertTrue(RegexUtils.isValidCardType("Visa"))
    }

    @Test
    fun `isValidCardType should return false for invalid types`() {
        assertFalse(RegexUtils.isValidCardType("V")) // Too short
        assertFalse(RegexUtils.isValidCardType("Visa1")) // Contains digits
    }

    // --- Card Expiry Month Validation ---
    @Test
    fun `isValidCardExpiryMonth should return true for valid 2-digit month`() {
        assertTrue(RegexUtils.isValidCardExpiryMonth("01"))
        assertTrue(RegexUtils.isValidCardExpiryMonth("12"))
    }

    @Test
    fun `isValidCardExpiryMonth should return false for invalid month`() {
        assertFalse(RegexUtils.isValidCardExpiryMonth("1")) // Too short
        assertFalse(RegexUtils.isValidCardExpiryMonth("123")) // Too long
        assertFalse(RegexUtils.isValidCardExpiryMonth("ab")) // Contains letters
    }

    // --- Card Expiry Year Validation ---
    @Test
    fun `isValidCardExpiryYear should return true for valid 4-digit year`() {
        assertTrue(RegexUtils.isValidCardExpiryYear("2023"))
    }

    @Test
    fun `isValidCardExpiryYear should return false for invalid year`() {
        assertFalse(RegexUtils.isValidCardExpiryYear("23")) // Too short
        assertFalse(RegexUtils.isValidCardExpiryYear("20234")) // Too long
        assertFalse(RegexUtils.isValidCardExpiryYear("abcd")) // Contains letters
    }

    // --- Date Validation (DD/MM/YYYY) ---
    @Test
    fun `isValidDate should return true for valid DD_MM_YYYY dates`() {
        assertTrue(RegexUtils.isValidDate("01/01/2023"))
        assertTrue(RegexUtils.isValidDate("31/12/1999"))
    }

    @Test
    fun `isValidDate should return false for invalid DD_MM_YYYY dates`() {
        assertFalse(RegexUtils.isValidDate("1/1/2023")) // Incorrect format
        assertFalse(RegexUtils.isValidDate("01-01-2023")) // Incorrect separator
        assertFalse(RegexUtils.isValidDate("2023/01/01")) // Incorrect order
    }

    // --- DateMatcher Validation (YYYY-MM-DD with leap year logic) ---
    @Test
    fun `DateMatcher isValid should return true for valid YYYY_MM_DD dates including leap year`() {
        val matcher = RegexUtils.DateMatcher()
        assertTrue(matcher.isValid("2024-02-29")) // Leap year
        assertTrue(matcher.isValid("2023-01-01"))
        assertTrue(matcher.isValid("1999-12-31"))
        assertTrue(matcher.isValid("2000-02-29")) // Century leap year
    }

    @Test
    fun `DateMatcher isValid should return false for invalid YYYY_MM_DD dates`() {
        val matcher = RegexUtils.DateMatcher()
        assertFalse(matcher.isValid("2023-02-29")) // Not a leap year
        assertFalse(matcher.isValid("2023-01-32")) // Invalid day
        assertFalse(matcher.isValid("2023-13-01")) // Invalid month
        assertFalse(matcher.isValid("01/01/2023")) // Wrong format
        assertFalse(matcher.isValid("2023-01-01 ")) // Trailing space
    }
}

@file:Suppress("unused")

package io.github.gaaabliz.kliz.common.util

import java.util.regex.Pattern

/**
 * Utility class for managing regular expressions used in application input fields.
 * Provides static methods for common validation patterns.
 */
sealed class RegexUtils {

    companion object {

        /**
         * Validates if a given string is a valid email address.
         * @param email The email string to validate.
         * @return `true` if the email is valid, `false` otherwise.
         */
        fun isValidEmail(email: String): Boolean {
            return email.matches(
                Regex("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")
            )
        }

        /**
         * Validates if a given string is a strong password.
         * Requires at least 8 characters, one uppercase letter, one lowercase letter, one digit, and one special character.
         * @param password The password string to validate.
         * @return `true` if the password is valid, `false` otherwise.
         */
        fun isValidPassword(password: String): Boolean {
            return password.matches(
                Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
            )
        }

        /**
         * Validates if a given string is a 10-digit phone number.
         * @param phoneNumber The phone number string to validate.
         * @return `true` if the phone number is valid, `false` otherwise.
         */
        fun isValidPhoneNumber(phoneNumber: String): Boolean {
            return phoneNumber.matches(
                Regex("^[0-9]{10}$")
            )
        }

        /**
         * Validates if a given string is a valid name (at least 2 alphabetic characters or spaces).
         * @param name The name string to validate.
         * @return `true` if the name is valid, `false` otherwise.
         */
        fun isValidName(name: String): Boolean {
            return name.matches(
                Regex("^[a-zA-Z ]{2,}$")
            )
        }

        /**
         * Validates if a given string is a valid address (at least 2 alphanumeric characters or spaces).
         * @param address The address string to validate.
         * @return `true` if the address is valid, `false` otherwise.
         */
        fun isValidAddress(address: String): Boolean {
            return address.matches(
                Regex("^[a-zA-Z0-9 ]{2,}$")
            )
        }

        /**
         * Validates if a given string is a valid city name (at least 2 alphabetic characters or spaces).
         * @param city The city string to validate.
         * @return `true` if the city name is valid, `false` otherwise.
         */
        fun isValidCity(city: String): Boolean {
            return city.matches(
                Regex("^[a-zA-Z ]{2,}$")
            )
        }

        /**
         * Validates if a given string is a valid state name (at least 2 alphabetic characters or spaces).
         * @param state The state string to validate.
         * @return `true` if the state name is valid, `false` otherwise.
         */
        fun isValidState(state: String): Boolean {
            return state.matches(
                Regex("^[a-zA-Z ]{2,}$")
            )
        }

        /**
         * Validates if a given string is a 5-digit zip code.
         * @param zip The zip code string to validate.
         * @return `true` if the zip code is valid, `false` otherwise.
         */
        fun isValidZip(zip: String): Boolean {
            return zip.matches(
                Regex("^[0-9]{5}$")
            )
        }

        /**
         * Validates if a given string is a valid country name (at least 2 alphabetic characters or spaces).
         * @param country The country string to validate.
         * @return `true` if the country name is valid, `false` otherwise.
         */
        fun isValidCountry(country: String): Boolean {
            return country.matches(
                Regex("^[a-zA-Z ]{2,}$")
            )
        }

        /**
         * Validates if a given string is a 16-digit card number.
         * @param cardNumber The card number string to validate.
         * @return `true` if the card number is valid, `false` otherwise.
         */
        fun isValidCardNumber(cardNumber: String): Boolean {
            return cardNumber.matches(
                Regex("^[0-9]{16}$")
            )
        }

        /**
         * Validates if a given string is a 4-digit card expiry.
         * @param cardExpiry The card expiry string to validate (e.g., "MMYY").
         * @return `true` if the card expiry is valid, `false` otherwise.
         */
        fun isValidCardExpiry(cardExpiry: String): Boolean {
            return cardExpiry.matches(
                Regex("^[0-9]{4}$")
            )
        }

        /**
         * Validates if a given string is a 3-digit card CVV.
         * @param cardCVV The card CVV string to validate.
         * @return `true` if the CVV is valid, `false` otherwise.
         */
        fun isValidCardCVV(cardCVV: String): Boolean {
            return cardCVV.matches(
                Regex("^[0-9]{3}$")
            )
        }

        /**
         * Validates if a given string is a valid card holder name (at least 2 alphabetic characters or spaces).
         * @param cardHolderName The card holder name string to validate.
         * @return `true` if the card holder name is valid, `false` otherwise.
         */
        fun isValidCardHolderName(cardHolderName: String): Boolean {
            return cardHolderName.matches(
                Regex("^[a-zA-Z ]{2,}$")
            )
        }

        /**
         * Validates if a given string is a valid card type (at least 2 alphabetic characters or spaces).
         * @param cardType The card type string to validate.
         * @return `true` if the card type is valid, `false` otherwise.
         */
        fun isValidCardType(cardType: String): Boolean {
            return cardType.matches(
                Regex("^[a-zA-Z ]{2,}$")
            )
        }

        /**
         * Validates if a given string is a 2-digit card expiry month.
         * @param cardExpiryMonth The card expiry month string to validate.
         * @return `true` if the month is valid, `false` otherwise.
         */
        fun isValidCardExpiryMonth(cardExpiryMonth: String): Boolean {
            return cardExpiryMonth.matches(
                Regex("^[0-9]{2}$")
            )
        }

        /**
         * Validates if a given string is a 4-digit card expiry year.
         * @param cardExpiryYear The card expiry year string to validate.
         * @return `true` if the year is valid, `false` otherwise.
         */
        fun isValidCardExpiryYear(cardExpiryYear: String): Boolean {
            return cardExpiryYear.matches(
                Regex("^[0-9]{4}$")
            )
        }

        /**
         * Validates if a given string is a date in "DD/MM/YYYY" format.
         * Note: This regex only checks the format, not the validity of the date itself (e.g., 31/02/2023 would pass).
         * @param date The date string to validate.
         * @return `true` if the date string matches the "DD/MM/YYYY" format, `false` otherwise.
         */
        fun isValidDate(date: String): Boolean {
            return date.matches(
                Regex("^[0-9]{2}/[0-9]{2}/[0-9]{4}$")
            )
        }

        private val DATE_PATTERN: Pattern = Pattern.compile(
            "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
                    + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$"
        )

        private val PASSWORD_PATTERN: Pattern = Pattern.compile("^" +
                "(?=.*[0-9])" +         // almeno 1 numero
                "(?=.*[a-z])" +         // almeno 1 lettera minuscola
                "(?=.*[A-Z])" +         // almeno 1 lettera maiuscola
                "(?=.*[a-zA-Z])" +      // ammessa ogni lettera
                "(?=.*[!@#$%^&+=])" +   // almeno 1 carattere speciale
                "(?=\\S+$)" +           // spazi bianchi non ammessi
                ".{4,20}" +             // almeno 4 caratteri totali
                "$")

        private val NOME_COGNOME_PATTERN: Pattern = Pattern.compile("^" +
                "(?=.*[a-zA-Z])" +      // ammessa ogni lettera
                "(?=\\S+$)" +           // spazi bianchi non ammessi
                ".{2,20}" +             // almeno 2 caratteri totali
                "$")

        private val NUM_TELEFONO_PATTERN: Pattern = Pattern.compile("^" +
                "(?=.*[0-9])" +         // almeno 1 numero
                "(?=\\S+$)" +           // spazi bianchi non ammessi
                ".{9,10}" +             // almeno 10 numeri totali
                "$")

        private val PAESE_CITTA: Pattern = Pattern.compile("^" +
                "(?=.*[a-zA-Z])" +      // ammessa ogni lettera
                "(?:[\\S-][a-zA-Z]+)" + /* spazi bianchi non ammessi e caratteri di separazione
                                           senza una lettera successiva non ammessi */
                "*$")

        private val INDIRIZZO: Pattern = Pattern.compile("^" +
                "[#.0-9a-zA-Z\\s,-]" +  /* ammesse lettere, numeri e simboli di separazione
                                           o # per indicare il numero civico */
                "$")

        private val CODICE: Pattern = Pattern.compile("^" +
                "(?=.*[0-9])" +         // almeno 1 numero
                "(?=.*[A-Z])" +         // almeno 1 lettera maiuscola
                "(?=\\S+$)" +           // spazi bianchi non ammessi
                ".{5,5}" +              // 5 caratteri minimi e totali
                "$")

        private var EMAIL_PATTERN_MULTIPLE = Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@" +
                    "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
                    "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." +
                    "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
                    "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|" +
                    "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        )

    }

    /**
     * Interface for a generic matcher that validates a string.
     */
    interface Matcher {
        /**
         * Validates the given string.
         * @param value The string to validate.
         * @return `true` if the string is valid, `false` otherwise.
         */
        fun isValid(value: String): Boolean
    }

    /**
     * A [Matcher] implementation for validating date strings against a specific "YYYY-MM-DD" pattern,
     * including basic leap year logic.
     */
    class DateMatcher() : Matcher {
        override fun isValid(value: String): Boolean {
            return DATE_PATTERN.matcher(value).matches()
        }
    }

}

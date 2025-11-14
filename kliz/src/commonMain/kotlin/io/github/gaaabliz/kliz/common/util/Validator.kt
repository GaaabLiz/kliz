package io.github.gaaabliz.kliz.common.util

import io.github.gaaabliz.kliz.common.model.ValidationResult

/**
 * A singleton object that provides various validation functions.
 */
object Validator {

    private val emailAddressRegex = Regex(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    /**
     * Processes a list of [ValidationResult]s and executes a callback based on the outcome.
     * If all results are successful, [onAllSuccess] is called.
     * Otherwise, [onFirstFailure] is called for the first failure found.
     * @param list The list of validation results to handle.
     * @param onAllSuccess A suspendable lambda to execute if all validations pass.
     * @param onFirstFailure A lambda to execute with the error message of the first validation that fails.
     */
    suspend fun handle(list : List<ValidationResult>, onAllSuccess: suspend () -> Unit, onFirstFailure: (error: String) -> Unit) {
        list.forEach {
            if(!it.successful) {
                onFirstFailure(it.errorMessage.toString())
                return
            }
        }
        onAllSuccess()
    }

    /**
     * Validates an email address.
     * @param email The email string to validate.
     * @return A [ValidationResult] indicating success or failure with an error message.
     */
    fun email(email : String? = null) : ValidationResult {
        if(email == null) return ValidationResult(false, "Email non inserita")
        if(email.isBlank()) return ValidationResult(false, "Email non inserita")
        if(!email.matches(emailAddressRegex)) return ValidationResult(false, "Email inserita non valida")
        return ValidationResult(true)
    }

    /**
     * Checks if a generic object is null.
     * @param obj The object to check.
     * @return A [ValidationResult] indicating if the object is non-null (success) or null (failure).
     */
    fun <T> checkNullObj(obj : T? = null) : ValidationResult {
        if(obj == null) return ValidationResult(false, "Campo non inserito")
        return ValidationResult(true)
    }

    /**
     * Validates a message string based on length constraints.
     * @param message The message string to validate.
     * @param maxLength The maximum allowed length for the message.
     * @return A [ValidationResult] indicating success or failure.
     */
    fun message(
        message : String? = null,
        maxLength : Int = 1000,
    ) : ValidationResult {
        if(message == null) return ValidationResult(false, "Messaggio non inserito")
        if(message.isBlank()) return ValidationResult(false, "Messaggio non inserito")
        if(message.length > maxLength) return ValidationResult(false, "Messaggio troppo lungo")
        return ValidationResult(true)
    }

    /**
     * Validates a password based on length and content rules.
     * @param password The password string to validate.
     * @param minLength The minimum required length for the password.
     * @param mustContainLetterAndDigits If true, the password must contain at least one letter and one digit.
     * @return A [ValidationResult] indicating success or failure.
     */
    fun password(
        password : String? = null,
        minLength : Int = 6,
        mustContainLetterAndDigits : Boolean = true
    ) : ValidationResult {
        if(password == null) return ValidationResult(false, "Password non inserita")
        if(password.isBlank()) return ValidationResult(false, "Password non inserita")
        if(password.length < minLength) return ValidationResult(false, "Password troppo corta")
        if(mustContainLetterAndDigits) {
            val cond = password.any { it.isDigit() } && password.any { it.isLetter() }
            if(!cond) return ValidationResult(false, "Password deve contenere almeno una lettera e un numero")
        }
        return ValidationResult(true)
    }

    /**
     * Validates if two password strings match.
     * @param password The first password.
     * @param repeatedPassword The second password for confirmation.
     * @return A [ValidationResult] indicating if the passwords match.
     */
    fun repeatedPassword(password: String? = null, repeatedPassword: String? = null) : ValidationResult {
        if(password == null || repeatedPassword == null) return ValidationResult(false, "Una delle due password non inserita")
        if(password.isBlank() || repeatedPassword.isBlank()) return ValidationResult(false, "Una delle due password non inserita")
        if(password != repeatedPassword) return ValidationResult(false, "Le password non coincidono")
        return ValidationResult(true)
    }

    /**
     * Validates a code based on its exact length.
     * @param codeToValidate The code string to validate.
     * @param length The exact required length of the code.
     * @return A [ValidationResult] indicating success or failure.
     */
    fun code(codeToValidate : String? = null, length: Int = 6) : ValidationResult {
        if(codeToValidate == null) return ValidationResult(false, "Codice non inserito")
        if(codeToValidate.isBlank()) return ValidationResult(false, "Codice non inserito")
        if(codeToValidate.length != length) return ValidationResult(false, "Codice non valido")
        return ValidationResult(true)
    }

    /**
     * Checks if an object is null.
     * @param obj The object to check.
     * @return A [ValidationResult] indicating if the object is non-null (success) or null (failure).
     */
    fun nullable(obj : Any?) : ValidationResult {
        if(obj == null) return ValidationResult(false, "Errore campo")
        return ValidationResult(true)
    }

    /**
     * Validates a name or surname based on length constraints.
     * @param name The name string to validate.
     * @param minLength The minimum allowed length.
     * @param maxLength The maximum allowed length.
     * @return A [ValidationResult] indicating success or failure.
     */
    fun nameSurname (name : String? = null, minLength: Int = 2, maxLength: Int = 100) : ValidationResult {
        if(name == null) return ValidationResult(false, "Nome non inserito")
        if(name.isBlank()) return ValidationResult(false, "Nome non inserito")
        if(name.length < minLength) return ValidationResult(false, "Nome troppo corto")
        if(name.length > maxLength) return ValidationResult(false, "Nome troppo lungo")
        return ValidationResult(true)
    }

    /**
     * Validates if a string is a valid, non-empty latitude value.
     * @param latitude The latitude string to validate.
     * @return `true` if the string is a valid latitude, `false` otherwise.
     */
    fun isValidNonEmptyLatitude(latitude : String) : Boolean {
        if(latitude == "") return false
        val pattern = Regex("^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)$")
        return pattern.matches(latitude)
    }

    /**
     * Validates if a string is a valid, non-empty longitude value.
     * @param longitude The longitude string to validate.
     * @return `true` if the string is a valid longitude, `false` otherwise.
     */
    fun isValidNonEmptyLongitude(longitude : String) : Boolean {
        if(longitude == "") return false
        val pattern = Regex("^[-+]?((1?[0-7]?\\d(\\.\\d+)?)|180(\\.0+)?)$")
        return pattern.matches(longitude)
    }

    /**
     * Validates a phone number string.
     * It checks if the string is not null or blank and contains only digits or the '+' symbol.
     * @param newPhone The phone number string to validate.
     * @return A [ValidationResult] indicating success or failure.
     */
    fun isValidPhone(newPhone: String?): ValidationResult {
        if (newPhone == null) return ValidationResult(false, "Telefono non inserito")
        if (newPhone.isBlank()) return ValidationResult(false, "Telefono non inserito")
        val regex = Regex("[0-9+]+")
        val result = regex.matches(newPhone)
        return if (result) ValidationResult(true) else ValidationResult(
            false,
            "Telefono non valido"
        )
    }
}
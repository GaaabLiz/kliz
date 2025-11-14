package io.github.gaaabliz.kliz.common.model

/**
 * Represents the result of a validation operation.
 *
 * @property successful `true` if the validation passed, `false` otherwise.
 * @property errorMessage An optional message describing the reason for failure if validation was not successful.
 */
data class ValidationResult(
    val successful : Boolean,
    val errorMessage : String? = null
)

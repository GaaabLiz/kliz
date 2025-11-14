package io.github.gaaabliz.kliz.common.util

import java.lang.NumberFormatException

/**
 * A utility object for various measurement conversions and validations.
 */
object MeasureUtils {

    /** Unit for irrigation, "l/s" (liters per second). */
    private const val IRRIGATION_UNIT = "l/s"
    /** Unit for temperature, "°C" (degrees Celsius). */
    private const val TEMPERATURE_UNIT = "°C"
    /** Unit for brightness, "lux". */
    private const val BRIGHTNESS_UNIT = "lux"
    /** Unit for humidity, "g/m^3" (grams per cubic meter). */
    private const val HUMIDITY_UNIT = "g/m^3"

    /** Minimum valid temperature value. */
    private const val VALUE_TEMPERATURE_MIN = 0
    /** Maximum valid temperature value. */
    private const val VALUE_TEMPERATURE_MAX = 50
    /** Maximum valid light value. */
    private const val VALUE_LIGHT_MAX = 1000
    /** Minimum valid light value. */
    private const val VALUE_LIGHT_MIN = 0
    /** Minimum valid humidity value. */
    private const val VALUE_HUMIDITY_MIN = 0
    /** Maximum valid humidity value. */
    private const val VALUE_HUMIDITY_MAX = 50
    /** Maximum valid irrigation value. */
    private const val VALUE_IRRIGATION_MAX = 100
    /** Minimum valid irrigation value. */
    private const val VALUE_IRRIGATION_MIN = 0

    /**
     * Converts milliseconds to seconds, returning the remainder when divided by 60.
     * This is useful for getting the "seconds" part of a time duration.
     *
     * @param msec The time in milliseconds.
     * @return The seconds part (0-59) as a [Long].
     */
    fun convertMsecToSec(msec: Long): Long = (msec / 1000) % 60

    /**
     * Converts milliseconds to total seconds.
     *
     * @param millisec The time in milliseconds.
     * @return The total seconds as a [Long].
     */
    fun convertMillisecToSec(millisec: Long): Long = millisec / 1000

    /**
     * Converts a value from meters to kilometers.
     *
     * @param metri The value in meters, as a [Number].
     * @return The converted value in kilometers, as a [Number] (Double).
     */
    fun convertMetersToKm(metri: Number): Number = (metri.toDouble()) / 1000

    /**
     * Converts a value from kilometers to meters.
     *
     * @param km The value in kilometers, as a [Number].
     * @return The converted value in meters, as a [Number] (Double).
     */
    fun convertKmToMeters(km: Number): Number = (km.toDouble()) * 1000

    /**
     * Validates if a string represents an integer temperature value within the defined range
     * ([VALUE_TEMPERATURE_MIN] to [VALUE_TEMPERATURE_MAX]).
     *
     * @param value The string to validate.
     * @return `true` if the string is a valid temperature value, `false` otherwise.
     */
    fun isValidTemperatureValue(value: String): Boolean {
        return try {
            val valueInt = value.toInt()
            valueInt in VALUE_TEMPERATURE_MIN..VALUE_TEMPERATURE_MAX
        } catch(e: NumberFormatException) {
            false
        }
    }

    /**
     * Validates if a string represents an integer light value within the defined range
     * ([VALUE_LIGHT_MIN] to [VALUE_LIGHT_MAX]).
     *
     * @param value The string to validate.
     * @return `true` if the string is a valid light value, `false` otherwise.
     */
    fun isValidLightValue(value: String): Boolean {
        return try {
            val valueInt = value.toInt()
            valueInt in VALUE_LIGHT_MIN..VALUE_LIGHT_MAX
        } catch(e: NumberFormatException) {
            false
        }
    }

    /**
     * Validates if a string represents an integer humidity value within the defined range
     * ([VALUE_HUMIDITY_MIN] to [VALUE_HUMIDITY_MAX]).
     *
     * @param value The string to validate.
     * @return `true` if the string is a valid humidity value, `false` otherwise.
     */
    fun isValidHumidityValue(value: String): Boolean {
        return try {
            val valueInt = value.toInt()
            valueInt in VALUE_HUMIDITY_MIN..VALUE_HUMIDITY_MAX
        } catch(e: NumberFormatException) {
            false
        }
    }

    /**
     * Validates if a string represents an integer irrigation value within the defined range
     * ([VALUE_IRRIGATION_MIN] to [VALUE_IRRIGATION_MAX]).
     *
     * @param value The string to validate.
     * @return `true` if the string is a valid irrigation value, `false` otherwise.
     */
    fun isValidIrrigationValue(value: String): Boolean {
        return try {
            val valueInt = value.toInt()
            valueInt in VALUE_IRRIGATION_MIN..VALUE_IRRIGATION_MAX
        } catch(e: NumberFormatException) {
            false
        }
    }

}
package io.github.gaaabliz.kliz.common.model

import java.time.DayOfWeek

/**
 * Represents a specific day of the work week with associated code, string representation, and [DayOfWeek].
 *
 * @property code A short code for the day (e.g., "L" for Lunedì).
 * @property string The full string representation of the day (e.g., "Lunedì").
 * @property dayOfTheWeek The corresponding [DayOfWeek] enum from `java.time`.
 */
enum class DayOfWork(
    val code: String,
    val string: String,
    val dayOfTheWeek: DayOfWeek
) {
    LUNEDI("L", "Lunedì", DayOfWeek.MONDAY),
    MARTEDI("MA", "Martedì", DayOfWeek.TUESDAY),
    MERCOLEDI("ME", "Mercoledì", DayOfWeek.WEDNESDAY),
    GIOVEDI("G", "Giovedì", DayOfWeek.THURSDAY),
    VENERDI("V", "Venerdì", DayOfWeek.FRIDAY),
    WEEKEND("END", "Week-end", DayOfWeek.SUNDAY)
}

/**
 * Represents a moment within a day.
 */
enum class DayMoment {
    /** Morning period. */
    MORNING,
    /** Afternoon period. */
    AFTERNOON,
    /** Pause or break period. */
    PAUSE,
    /** Unknown or unspecified moment. */
    UNKNOWN
}
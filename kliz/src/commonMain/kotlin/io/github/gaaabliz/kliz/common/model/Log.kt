package io.github.gaaabliz.kliz.common.model

import io.github.gaaabliz.kliz.common.util.GenUtils

/**
 * Represents a log entry. This class is deprecated.
 *
 * @property id A unique identifier for the log entry, defaults to a random 20-character ID.
 * @property date The date of the log entry.
 * @property time The time of the log entry.
 * @property type The [LogType] of the entry (e.g., DEBUG, INFO, ERROR).
 * @property message The detailed message of the log entry.
 */
@Deprecated(message = "This class is deprecated and may be removed in future versions.", level = DeprecationLevel.WARNING)
data class Log(
    val id: String = GenUtils.generateRandomId(20),
    val date: String,
    val time: String,
    val type: LogType,
    val message: String
)

/**
 * Defines the severity or type of a log entry.
 */
enum class LogType{
    /** Debugging information. */
    DEBUG,
    /** General information. */
    INFO,
    /** Error message. */
    ERROR,
    /** Tracing information. */
    TRACE,
    /** Warning message. */
    WARNING
}
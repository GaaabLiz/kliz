package io.github.gaaabliz.kliz.common.base

/**
 * Custom exception class used to signal specific operational errors within the application.
 * It extends [Exception] and allows for additional context like a state and a title.
 *
 * @param msg A descriptive message for the exception, also passed to the superclass.
 * @param state An optional boolean state associated with the operation that failed.
 * @param title An optional title for the error, providing more context.
 */
class OperationException(
    val msg:String,
    val state:Boolean? = null,
    val title:String? = null,
) : Exception(msg)

/**
 * Custom exception for database-related errors.
 * It can wrap an original exception or just carry a specific message.
 *
 * @param exc The original [Exception] that was caught, if any.
 * @param msg An optional specific message for this exception. If provided, it's passed to the superclass.
 */
class DbException(private val exc:Exception? = null, private val msg: String? = null) : Exception(msg) {
    /**
     * Retrieves the most relevant error message.
     * It prioritizes the message from the wrapped exception (`exc`),
     * then falls back to the specific message (`msg`) of this exception,
     * and finally returns a default message if no other information is available.
     *
     * @return A [String] containing the error message.
     */
    fun getExcMessage() : String {
        if(exc != null) return exc.message.toString()
        return if(msg != null ) this.message.toString()
        else "DbException has not info attached to it."
    }
}


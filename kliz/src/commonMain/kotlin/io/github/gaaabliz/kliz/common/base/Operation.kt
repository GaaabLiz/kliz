package io.github.gaaabliz.kliz.common.base

/**
 * A sealed class representing the result of an operation that can either succeed or fail.
 * This is a generic class that can hold any type of data on success.
 *
 * @param T The type of data returned on a successful operation.
 */
sealed class Operation <T> (
    data : T? = null,
    errorMessage:String? = null,
)  {

    companion object {

        /**
         * Checks if the given operation was successful.
         * @param operation The operation to check.
         * @return `true` if the operation is [Success], `false` otherwise.
         */
        fun <T> isOk(operation : Operation<T>) : Boolean {
            return when(operation) {
                is Success -> true
                is Error -> false
            }
        }

        /**
         * Executes a given lambda based on the result of the operation.
         * @param operation The operation to execute.
         * @param onSuccess A lambda to be executed with the data if the operation is a [Success].
         * @param onError A lambda to be executed with the error message if the operation is an [Error].
         */
        fun <T> exec(
            operation : Operation<T>,
            onSuccess : (T) -> Unit,
            onError : (String?) -> Unit,
        ) {
            return when(operation) {
                is Success -> onSuccess(operation.data)
                is Error -> onError(operation.errorMessage)
            }
        }

        /**
         * Executes a given lambda and returns a new [Operation] result, allowing for chaining operations.
         * @param OI The input operation data type.
         * @param OO The output operation data type.
         * @param operation The operation to execute.
         * @param onSuccess A lambda that takes the success data and returns a new [Operation].
         * @param onError A lambda that takes the error message and returns a new [Operation].
         * @return A new [Operation] of type [OO].
         */
        fun <OI, OO> execWithReturn(
            operation : Operation<OI>,
            onSuccess : (OI) -> Operation<OO>,
            onError : (String?) -> Operation<OO>,
        ) : Operation<OO> {
            return when(operation) {
                is Success -> { return onSuccess(operation.data) }
                is Error -> onError(operation.errorMessage)
            }
        }

        /**
         * Safely retrieves data from a successful operation.
         * If the operation is an error, it executes the [onError] lambda and returns null.
         * @param operation The operation to extract data from.
         * @param onError A lambda to be executed if the operation is an [Error].
         * @param onSuccess An optional lambda to be executed with the data if the operation is a [Success].
         * @return The data of type [T] if successful, otherwise `null`.
         */
        fun <T> getSuccessDataFrom(
            operation: Operation<T>,
            onError : (String?) -> Unit,
            onSuccess: (T?) -> Unit = {},
        ) : T? {
            return when(operation) {
                is Success -> {
                    onSuccess(operation.data)
                    operation.data
                }
                is Error -> {
                    onError(operation.errorMessage)
                    null
                }
            }
        }

        /**
         * Safely retrieves data from a successful operation.
         * If the operation is an error, it executes the [onError] lambda and returns null.
         * @param operation The operation to extract data from.
         * @param onError A lambda to be executed if the operation is an [Error].
         * @return The data of type [T] if successful, otherwise `null`.
         */
        fun <T> getSuccessDataFrom(
            operation: Operation<T>,
            onError : (String?) -> Unit,
        ) : T? {
            return when(operation) {
                is Success -> operation.data
                is Error -> {
                    onError(operation.errorMessage)
                    null
                }
            }
        }

        /**
         * Executes a given suspendable lambda based on the result of the operation.
         * @param operation The operation to execute.
         * @param onSuspendSuccess A suspendable lambda to be executed with the data if the operation is a [Success].
         * @param onError A lambda to be executed with the error message if the operation is an [Error].
         */
        suspend fun <T> exec(
            operation : Operation<T>,
            onSuspendSuccess : suspend (T) -> Unit,
            onError : (String?) -> Unit,
        ) {
            return when(operation) {
                is Success -> onSuspendSuccess(operation.data)
                is Error -> onError(operation.errorMessage)
            }
        }
    }

    /**
     * Represents a successful operation, containing the result data.
     * @param data The data returned by the successful operation.
     */
    data class Success <T>(
        val data: T,
    ) : Operation<T>(data, null)

    /**
     * Represents a failed operation, containing an error message.
     * @param errorMessage A string describing the error.
     */
    data class Error <T>(
        val errorMessage: String?,
    ) : Operation<T>(null, errorMessage) {

    }
}

/**
 * Wraps a lambda function in a try-catch block and returns an [Operation] result.
 * If the lambda executes successfully, it returns the [Operation.Success] produced by the action.
 * If it throws an exception, it catches it and returns an [Operation.Error].
 *
 * @param action The lambda function to execute, which should return an [Operation].
 * @return The [Operation] result from the action, or an [Operation.Error] if an exception occurred.
 */
inline fun <T> safeCall(action: () -> Operation<T>): Operation<T> {
    return try {
        action()
    } catch (e: Exception) {
        Operation.Error(e.message ?: "An unknown Error Occurred")
    }
}


/**
 * A sealed class representing a more descriptive operation result, including titles and custom messages.
 *
 * @param T The type of data returned on a successful operation.
 */
sealed class CustomOperation <T> (
    title : String? = null,
    data : T? = null,
    successMessage : String? = null,
    errorMessage:String? = null,
)  {

    /**
     * Represents a successful custom operation.
     * @param title A title for the operation result.
     * @param successMessage An optional message describing the success.
     * @param data Optional data returned by the operation.
     */
    data class Success <T>(
        val title : String,
        val successMessage : String? = null,
        val data : T? = null,
    ) : CustomOperation<T>(successMessage = successMessage, data = data, title = title)

    /**
     * Represents a failed custom operation.
     * @param title A title for the operation result.
     * @param errorMessage A message describing the error.
     */
    data class Error <T>(
        val title : String,
        val errorMessage : String,
    ) : CustomOperation<T>(title = title, errorMessage = errorMessage)

}

package it.gabliz.kliz.common.model

import org.slf4j.Logger

sealed class Operation <T> (
    data : T? = null,
    errorMessage:String? = null,
)  {

    data class Success <T>(
        val data: T,
    ) : Operation<T>(data, null)

    data class Error <T>(
        val errorMessage: String?,
    ) : Operation<T>(null, errorMessage) {

        constructor(errorMessage: String?, logger : Logger) : this(errorMessage) {
            if(errorMessage != null) {
                logger.error(errorMessage)
            } else {
                logger.warn("Operation class failed to print log because the error message was null.")
            }
        }
    }
}


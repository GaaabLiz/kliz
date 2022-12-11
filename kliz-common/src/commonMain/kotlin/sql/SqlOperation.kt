@file:Suppress("unused")
package sql


import org.slf4j.Logger


class SqlOperation<T> private constructor(
    private val sqlQuery : String? = null,
) {

    companion object {
        private const val SQL_BASE_PATH = "/sql/"
        const val PARAMER_CHAR = "?"

        fun readSqlText(fileName : String) : String {
            return this::class.java.getResource(SQL_BASE_PATH + fileName)!!.readText()
        }

    }

    var returnObject : T? = null


    @Deprecated(
            message = "",
            replaceWith = ReplaceWith(""),
            level = DeprecationLevel.HIDDEN
        )
    class Builder {
        private var sqlQueryFileName : String? = null
        private var queryText : String? = null

        fun setSqlQueryFileName(sqlQueryFileName: String) = apply { this.sqlQueryFileName = sqlQueryFileName }
        fun readQuery() = apply {
            try {
                queryText = readSqlText(sqlQueryFileName!!)
            }catch (e : Exception) {
                throw Exception(e)
            }
        }
        fun replaceParam(stringParam : String) = apply { queryText = queryText!!.replaceFirst(PARAMER_CHAR, stringParam) }
        fun build() = SqlOperation<Any>(queryText)
    }

}
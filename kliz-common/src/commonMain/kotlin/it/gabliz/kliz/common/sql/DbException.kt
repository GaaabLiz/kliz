package it.gabliz.kliz.common.sql

class DbException(private val exc:Exception? = null, private val msg: String? = null) : Exception(msg) {
    fun getExcMessage() : String {
        if(exc != null) return exc.message.toString()
        return if(msg != null ) this.message.toString()
        else "DbException has not info attached to it."
    }
}
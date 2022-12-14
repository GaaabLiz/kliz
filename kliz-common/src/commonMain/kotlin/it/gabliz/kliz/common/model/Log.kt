package it.gabliz.kliz.common.model

import it.gabliz.kliz.common.util.DataUtils

@Deprecated(message = "", level = DeprecationLevel.WARNING)
data class Log(
    val id: String = DataUtils.generateRandomId(20),
    val date: String,
    val time: String,
    val type: LogType,
    val message: String
)

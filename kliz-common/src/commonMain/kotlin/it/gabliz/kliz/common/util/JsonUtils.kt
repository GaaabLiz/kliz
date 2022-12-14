package it.gabliz.kliz.common.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONObject
import java.lang.reflect.Type

object JsonUtils {

    fun <T> convertJsonToObj(stringJson : String, outputType : Type) : T {
        val json = JSONObject(stringJson)
        return Gson().fromJson(json.toString(), outputType)
    }

    fun <T> convertObjToJson(objectToConvert : T) : String  {
        val gson: Gson = GsonBuilder().setPrettyPrinting().create()
        return gson.toJson(objectToConvert)
    }
}
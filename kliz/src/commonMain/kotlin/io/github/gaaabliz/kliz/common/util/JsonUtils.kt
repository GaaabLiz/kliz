package io.github.gaaabliz.kliz.common.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import org.json.JSONObject
import java.lang.reflect.Type

/**
 * A utility object for converting between JSON strings and Kotlin objects.
 */
object JsonUtils {

    /**
     * Converts a JSON string to an object of the specified type.
     * It first parses the string using `org.json.JSONObject` and then uses `Gson` for conversion.
     *
     * @param stringJson The JSON string to convert.
     * @param outputType The [Type] of the object to convert to (e.g., `MyClass::class.java`).
     * @return An object of type [T] parsed from the JSON string.
     * @throws org.json.JSONException if the input string is not a valid JSON.
     * @throws JsonSyntaxException if Gson encounters a syntax error during deserialization.
     */
    fun <T> convertJsonToObj(stringJson : String, outputType : Type) : T {
        val json = JSONObject(stringJson)
        return Gson().fromJson(json.toString(), outputType)
    }

    /**
     * Converts a Kotlin object to a pretty-printed JSON string.
     *
     * @param objectToConvert The object to convert to JSON.
     * @return A pretty-printed JSON string representation of the object.
     */
    fun <T> convertObjToJson(objectToConvert : T) : String  {
        val gson: Gson = GsonBuilder().setPrettyPrinting().serializeNulls().create()
        return gson.toJson(objectToConvert)
    }
}
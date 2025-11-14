@file:Suppress("RedundantNullableReturnType")

package io.github.gaaabliz.kliz.common.util

import org.apache.commons.lang3.StringUtils
import java.util.Locale

/**
 * A utility object providing various functions for data manipulation and conversion.
 */
object DataUtils {

    /**
     * Maps two lists into a [Map], using elements from the first list as keys and elements from the second list as values.
     *
     * @param list1 The first list, whose elements will be used as keys.
     * @param list2 The second list, whose elements will be used as values.
     * @return A [Map] where keys are from [list1] and values are from [list2].
     * @throws IllegalArgumentException if the two lists have different sizes.
     */
    fun <T, S> mapTwoList(list1 : List<T>, list2 : List<S>) : Map<T, S> {
        if(list1.size != list2.size) {
            throw IllegalArgumentException("The two lists must have the same size.")
        }
        val map = mutableMapOf<T, S>()
        for (i in list1.indices) {
            map[list1[i]] = list2[i]
        }
        return map
    }

    /**
     * Converts a multi-word enum string (e.g., `HOME_SECONDARY`) to a display name (e.g., `Home Secondary`).
     * This function is deprecated; use [adjustEnumString] instead.
     *
     * @param enumName The enum string to convert.
     * @return The display name.
     *
     * Example: `HOME_SECONDARY` -> `Home Secondary`
     */
    @Deprecated("Use adjustEnumString instead", ReplaceWith("adjustEnumString(enumName)"))
    fun convertEnumNameToDisplayName(enumName : String) : String {
        return adjustEnumString(enumName)
    }

    /**
     * Checks if all the provided boolean conditions are true.
     * @param b A variable number of boolean arguments.
     * @return `true` if all booleans are true, `false` otherwise.
     */
    private fun checkAllTrue(vararg b: Boolean):Boolean {
        var status:Boolean = false;
        for (t in b) {
            if(!t) return false
        }
        return true
    }

    /**
     * Checks if an object is null and throws an [IllegalArgumentException] if it is.
     *
     * @param obj The object to check for nullability.
     * @param name The name of the object, used in the exception message if null.
     * @return The non-null object if it passes the check.
     * @throws IllegalArgumentException if the object is null.
     */
    fun <T> checkNullThrow(obj: T?, name:String): T? {
        if(obj == null) {
            throw IllegalArgumentException("Object $name cannot be null")
        }
        return obj
    }

    /**
     * Limits the size of a list to the specified limit.
     * If the list's size is greater than the limit, a sublist containing elements from index 0 up to (but not including) the limit is returned.
     * Otherwise, the original list is returned.
     *
     * @param list The input list.
     * @param limit The maximum desired size of the list.
     * @return A new list with a size up to the specified limit, or the original list if it's smaller than the limit.
     */
    fun <T> limitList(list: List<T>, limit: Int): List<T> {
        return if(list.size > limit) {
            list.subList(0, limit)
        } else {
            list
        }
    }

    /**
     * Converts a multi-word enum string (e.g., `HOME_SECONDARY`) to a display name (e.g., `Home Secondary`).
     *
     * @param oldString The enum string to convert.
     * @return The display name.
     *
     * Example: `HOME_SECONDARY` -> `Home Secondary`
     */
    fun adjustEnumString(oldString: String): String {
        /* Sovrascrivo tutti gli underscore con degli spazi */
        val stringWithSpaces = oldString.replace('_', ' ')

        /* Prendo tutte le parole nella stringa e le metto in un array */
        val arrayStrings = stringWithSpaces.split(' ').toTypedArray()

        /* Metto tutte le stringhe in minuscola */
        val arrayLower = ArrayList<String>()
        arrayStrings.forEach { arrayLower.add(it.lowercase(Locale.getDefault())) }

        /* Metto la prima lettera di ogni stringa in maiuscolo */
        val arrayLowerCap = ArrayList<String>()
        arrayLower.forEach { it ->
            arrayLowerCap.add(it.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }) }

        /* Unisco tutte le stringhe in una */
        val stringBuilder = StringBuilder()
        arrayLowerCap.forEach { stringBuilder.append(it.plus(' ')) }

        /* Rimuovo ultimo char */
        val newString = stringBuilder.toString()

        /* Ritorno la stringa */
        return StringUtils.removeEnd(newString, " ")
    }

    /**
     * Creates a new list by adding an element to an existing list.
     * If the source list is null, a new list containing only the element to add is returned.
     *
     * @param listSource The source list, which can be null.
     * @param elementToAdd The element to add to the list.
     * @return A new list containing elements from the source list (if not null) and the new element.
     */
    fun <T> addElementToImmutableList(listSource: List<T>?, elementToAdd: T): List<T?> {
        return if(listSource != null) {
            /* Aggiungo nella vecchia lista quelli sorgente */
            val oldList: MutableList<T> = ArrayList()
            listSource.forEach { oldList.add(it) }
            /* Creo nuovo lista con quelli vecchi + quello nuovo */
            val newList: MutableList<T> = ArrayList()
            newList.addAll(oldList)
            newList.add(elementToAdd)
            newList
        } else {
            listOf(elementToAdd)
        }
    }

    /**
     * Converts a string to lowercase and capitalizes its first letter.
     *
     * @param string The input string.
     * @return The string with its first letter capitalized and the rest in lowercase.
     */
    fun toLowerAndCap(string: String) : String {
        return string.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
}
package util

object DataUtils {

    fun generateRandomId(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return(1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

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

    fun convertEnumNameToDisplayName(enumName : String) : String {
        return enumName.replace("_", " ").toLowerCase().capitalize()
    }

}
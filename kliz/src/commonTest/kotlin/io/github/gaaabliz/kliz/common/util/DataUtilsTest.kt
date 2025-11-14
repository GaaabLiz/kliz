package io.github.gaaabliz.kliz.common.util

import kotlin.test.*

class DataUtilsTest {

    @Test
    fun `mapTwoList should correctly map two lists of equal size`() {
        val list1 = listOf("one", "two", "three")
        val list2 = listOf(1, 2, 3)
        val expectedMap = mapOf("one" to 1, "two" to 2, "three" to 3)
        assertEquals(expectedMap, DataUtils.mapTwoList(list1, list2))
    }

    @Test
    fun `mapTwoList should throw IllegalArgumentException if lists have different sizes`() {
        val list1 = listOf("one", "two")
        val list2 = listOf(1, 2, 3)
        assertFailsWith<IllegalArgumentException> {
            DataUtils.mapTwoList(list1, list2)
        }
    }

    @Test
    fun `mapTwoList should work with empty lists`() {
        val list1 = emptyList<String>()
        val list2 = emptyList<Int>()
        assertTrue(DataUtils.mapTwoList(list1, list2).isEmpty())
    }

    @Test
    fun `convertEnumNameToDisplayName should convert enum string to display name`() {
        assertEquals("Home Secondary", DataUtils.convertEnumNameToDisplayName("HOME_SECONDARY"))
        assertEquals("Single Word", DataUtils.convertEnumNameToDisplayName("SINGLE_WORD"))
        assertEquals("Another Example With Multiple Words", DataUtils.convertEnumNameToDisplayName("ANOTHER_EXAMPLE_WITH_MULTIPLE_WORDS"))
        assertEquals("", DataUtils.convertEnumNameToDisplayName(""))
    }

    @Test
    fun `adjustEnumString should convert enum string to display name`() {
        assertEquals("Home Secondary", DataUtils.adjustEnumString("HOME_SECONDARY"))
        assertEquals("Single Word", DataUtils.adjustEnumString("SINGLE_WORD"))
        assertEquals("Another Example With Multiple Words", DataUtils.adjustEnumString("ANOTHER_EXAMPLE_WITH_MULTIPLE_WORDS"))
        assertEquals("", DataUtils.adjustEnumString(""))
    }

    @Test
    fun `checkNullThrow should return object if not null`() {
        val obj = "Hello"
        assertEquals(obj, DataUtils.checkNullThrow(obj, "testObject"))
    }

    @Test
    fun `checkNullThrow should throw IllegalArgumentException if object is null`() {
        assertFailsWith<IllegalArgumentException> {
            DataUtils.checkNullThrow(null, "testObject")
        }
        val exception = assertFailsWith<IllegalArgumentException> {
            DataUtils.checkNullThrow(null, "anotherObject")
        }
        assertEquals("Object anotherObject cannot be null", exception.message)
    }

    @Test
    fun `limitList should return original list if size is less than limit`() {
        val list = listOf(1, 2, 3)
        assertEquals(listOf(1, 2, 3), DataUtils.limitList(list, 5))
    }

    @Test
    fun `limitList should return sublist if size is greater than limit`() {
        val list = listOf(1, 2, 3, 4, 5)
        assertEquals(listOf(1, 2, 3), DataUtils.limitList(list, 3))
    }

    @Test
    fun `limitList should return empty list if limit is 0`() {
        val list = listOf(1, 2, 3)
        assertTrue(DataUtils.limitList(list, 0).isEmpty())
    }

    @Test
    fun `limitList should return empty list for empty input list`() {
        val list = emptyList<Int>()
        assertTrue(DataUtils.limitList(list, 5).isEmpty())
    }

    @Test
    fun `addElementToImmutableList should add element to null list`() {
        val result = DataUtils.addElementToImmutableList(null, "newElement")
        assertEquals(listOf("newElement"), result)
    }

    @Test
    fun `addElementToImmutableList should add element to empty list`() {
        val result = DataUtils.addElementToImmutableList(emptyList(), "newElement")
        assertEquals(listOf("newElement"), result)
    }

    @Test
    fun `addElementToImmutableList should add element to non-empty list`() {
        val sourceList = listOf("old1", "old2")
        val result = DataUtils.addElementToImmutableList(sourceList, "newElement")
        assertEquals(listOf("old1", "old2", "newElement"), result)
    }

    @Test
    fun `toLowerAndCap should convert string to lowercase and capitalize first letter`() {
        assertEquals("Hello", DataUtils.toLowerAndCap("hello"))
        assertEquals("Hello", DataUtils.toLowerAndCap("HELLO"))
        assertEquals("Hello world", DataUtils.toLowerAndCap("hello world"))
        assertEquals("", DataUtils.toLowerAndCap(""))
        assertEquals("123test", DataUtils.toLowerAndCap("123test")) // Numbers should not be capitalized
    }
}

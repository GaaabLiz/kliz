package io.github.gaaabliz.kliz.common.util

import com.google.gson.JsonSyntaxException
import org.json.JSONException
import kotlin.test.*

// Helper data class for testing
data class TestData(val name: String, val value: Int)

class JsonUtilsTest {

    @Test
    fun `convertJsonToObj should convert valid JSON string to object`() {
        val jsonString = """{"name":"TestName","value":123}"""
        val expectedObject = TestData("TestName", 123)
        val convertedObject = JsonUtils.convertJsonToObj<TestData>(jsonString, TestData::class.java)
        assertEquals(expectedObject, convertedObject)
    }

    @Test
    fun `convertJsonToObj should throw JSONException for invalid JSON format`() {
        val invalidJsonString = """{"name":"TestName","value":123""" // Missing closing brace
        assertFailsWith<JSONException> {
            JsonUtils.convertJsonToObj(invalidJsonString, TestData::class.java)
        }
    }

    @Test
    fun `convertJsonToObj should throw JsonSyntaxException for JSON with wrong types`() {
        val wrongTypeJsonString = """{"name":"TestName","value":"not_an_int"}"""
        assertFailsWith<JsonSyntaxException> {
            JsonUtils.convertJsonToObj(wrongTypeJsonString, TestData::class.java)
        }
    }

    @Test
    fun `convertObjToJson should convert object to pretty-printed JSON string`() {
        val testObject = TestData("AnotherTest", 456)
        val jsonString = JsonUtils.convertObjToJson(testObject)

        // Check for pretty printing (newlines and indentation)
        assertTrue(jsonString.contains("\n"))
        assertTrue(jsonString.contains("  \"name\": \"AnotherTest\","))
        assertTrue(jsonString.contains("  \"value\": 456"))

        // Check content
        val expectedJsonPart1 = "\"name\": \"AnotherTest\""
        val expectedJsonPart2 = "\"value\": 456"
        assertTrue(jsonString.contains(expectedJsonPart1))
        assertTrue(jsonString.contains(expectedJsonPart2))
    }

    @Test
    fun `convertObjToJson should handle null values`() {
        data class NullableTestData(val name: String?, val value: Int?)
        val testObject = NullableTestData(null, null)
        val jsonString = JsonUtils.convertObjToJson(testObject)
        assertTrue(jsonString.contains("\"name\": null"))
        assertTrue(jsonString.contains("\"value\": null"))
    }
}

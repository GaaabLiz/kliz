package io.github.gaaabliz.kliz.base

import io.github.gaaabliz.kliz.common.base.DbException
import io.github.gaaabliz.kliz.common.base.OperationException
import kotlin.test.*

class ExceptionsTest {

    @Test
    fun `OperationException should be instantiated with message only`() {
        val exception = OperationException("Test message")
        assertEquals("Test message", exception.msg)
        assertEquals("Test message", exception.message)
        assertNull(exception.state)
        assertNull(exception.title)
    }

    @Test
    fun `OperationException should be instantiated with message and state`() {
        val exception = OperationException("Test message with state", true)
        assertEquals("Test message with state", exception.msg)
        assertEquals("Test message with state", exception.message)
        assertTrue(exception.state!!)
        assertNull(exception.title)
    }

    @Test
    fun `OperationException should be instantiated with message and title`() {
        val exception = OperationException("Test message with title", title = "Error Title")
        assertEquals("Test message with title", exception.msg)
        assertEquals("Test message with title", exception.message)
        assertNull(exception.state)
        assertEquals("Error Title", exception.title)
    }

    @Test
    fun `OperationException should be instantiated with all parameters`() {
        val exception = OperationException("Full message", false, "Full Title")
        assertEquals("Full message", exception.msg)
        assertEquals("Full message", exception.message)
        assertFalse(exception.state!!)
        assertEquals("Full Title", exception.title)
    }

    @Test
    fun `OperationException should be catchable`() {
        val testMessage = "Catch me if you can!"
        var caught = false
        try {
            throw OperationException(testMessage)
        } catch (e: OperationException) {
            caught = true
            assertEquals(testMessage, e.message)
        }
        assertTrue(caught, "OperationException was not caught.")
    }

    @Test
    fun `DbException getExcMessage should return wrapped exception message`() {
        val originalException = IllegalStateException("Original error")
        val dbException = DbException(exc = originalException)
        assertEquals("Original error", dbException.getExcMessage())
    }

    @Test
    fun `DbException getExcMessage should prioritize wrapped exception message`() {
        val originalException = IllegalStateException("Original error")
        val dbException = DbException(exc = originalException, msg = "This should be ignored")
        assertEquals("Original error", dbException.getExcMessage())
    }

    @Test
    fun `DbException getExcMessage should return its own message if no wrapped exception`() {
        val dbException = DbException(msg = "Specific DB error")
        assertEquals("Specific DB error", dbException.getExcMessage())
    }

    @Test
    fun `DbException getExcMessage should return default message if no info is provided`() {
        val dbException = DbException()
        assertEquals("DbException has not info attached to it.", dbException.getExcMessage())
    }
}

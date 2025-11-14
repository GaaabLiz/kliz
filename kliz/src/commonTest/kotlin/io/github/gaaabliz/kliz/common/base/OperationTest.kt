package io.github.gaaabliz.kliz.common.base

import kotlin.test.*

class OperationTest {

    // Test data
    private val successData = "SuccessData"
    private val errorMessage = "ErrorOccurred"
    private val successOperation = Operation.Success(successData)
    private val errorOperation = Operation.Error<String>(errorMessage)

    @Test
    fun `isOk should return true for Success and false for Error`() {
        assertTrue(Operation.isOk(successOperation))
        assertFalse(Operation.isOk(errorOperation))
    }

    @Test
    fun `exec should call onSuccess for Success operation`() {
        var onSuccessCalled = false
        var onErrorCalled = false

        Operation.exec(
            operation = successOperation,
            onSuccess = {
                assertEquals(successData, it)
                onSuccessCalled = true
            },
            onError = { onErrorCalled = true }
        )

        assertTrue(onSuccessCalled)
        assertFalse(onErrorCalled)
    }

    @Test
    fun `exec should call onError for Error operation`() {
        var onSuccessCalled = false
        var onErrorCalled = false

        Operation.exec(
            operation = errorOperation,
            onSuccess = { onSuccessCalled = true },
            onError = {
                assertEquals(errorMessage, it)
                onErrorCalled = true
            }
        )

        assertTrue(onErrorCalled)
        assertFalse(onSuccessCalled)
    }

    @Test
    fun `execWithReturn should correctly transform operations`() {
        val newSuccessOp = Operation.Success(123)
        val newErrorOp = Operation.Error<Int>("New Error")

        // Test success path
        val result1 = Operation.execWithReturn(
            operation = successOperation,
            onSuccess = {
                assertEquals(successData, it)
                newSuccessOp
            },
            onError = { newErrorOp }
        )
        assertTrue(result1 is Operation.Success)
        assertEquals(123, result1.data)

        // Test error path
        val result2 = Operation.execWithReturn(
            operation = errorOperation,
            onSuccess = { newSuccessOp },
            onError = {
                assertEquals(errorMessage, it)
                newErrorOp
            }
        )
        assertTrue(result2 is Operation.Error)
        assertEquals("New Error", result2.errorMessage)
    }

    @Test
    fun `getSuccessDataFrom should return data on Success`() {
        var onErrorCalled = false
        var onSuccessCalled = false
        val data = Operation.getSuccessDataFrom(
            operation = successOperation,
            onError = { onErrorCalled = true },
            onSuccess = {
                assertEquals(successData, it)
                onSuccessCalled = true
            }
        )
        assertTrue(onSuccessCalled)
        assertFalse(onErrorCalled)
        assertEquals(successData, data)
    }

    @Test
    fun `getSuccessDataFrom should return null on Error`() {
        var onErrorCalled = false
        var onSuccessCalled = false
        val data = Operation.getSuccessDataFrom(
            operation = errorOperation,
            onError = {
                assertEquals(errorMessage, it)
                onErrorCalled = true
            },
            onSuccess = { onSuccessCalled = true }
        )
        assertTrue(onErrorCalled)
        assertFalse(onSuccessCalled)
        assertNull(data)
    }

    @Test
    fun `safeCall should return Success when no exception is thrown`() {
        val result = safeCall {
            Operation.Success("No exception")
        }
        assertTrue(result is Operation.Success)
        assertEquals("No exception", result.data)
    }

    @Test
    fun `safeCall should return Error when an exception is thrown`() {
        val exceptionMessage = "Something went wrong"
        val result = safeCall {
            throw IllegalStateException(exceptionMessage)
            @Suppress("UNREACHABLE_CODE")
            Operation.Success("This won't be reached")
        }
        assertTrue(result is Operation.Error)
        assertEquals(exceptionMessage, result.errorMessage)
    }

    @Test
    fun `CustomOperation Success should hold correct data`() {
        val op = CustomOperation.Success(title = "Test", successMessage = "It worked", data = 42)
        assertEquals("Test", op.title)
        assertEquals("It worked", op.successMessage)
        assertEquals(42, op.data)
    }

    @Test
    fun `CustomOperation Error should hold correct data`() {
        val op = CustomOperation.Error<Unit>(title = "Failure", errorMessage = "It failed")
        assertEquals("Failure", op.title)
        assertEquals("It failed", op.errorMessage)
    }
}

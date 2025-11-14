package io.github.kotlin.fibonacci

import io.github.gaaabliz.kliz.examples.generateFibi
import org.junit.Test
import kotlin.test.assertEquals

class JvmFibiTest {

    @Test
    fun `test 3rd element`() {
        assertEquals(2, generateFibi().take(3).last())
    }
}
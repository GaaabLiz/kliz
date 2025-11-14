package io.github.gaaabliz.kliz.commonTest

import io.github.gaaabliz.kliz.examples.firstElement
import io.github.gaaabliz.kliz.examples.generateFibi
import io.github.gaaabliz.kliz.examples.secondElement
import kotlin.test.Test
import kotlin.test.assertEquals

class FibiTest {

    @Test
    fun `test 3rd element`() {
        assertEquals(firstElement + secondElement, generateFibi().take(3).last())
    }
}
package fibi

import io.github.gaaabliz.kliz.examples.generateFibi
import org.junit.Test
import kotlin.test.assertEquals

class AndroidFibiTest {

    @Test
    fun `test 3rd element`() {
        assertEquals(1, generateFibi().take(3).last())
    }
}
package eu.luminis.workshop.cleancode

import kotlin.test.Test
import kotlin.test.assertEquals

class HelloTest {
    @Test
    fun `Say hello`() {
        assertEquals("Hello Henk!", Hello("Henk").say())
    }
}
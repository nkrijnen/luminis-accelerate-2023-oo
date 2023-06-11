package eu.luminis.workshop.cleancode

import kotlin.test.*

class VolumeTest {
    @Test
    fun `should be equal between different volume units`() {
        assertEquals(1.gill(), 5.oz())
        assertEquals(1.pint(), 4.gill())
    }
}
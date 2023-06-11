package eu.luminis.workshop.cleancode

import kotlin.test.*

class VolumeTest {
    @Test
    fun `should be equal between different volume units`() {
        assertEquals(1.gill, 5.oz)
        assertEquals(1.pint, 4.gill)
    }

    @Test
    fun `should respect hashCode & equals contract`() {
        val a = 1.gill
        val b = 1.gill
        assertEquals(a, b)
        assertEquals(b, a)
        assertEquals(a.hashCode(), b.hashCode())
    }

    @Test
    fun `should add volumes`() {
        assertEquals(2.gill, 5.oz + 1.gill)
    }
}
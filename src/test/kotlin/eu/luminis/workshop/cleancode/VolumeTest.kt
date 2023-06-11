package eu.luminis.workshop.cleancode

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class VolumeTest {
    @Test
    fun `should be equal between different volume units`() {
        assertEquals(1.gill, 5.oz)
        assertEquals(1.pint, 4.gill)
        assertEquals(0.gill, 0.oz)

        assertNotEquals(1.gill, 1.oz)
    }

    @Test
    fun `should respect hashCode & equals contract`() {
        val a = 1.gill
        val b = 1.gill
        assertEquals(a, b)
        assertEquals(b, a)
        assertEquals(a.hashCode(), b.hashCode())

        val c = 2.gill
        assertNotEquals(a, c)
        assertNotEquals(c, a)
        assertNotEquals(a.hashCode(), c.hashCode())
    }

    @Test
    fun `should add volumes`() {
        assertEquals(2.gill, 5.oz + 1.gill)
    }
}
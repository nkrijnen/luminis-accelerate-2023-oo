package eu.luminis;

import org.junit.jupiter.api.Test;

import static eu.luminis.Volume.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class VolumeTest {

    @Test
    public void shouldBeEqualBetweenDifferentVolumeUnits() {
        assertEquals(gill(1), oz(5));
        assertEquals(pint(1), gill(4));
        assertEquals(quart(1), pint(2));
        assertEquals(gallon(1), quart(4));

        assertEquals(gill(0), oz(0));
        assertEquals(pint(0.25), gill(1));

        assertNotEquals(gill(1), oz(1));
    }

    @Test
    public void shouldRespectHashCodeEqualsContract() {
        assertEquals(gill(0).hashCode(), oz(0).hashCode());

        var a = gill(1);
        var b = gill(1);
        assertEquals(a, b);
        assertEquals(b, a);
        assertEquals(a.hashCode(), b.hashCode());

        var c = gill(2);
        assertNotEquals(a, c);
        assertNotEquals(c, a);
        assertNotEquals(a.hashCode(), c.hashCode());
    }

}

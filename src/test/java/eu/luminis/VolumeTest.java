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

}

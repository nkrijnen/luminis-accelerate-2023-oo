package eu.luminis;

import org.junit.jupiter.api.Test;

import static eu.luminis.Measurement.*;
import static org.junit.jupiter.api.Assertions.*;

public class MeasurementTest {
    @Test
    public void shouldBeEqualBetweenDifferentUnitsOfSameMeasurementType() {
        assertEquals(gill(1), oz(5));
        assertEquals(gill(0), oz(0));
        assertEquals(pint(0.25), gill(1));
        assertNotEquals(gill(1), oz(1));
    }

    @Test
    public void shouldNeverEqualBetweenDifferentMeasurementTypes() {
        assertThrows(IllegalArgumentException.class, () ->
                assertNotEquals(inch(1), oz(1))
        );
    }

    @Test
    public void shouldSupportVolumeUnits() {
        assertEquals(gill(1), oz(5));
        assertEquals(pint(1), gill(4));
        assertEquals(quart(1), pint(2));
        assertEquals(gallon(1), quart(4));
    }

    @Test
    public void shouldSupportDistanceUnits() {
        assertEquals(foot(1), inch(12));
        assertEquals(yard(1), foot(3));
        assertEquals(chain(1), yard(22));
        assertEquals(furlong(1), chain(10));
        assertEquals(mile(1), furlong(8));
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

        assertNotEquals(inch(1).hashCode(), oz(1).hashCode());
    }

    @Test
    public void shouldAddVolumes() {
        assertEquals(gill(2), oz(5).add(gill(1)));
        assertEquals(gill(2), pint(0.25).add(gill(1)));
    }

    @Test
    public void shouldSubtractVolumes() {
        assertEquals(gill(2), gill(3).subtract(oz(5)));
        assertEquals(pint(0), gill(1).subtract(gill(1)));
    }

    @Test
    public void shouldMultiplyVolumes() {
        assertEquals(gill(2), gill(1).multiplyBy(2));
    }

    @Test
    public void shouldMDivideVolumes() {
        assertEquals(gill(0.5), gill(1).divideBy(2));
    }
}

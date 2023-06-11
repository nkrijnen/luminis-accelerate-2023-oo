package eu.luminis;

import org.junit.jupiter.api.Test;

import static eu.luminis.Distance.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DistanceTest {
    @Test
    public void shouldBeEqualBetweenDifferentVolumeUnits() {
        assertEquals(foot(1), inch(12));
        assertEquals(yard(1), foot(3));
        assertEquals(chain(1), yard(22));
        assertEquals(furlong(1), chain(10));
        assertEquals(mile(1), furlong(8));

        assertEquals(foot(0), inch(0));
        assertEquals(furlong(0.1), chain(1));

        assertNotEquals(foot(1), inch(1));
    }

    @Test
    public void shouldRespectHashCodeEqualsContract() {
        assertEquals(foot(0).hashCode(), inch(0).hashCode());

        var a = foot(1);
        var b = foot(1);
        assertEquals(a, b);
        assertEquals(b, a);
        assertEquals(a.hashCode(), b.hashCode());

        var c = foot(2);
        assertNotEquals(a, c);
        assertNotEquals(c, a);
        assertNotEquals(a.hashCode(), c.hashCode());
    }

    @Test
    public void shouldAddVolumes() {
        assertEquals(foot(2), inch(12).add(foot(1)));
        assertEquals(foot(2), yard(1.0 / 3).add(foot(1)));
    }

    @Test
    public void shouldSubtractVolumes() {
        assertEquals(foot(2), foot(3).subtract(inch(12)));
        assertEquals(yard(0), foot(1).subtract(foot(1)));
    }

    @Test
    public void shouldMultiplyVolumes() {
        assertEquals(foot(2), foot(1).multiplyBy(2));
    }

    @Test
    public void shouldMDivideVolumes() {
        assertEquals(foot(0.5), foot(1).divideBy(2));
    }
}

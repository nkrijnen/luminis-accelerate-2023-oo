package eu.luminis;

import java.util.Objects;

public class Measurement {

    public static Measurement oz(Number amount) {
        return new Measurement(amount, MeasurementUnit.oz);
    }

    public static Measurement gill(Number amount) {
        return new Measurement(amount, MeasurementUnit.gill);
    }

    public static Measurement pint(Number amount) {
        return new Measurement(amount, MeasurementUnit.pint);
    }

    public static Measurement quart(Number amount) {
        return new Measurement(amount, MeasurementUnit.quart);
    }

    public static Measurement gallon(Number amount) {
        return new Measurement(amount, MeasurementUnit.gallon);
    }


    public static Measurement inch(Number amount) {
        return new Measurement(amount, MeasurementUnit.inch);
    }

    public static Measurement foot(Number amount) {
        return new Measurement(amount, MeasurementUnit.foot);
    }

    public static Measurement yard(Number amount) {
        return new Measurement(amount, MeasurementUnit.yard);
    }

    public static Measurement chain(Number amount) {
        return new Measurement(amount, MeasurementUnit.chain);
    }

    public static Measurement furlong(Number amount) {
        return new Measurement(amount, MeasurementUnit.furlong);
    }

    public static Measurement mile(Number amount) {
        return new Measurement(amount, MeasurementUnit.mile);
    }


    private static final double EPSILON = 0.0000000001;

    private final Number amount;
    private final MeasurementUnit unit;

    private Measurement(Number amount, MeasurementUnit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    public Measurement add(Measurement other) {
        return new Measurement(amount.doubleValue() + convertToThisUnit(other).doubleValue(), unit);
    }

    public Measurement subtract(Measurement other) {
        return new Measurement(amount.doubleValue() - convertToThisUnit(other).doubleValue(), unit);
    }

    public Measurement multiplyBy(Number factor) {
        return new Measurement(amount.doubleValue() * factor.doubleValue(), unit);
    }

    public Measurement divideBy(Number factor) {
        return new Measurement(amount.doubleValue() / factor.doubleValue(), unit);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Measurement)) return false;
        return sameValue(amount, convertToThisUnit((Measurement) other));
    }

    @Override
    public int hashCode() {
        return unit.hashCodeFor(amount);
    }

    private Number convertToThisUnit(Measurement other) {
        return other.unit.convertTo(this.unit, other.amount);
    }

    private boolean sameValue(Number value, Number other) {
        return Math.abs(value.doubleValue() - other.doubleValue()) < EPSILON;
    }

    static class MeasurementUnit {
        static MeasurementUnit oz = new MeasurementUnit();
        static MeasurementUnit gill = new MeasurementUnit(5, oz);
        static MeasurementUnit pint = new MeasurementUnit(4, gill);
        static MeasurementUnit quart = new MeasurementUnit(2, pint);
        static MeasurementUnit gallon = new MeasurementUnit(4, quart);

        static MeasurementUnit inch = new MeasurementUnit();
        static MeasurementUnit foot = new MeasurementUnit(12, inch);
        static MeasurementUnit yard = new MeasurementUnit(3, foot);
        static MeasurementUnit chain = new MeasurementUnit(22, yard);
        static MeasurementUnit furlong = new MeasurementUnit(10, chain);
        static MeasurementUnit mile = new MeasurementUnit(8, furlong);

        private final int baseAmount;

        private final MeasurementUnit baseUnit;

        MeasurementUnit() {
            this.baseAmount = 1;
            this.baseUnit = this;
        }

        MeasurementUnit(int amount, MeasurementUnit unit) {
            baseAmount = amount * unit.baseAmount;
            baseUnit = unit.baseUnit;
        }

        Number convertTo(MeasurementUnit other, Number amount) {
            requireSameBaseUnit(other);
            return amount.doubleValue() * baseAmount / other.baseAmount;
        }

        int hashCodeFor(Number amount) {
            return Objects.hash(convertTo(baseUnit, amount), baseUnit);
        }

        private void requireSameBaseUnit(MeasurementUnit other) {
            if (!(this.baseUnit == other.baseUnit))
                throw new IllegalArgumentException("Must be same measurement unit type");
        }
    }
}

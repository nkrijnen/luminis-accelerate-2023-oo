package eu.luminis;

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
        return amountInBaseUnit().hashCode();
    }

    private Number amountInBaseUnit() {
        return unit.convertToBaseUnit(amount);
    }

    private Number convertToThisUnit(Measurement other) {
        return other.unit.convertTo(this.unit, other.amount);
    }

    private boolean sameValue(Number value, Number other) {
        return Math.abs(value.doubleValue() - other.doubleValue()) < EPSILON;
    }

    static class MeasurementUnit {
        static MeasurementUnit oz = new MeasurementUnit(1);
        static MeasurementUnit gill = new MeasurementUnit(5, oz);
        static MeasurementUnit pint = new MeasurementUnit(4, gill);
        static MeasurementUnit quart = new MeasurementUnit(2, pint);
        static MeasurementUnit gallon = new MeasurementUnit(4, quart);

        static MeasurementUnit inch = new MeasurementUnit(1);
        static MeasurementUnit foot = new MeasurementUnit(12, inch);
        static MeasurementUnit yard = new MeasurementUnit(3, foot);
        static MeasurementUnit chain = new MeasurementUnit(22, yard);
        static MeasurementUnit furlong = new MeasurementUnit(10, chain);
        static MeasurementUnit mile = new MeasurementUnit(8, furlong);

        private final int baseAmount;

        MeasurementUnit(int baseAmount) {
            this.baseAmount = baseAmount;
        }

        MeasurementUnit(int amount, MeasurementUnit unit) {
            this(amount * unit.baseAmount);
        }

        Number convertTo(MeasurementUnit other, Number amount) {
            return amount.doubleValue() * baseAmount / other.baseAmount;
        }

        Number convertToBaseUnit(Number amount) {
            return amount.doubleValue() * baseAmount / oz.baseAmount;
        }
    }
}

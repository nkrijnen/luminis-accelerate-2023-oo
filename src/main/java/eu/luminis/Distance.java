package eu.luminis;

public class Distance {

    private static final double EPSILON = 0.0000000001;

    public static Distance inch(Number amount) {
        return new Distance(amount, DistanceUnit.inch);
    }

    public static Distance foot(Number amount) {
        return new Distance(amount, DistanceUnit.foot);
    }

    public static Distance yard(Number amount) {
        return new Distance(amount, DistanceUnit.yard);
    }

    public static Distance chain(Number amount) {
        return new Distance(amount, DistanceUnit.chain);
    }

    public static Distance furlong(Number amount) {
        return new Distance(amount, DistanceUnit.furlong);
    }

    public static Distance mile(Number amount) {
        return new Distance(amount, DistanceUnit.mile);
    }

    private final Number amount;
    private final DistanceUnit unit;

    private Distance(Number amount, DistanceUnit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    public Distance add(Distance other) {
        return new Distance(amount.doubleValue() + convertToThisUnit(other).doubleValue(), unit);
    }

    public Distance subtract(Distance other) {
        return new Distance(amount.doubleValue() - convertToThisUnit(other).doubleValue(), unit);
    }

    public Distance multiplyBy(Number factor) {
        return new Distance(amount.doubleValue() * factor.doubleValue(), unit);
    }

    public Distance divideBy(Number factor) {
        return new Distance(amount.doubleValue() / factor.doubleValue(), unit);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Distance)) return false;
        return sameValue(amount, convertToThisUnit((Distance) other));
    }

    @Override
    public int hashCode() {
        return amountInBaseUnit().hashCode();
    }

    private Number amountInBaseUnit() {
        return unit.convertToBaseUnit(amount);
    }

    private Number convertToThisUnit(Distance other) {
        return other.unit.convertTo(this.unit, other.amount);
    }

    private boolean sameValue(Number value, Number other) {
        return Math.abs(value.doubleValue() - other.doubleValue()) < EPSILON;
    }

    static class DistanceUnit {
        static DistanceUnit inch = new DistanceUnit(1);
        static DistanceUnit foot = new DistanceUnit(12, inch);
        static DistanceUnit yard = new DistanceUnit(3, foot);
        static DistanceUnit chain = new DistanceUnit(22, yard);
        static DistanceUnit furlong = new DistanceUnit(10, chain);
        static DistanceUnit mile = new DistanceUnit(8, furlong);

        private final int baseAmount;

        DistanceUnit(int baseAmount) {
            this.baseAmount = baseAmount;
        }

        DistanceUnit(int amount, DistanceUnit unit) {
            this(amount * unit.baseAmount);
        }

        Number convertTo(DistanceUnit other, Number amount) {
            return amount.doubleValue() * baseAmount / other.baseAmount;
        }

        Number convertToBaseUnit(Number amount) {
            return amount.doubleValue() * baseAmount / inch.baseAmount;
        }
    }
}

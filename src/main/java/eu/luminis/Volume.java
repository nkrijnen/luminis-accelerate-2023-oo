package eu.luminis;

public class Volume {

    private static final double EPSILON = 0.0000000001;

    public static Volume oz(Number amount) {
        return new Volume(amount, VolumeUnit.oz);
    }

    public static Volume gill(Number amount) {
        return new Volume(amount, VolumeUnit.gill);
    }

    public static Volume pint(Number amount) {
        return new Volume(amount, VolumeUnit.pint);
    }

    public static Volume quart(Number amount) {
        return new Volume(amount, VolumeUnit.quart);
    }

    public static Volume gallon(Number amount) {
        return new Volume(amount, VolumeUnit.gallon);
    }

    private final Number amount;
    private final VolumeUnit unit;

    private Volume(Number amount, VolumeUnit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    public Volume add(Volume other) {
        return new Volume(amount.doubleValue() + convertToThisUnit(other).doubleValue(), unit);
    }

    public Volume subtract(Volume other) {
        return new Volume(amount.doubleValue() - convertToThisUnit(other).doubleValue(), unit);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Volume)) return false;
        return sameValue(amount, convertToThisUnit((Volume) other));
    }

    @Override
    public int hashCode() {
        return amountInBaseUnit().hashCode();
    }

    @Override
    public String toString() {
        return amount + " " + unit.convertTo(VolumeUnit.oz, amount);
    }

    private Number amountInBaseUnit() {
        return unit.convertToBaseUnit(amount);
    }

    private Number convertToThisUnit(Volume other) {
        return other.unit.convertTo(this.unit, other.amount);
    }

    private boolean sameValue(Number value, Number other) {
        return Math.abs(value.doubleValue() - other.doubleValue()) < EPSILON;
    }
}

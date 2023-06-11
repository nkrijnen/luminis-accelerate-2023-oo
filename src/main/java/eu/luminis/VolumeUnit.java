package eu.luminis;

class VolumeUnit {
    static VolumeUnit oz = new VolumeUnit(1);
    static VolumeUnit gill = new VolumeUnit(5, oz);
    static VolumeUnit pint = new VolumeUnit(4, gill);
    static VolumeUnit quart = new VolumeUnit(2, pint);
    static VolumeUnit gallon = new VolumeUnit(4, quart);

    private final int baseAmount;

    VolumeUnit(int baseAmount) {
        this.baseAmount = baseAmount;
    }

    VolumeUnit(int amount, VolumeUnit unit) {
        this(amount * unit.baseAmount);
    }

    Number convertTo(VolumeUnit other, Number amount) {
        return amount.doubleValue() * baseAmount / other.baseAmount;
    }

    Number convertToBaseUnit(Number amount) {
        return amount.doubleValue() * baseAmount / oz.baseAmount;
    }
}

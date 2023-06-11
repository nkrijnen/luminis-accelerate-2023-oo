package eu.luminis;

class VolumeUnit {
    static VolumeUnit oz = new VolumeUnit(1);
    static VolumeUnit gill = new VolumeUnit(5, oz);
    static VolumeUnit pint = new VolumeUnit(4, gill);
    static VolumeUnit quart = new VolumeUnit(2, pint);
    static VolumeUnit gallon = new VolumeUnit(4, quart);

    private final int baseAmount;

    public VolumeUnit(int baseAmount) {
        this.baseAmount = baseAmount;
    }

    public VolumeUnit(int amount, VolumeUnit unit) {
        this(amount * unit.baseAmount);
    }

    public Number convertTo(VolumeUnit other, Number amount) {
        return amount.doubleValue() * baseAmount / other.baseAmount;
    }
}

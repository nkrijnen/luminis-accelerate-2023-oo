package eu.luminis.workshop.cleancode

class VolumeUnit(private val baseAmount: Int) {
    companion object {
        val oz = VolumeUnit(1)
        val gill = VolumeUnit(5, oz)
        val pint = VolumeUnit(4, gill)
    }

    private constructor(amount: Int, unit: VolumeUnit) : this(amount * unit.baseAmount)

    fun convertTo(other: VolumeUnit, amount: Number): Number = amount.toInt() * baseAmount / other.baseAmount
}

class Volume(private val amount: Number, private val unit: VolumeUnit) {
    override fun equals(other: Any?): Boolean {
        if (other !is Volume) return false
        return this.amount == other.unit.convertTo(this.unit, other.amount)
    }
}

fun Number.oz() = Volume(this, VolumeUnit.oz)
fun Number.gill() = Volume(this, VolumeUnit.gill)
fun Number.pint() = Volume(this, VolumeUnit.pint)

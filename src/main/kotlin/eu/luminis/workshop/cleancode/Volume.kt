package eu.luminis.workshop.cleancode

import kotlin.math.abs

internal class VolumeUnit(private val baseAmount: Int) {
    companion object {
        val oz = VolumeUnit(1)
        val gill = VolumeUnit(5, oz)
        val pint = VolumeUnit(4, gill)
        val quart = VolumeUnit(2, pint)
        val gallon = VolumeUnit(4, quart)
    }

    private constructor(amount: Int, unit: VolumeUnit) : this(amount * unit.baseAmount)

    internal fun convertTo(other: VolumeUnit, amount: Number): Number =
        amount.toDouble() * baseAmount / other.baseAmount
}

class Volume internal constructor(private val amount: Number, private val unit: VolumeUnit) {
    override fun equals(other: Any?): Boolean {
        if (other !is Volume) return false
        return amount.sameValueAs(convertToThisUnit(other))
    }

    override fun hashCode(): Int {
        var result = amount.hashCode()
        result = 31 * result + unit.hashCode()
        return result
    }

    operator fun plus(other: Volume) = Volume(amount + convertToThisUnit(other), this.unit)

    operator fun minus(other: Volume) = Volume(amount - convertToThisUnit(other), this.unit)

    private fun convertToThisUnit(other: Volume) = other.unit.convertTo(this.unit, other.amount)
}

private operator fun Number.plus(other: Number): Number = this.toDouble() + other.toDouble()

private operator fun Number.minus(other: Number): Number = this.toDouble() - other.toDouble()

private fun Number.sameValueAs(other: Number): Boolean =
    abs(this.toDouble() - other.toDouble()) < 0.0000000001

val Number.oz get() = Volume(this, VolumeUnit.oz)
val Number.gill get() = Volume(this, VolumeUnit.gill)
val Number.pint get() = Volume(this, VolumeUnit.pint)
val Number.quart get() = Volume(this, VolumeUnit.quart)
val Number.gallon get() = Volume(this, VolumeUnit.gallon)

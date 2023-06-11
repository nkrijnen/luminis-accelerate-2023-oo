package eu.luminis.workshop.cleancode

import kotlin.math.abs

val Number.oz get() = Volume(this, VolumeUnit.oz)
val Number.gill get() = Volume(this, VolumeUnit.gill)
val Number.pint get() = Volume(this, VolumeUnit.pint)
val Number.quart get() = Volume(this, VolumeUnit.quart)
val Number.gallon get() = Volume(this, VolumeUnit.gallon)

internal class VolumeUnit(private val baseAmount: Int = 1) {
    companion object {
        val oz = VolumeUnit()
        val gill = VolumeUnit(5, oz)
        val pint = VolumeUnit(4, gill)
        val quart = VolumeUnit(2, pint)
        val gallon = VolumeUnit(4, quart)
    }

    private constructor(amount: Int, unit: VolumeUnit) : this(amount * unit.baseAmount)

    internal fun convertTo(other: VolumeUnit, amount: Number): Number =
        amount.toDouble() * baseAmount / other.baseAmount

    internal fun convertToBaseUnit(amount: Number): Number =
        amount.toDouble() * baseAmount / oz.baseAmount
}

class Volume internal constructor(private val amount: Number, private val unit: VolumeUnit) {
    override fun equals(other: Any?): Boolean {
        if (other !is Volume) return false
        return amount.sameValueAs(convertToThisUnit(other))
    }

    override fun hashCode(): Int {
        return amountInBaseUnit().hashCode()
    }

    operator fun plus(other: Volume) = Volume(amount + convertToThisUnit(other), this.unit)

    operator fun minus(other: Volume) = Volume(amount - convertToThisUnit(other), this.unit)

    operator fun times(factor: Number) = Volume(amount * factor, unit)

    operator fun div(factor: Number) = Volume(amount / factor, unit)

    private fun amountInBaseUnit() = unit.convertToBaseUnit(amount)

    private fun convertToThisUnit(other: Volume) = other.unit.convertTo(this.unit, other.amount)
}

private operator fun Number.plus(other: Number): Number = this.toDouble() + other.toDouble()

private operator fun Number.minus(other: Number): Number = this.toDouble() - other.toDouble()

private operator fun Number.times(factor: Number): Number = this.toDouble() * factor.toDouble()

private operator fun Number.div(factor: Number): Number = this.toDouble() / factor.toDouble()

private const val epsilon = 0.0000000001

private fun Number.sameValueAs(other: Number): Boolean =
    abs(this.toDouble() - other.toDouble()) < epsilon

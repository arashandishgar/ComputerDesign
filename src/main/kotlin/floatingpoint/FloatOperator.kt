package floatingpoint

import util.*

const val fractionSize = 23
val fractionRangeDigit: IntRange by lazy {
    1..fractionSize
}

const val exponentSize = 8
val exponentRangeDigit: IntRange by lazy {
    fractionSize + 1..fractionSize + exponentSize
}

const val signSize = 1
const val signDigit = 32;

fun plusFloat(n1: Float, n2: Float) {
    val n1BinaryValue = n1.toBits()
    val n2BinaryValue = n2.toBits()
    val numberOfShift = n1BinaryValue.getExponent() plusBool n2BinaryValue.getOppositeValue()
    val n2FracttionShifted =
        getShiftedFraction(n2BinaryValue.getRawFraction(), numberOfShift).showTwoBinaryRepresentation()
    // for generate one
    val n1FracttionShifted = getShiftedFraction(n1BinaryValue.getRawFraction(), 0).showTwoBinaryRepresentation()

    val sumFraction = n1FracttionShifted plusBool n2FracttionShifted


}

fun getExponent(n: Int): Int {
    var v = 0
    for (i in exponentRangeDigit) {
        v = concatBinaryValueTwoNumber(n findDigit i, v, i - fractionSize - 1)
    }
    return v
}


fun getExponent(n: Float) = getExponent(n.toBits())

@JvmName("getExponent_extend for float")
fun Float.getExponent() = getExponent(this)

@JvmName("getExponent_extend for Int")
fun Int.getExponent() = getExponent(this)

// get fraction ieee754 without sing
fun getRawFraction(fraction: Int): Int {
    var v = 0
    for (i in fractionRangeDigit) {
        v = concatBinaryValueTwoNumber(fraction findDigit i, v, i - 1)
    }
    return v
}

fun getRawFraction(n: Float) = getRawFraction(n.toBits())

@JvmName("getRawFraction_extend for float")
fun Float.getRawFraction() = getRawFraction(this)

@JvmName("getRawFraction_extend for Int")
fun Int.getRawFraction() = getExponent(this)

fun getShiftedFraction(fraction: Int, numberOFShift: Int) =
    ((fraction shr numberOFShift) or (concatBinaryValueTwoNumber(1, 0, fractionSize - numberOFShift)))

fun getShiftedFraction(number: Float, numberOFShift: Int) = getShiftedFraction(number.getRawFraction(), numberOFShift)

@JvmName("getShiftedFraction_extend for Int")
infix fun Int.getShiftedFraction(numberOFShift: Int) = getShiftedFraction(this, numberOFShift)

@JvmName("getShiftedFraction_extend for float")
infix fun Float.getShiftedFraction(numberOFShift: Int) = getShiftedFraction(this.getRawFraction(), numberOFShift)

fun main() {
/*    ((10f).getRawFraction().getShiftedFraction(0)
            plusBool (-9f).getRawFraction().getShiftedFraction(0).getOppositeValue())
        .getOppositeValue().printBinaryString()
    println()*/
    println()
val a=(100f).getRawFraction().getShiftedFraction(0) plusBool
           (-98f).getRawFraction().getShiftedFraction(0).getOppositeValue()
    (a shl 5 ).printBinaryString()
    println()
    println((1.8f+2.1f))
    println((2.1-1.9)==.2)
    println(Float.fromBits(0b01000000000000000000000000000000))
}
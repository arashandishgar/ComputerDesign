package floatingpoint

import util.concatBinaryValueTwoNumber
import util.findDigit

const val EXPONENT_BASE_NUM=127
const val Fraction_Size = 23
val Fraction_Range_Digit: IntRange by lazy {
    1..Fraction_Size
}

const val Exponent_Size = 8
val Exponent_Range_Digit: IntRange by lazy {
    Fraction_Size + 1 .. Fraction_Size + Exponent_Size
}

const val Sign_Size = 1
const val Sign_Digit = 32

fun getExponent(n: Int): Int =n.getRangeOFBit(Exponent_Range_Digit)

fun getExponent(n: Float) = getExponent(n.toBits())

@JvmName("getExponent_extend for float")
fun Float.getExponent() = getExponent(this)

@JvmName("getExponent_extend for Int")
fun Int.getExponent() = getExponent(this)

// get fraction ieee754 without sing
fun getRawFraction(fraction: Int): Int =fraction.getRangeOFBit(Fraction_Range_Digit)

fun getRawFraction(n: Float) = getRawFraction(n.toBits())

@JvmName("getRawFraction_extend for float")
fun Float.getRawFraction() = getRawFraction(this)

@JvmName("getRawFraction_extend for Int")
fun Int.getRawFraction() = getRawFraction(this)

fun getShiftedFraction(fraction: Int, numberOFShift: Int) =
    (concatBinaryValueTwoNumber(1, fraction shr numberOFShift, Fraction_Size - numberOFShift))

fun getShiftedFraction(number: Float, numberOFShift: Int) = getShiftedFraction(number.getRawFraction(), numberOFShift)

@JvmName("getShiftedFraction_extend for Int")
infix fun Int.getShiftedFraction(numberOFShift: Int) = getShiftedFraction(this, numberOFShift)

@JvmName("getShiftedFraction_extend for float")
infix fun Float.getShiftedFraction(numberOFShift: Int) = getShiftedFraction(this.getRawFraction(), numberOFShift)

fun findOne(fraction: Int):Int {
    // plus two becuse we assume 25 bit for addition and sbtraction
    for (i in Fraction_Size+2 downTo  1){
        if((fraction findDigit i) ==1){
            return i - (Fraction_Size+1)
        }
    }
    return Int.MIN_VALUE
}
// first must be smaller than last
infix fun Int.getRangeOFBit(intRange:IntRange):Int{
    val first =intRange.first
    var v=0
    for (i in intRange) {
        v = concatBinaryValueTwoNumber(this findDigit i, v, i - first)
    }
    return v
}
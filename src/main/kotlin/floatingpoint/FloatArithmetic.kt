package floatingpoint

import util.*


fun plusFloat(n1: Float, n2: Float): Float {
    val n1BinaryValue = n1.toBits()
    val n2BinaryValue = n2.toBits()
    var mainExponent = n1BinaryValue.getExponent()
    var numberOfShift = n1BinaryValue.getExponent() plusBool n2BinaryValue.getExponent().getOppositeValue()
    val n2FracttionShifted =
        n2BinaryValue.getRawFraction().getShiftedFraction(numberOfShift)
            .showTwoBinaryRepresentation(n2BinaryValue findDigit Sign_Digit)
    // for generate one
    val n1FracttionShifted =
        getShiftedFraction(
            n1BinaryValue.getRawFraction(),
            0
        ).showTwoBinaryRepresentation(n1BinaryValue findDigit Sign_Digit)
    var sumFraction = n2FracttionShifted plusBool n1FracttionShifted
    val sign = sumFraction findDigit Sign_Digit
    sumFraction = sumFraction.showTwoBinaryRepresentation(sign)
    numberOfShift = findOne(sumFraction)
    mainExponent = mainExponent plusBool numberOfShift
    if (numberOfShift >= 0) {
        sumFraction = sumFraction shr numberOfShift
    } else if (numberOfShift == Int.MIN_VALUE) {
        return 0f
    } else {
        sumFraction = sumFraction shl numberOfShift.getOppositeValue()
    }
    var result = (0f).toBits()
    for (i in Fraction_Range_Digit) {
        result = concatBinaryValueTwoNumber(sumFraction findDigit i, result, i - 1)
    }
    result = concatBinaryValueTwoNumber(mainExponent, result, Fraction_Size)
    result = concatBinaryValueTwoNumber(sign, result, Sign_Digit - Sign_Size)
    return Float.fromBits(result)
}

fun main() {
    val a = plusFloat(2.1f, 1.9f)
    println(a)
}

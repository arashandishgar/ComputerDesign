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
    var result = sumFraction.getRawFraction()
    result = concatBinaryValueTwoNumber(mainExponent, result, Fraction_Size)
    result = concatBinaryValueTwoNumber(sign, result, Sign_Digit - Sign_Size)
    return Float.fromBits(result)
}

fun multiplictcationFloat(n1: Float, n2: Float): Float {
    val n1BinaryValue = n1.toBits()
    val n2BinaryValue = n2.toBits()
    val n1Sign = n1BinaryValue findDigit Sign_Digit
    val n2Sign = n2BinaryValue findDigit Sign_Digit
    val n1Exponent = n1BinaryValue.getExponent()
    val n1ExponentABS = Math.abs(n1Exponent plusBool (-EXPONENT_BASE_NUM))
    val n2Exponent = n2BinaryValue.getExponent()
    val n2ExponentABS = Math.abs(n2Exponent plusBool (-EXPONENT_BASE_NUM))
    val n1Fraction = n1BinaryValue.getRawFraction()
    val n1FractionShifted =
        n1Fraction.getShiftedFraction(0).getRangeOFBit(
            ((Fraction_Size + 1) - n1ExponentABS)..(Fraction_Size + 1))
            val n2Fraction = n2BinaryValue . getRawFraction ()
    val n2FractionShifted =
        n2Fraction.getShiftedFraction(0).getRangeOFBit(((Fraction_Size + 1) - n2ExponentABS)..(Fraction_Size + 1))
    var sumExponent = (n1Exponent plusBool n2Exponent) plusBool (-EXPONENT_BASE_NUM)
    var multipicandValue = 0
    for (i in 1..n1ExponentABS + 1) {
        val n1Digit = n1FractionShifted findDigit i
        var temp = 0
        for (j in 1..n2ExponentABS + 1) {
            val v = (n2FractionShifted findDigit j) and n1Digit
            temp = concatBinaryValueTwoNumber(v, temp, j - 1)
           /* println(" i : $i - j : $j - temp : $temp - n1Digit : $n1Digit ")*/
        }
        temp = concatBinaryValueTwoNumber(temp, 0, i - 1)
        multipicandValue = multipicandValue plusBool temp
    }
    multipicandValue.println()
    val max = if (n1ExponentABS >= n2ExponentABS) n1ExponentABS else n2ExponentABS
    val numberOfBit = numberOfBitWithSign(multipicandValue)-1
    val numShif = numberOfBit - (n1ExponentABS+n2ExponentABS+1)
    numShif.println()
    sumExponent = sumExponent plusBool numShif
    multipicandValue = multipicandValue shr numShif
    sumExponent.println()
    var result = 0
    result = concatBinaryValueTwoNumber(multipicandValue.getRangeOFBit(1..7), result, (Fraction_Size - numberOfBit))
    result = concatBinaryValueTwoNumber(sumExponent, result, Fraction_Size)
    val resultSign = n1Sign and n2Sign
    result = concatBinaryValueTwoNumber(resultSign, result, Sign_Digit - 1)
    return Float.fromBits(result)
}

fun main() {
   println((multiplictcationFloat(100f, 2f)))
}

package booth.binary

import floatingpoint.getRangeOFBit
import util.*

fun calculate(multipicand: Int, multiplier1: Int): Int {
    var multiplier = multiplier1
    val numberOfBit = numberOfBitWithSign(multipicand)
    var q0 = 0
    var accumulated = 0
    val immutableMap = HashMap<Int, Int>().apply {
        put(1, multipicand)
        put(2, multipicand.getNagativeValue())
    }
    for (i in 0 until numberOfBit) {
        val q1q0 = concatBinaryValueTwoNumber(multiplier findDigit  1 , q0, 1)
        if (q1q0 == 2 || q1q0 == 1) {
            accumulated = accumulated plusBool immutableMap[q1q0]!!

        }
        /* val temp = (accumulated shl numberOfBit + 1) or (multiplier shl 1) or q0*/
        var temp = concatBinaryValueTwoNumber(multiplier, q0, 1)
        temp = concatBinaryValueTwoNumber(accumulated, temp, numberOfBit plusBool  1)
        var result = arethmathicShiftRight(temp,  numberOfBit shl 1 plusBool 1)
        q0 = result findDigit 1
        result = result deletNumberOfFromFirst  1
        multiplier = result.getRangeOFBit(1..numberOfBit)
        result = result deletNumberOfFromFirst  numberOfBit
        accumulated = result

    }
    val value = concatBinaryValueTwoNumber(accumulated, multiplier, numberOfBit)
    return value
}



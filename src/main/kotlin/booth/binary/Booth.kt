package booth.binary

fun calculate(multipicand: Int, multiplier1: Int): Int {
    var multiplier = multiplier1
    val numberOfBit = numberOfBit(multipicand)
    var q0 = 0
    var accumulated = 0
    val immutableMap = HashMap<Int, Int>().apply {
        put(1, multipicand)
        put(2, -multipicand)
    }
    for (i in 0 until numberOfBit) {
        val q1q0 = ((multiplier and 1) shl 1) or q0
        if (q1q0 == 2 || q1q0 == 1) {
            accumulated = plusBool(accumulated, immutableMap[q1q0]!!)

        }
        val temp = (accumulated shl numberOfBit + 1) or (multiplier shl 1) or q0
        var result = arethmathicShiftRight(temp, 2 * numberOfBit + 1)
        q0 = result and 1
        result = result shr 1

        multiplier = 0
        for (j in numberOfBit - 1 downTo 0) {
            multiplier += (result and (1 shl j))
        }
        for (j in numberOfBit - 1 downTo 0) {
            result = result shr 1
        }
        accumulated = result

    }
    val value = ((accumulated shl numberOfBit) or multiplier)
    return value;
}



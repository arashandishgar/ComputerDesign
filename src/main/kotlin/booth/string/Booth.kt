fun calculate(multipicand: Number, multiplier: Number): Int {
    val multipicand_binaryValue = binaryValue(multipicand)
    val numberOfBitMutltipcand = multipicand_binaryValue.length
    multipicand.isPositive = !multipicand.isPositive
    val multipicand_oppositeBinaryValue = binaryValue(multipicand)
    val temp = binaryValue(multiplier)
    var multiplier_binaryValue = complementWithCustomBasic(numberOfBitMutltipcand - temp.length, temp, multiplier.isPositive)
    var q0 = "0";
    var accumulated = complementWithCustomBasic(numberOfBitMutltipcand, "", true)
    val immutableMap = HashMap<String, String>().apply {
        put("01", multipicand_binaryValue)
        put("10", multipicand_oppositeBinaryValue)
    }
    val sb = StringBuilder()
    for (i in 0 until numberOfBitMutltipcand) {
        sb += multiplier_binaryValue[multipicand_binaryValue.lastIndex]
        sb += q0
        val q1q0 = sb.toString()
        if (q1q0 == "10" || q1q0 == "01") {
            accumulated = accumulated plusBool immutableMap[q1q0]!!
        }
        sb.clear();
        sb += accumulated
        sb += multiplier_binaryValue
        sb += q0
        val result = shiftRight(sb.toString())
        accumulated = result.slice(0 until numberOfBitMutltipcand)
        multiplier_binaryValue = result.slice(numberOfBitMutltipcand until 2 * numberOfBitMutltipcand)
        q0 = result[2 * numberOfBitMutltipcand].toString()
        sb.clear()
    }

    val t = sb.append(accumulated).append(multiplier_binaryValue).toString();
    println("binary value is : " + t)
    val value = showTwoComplement(t)
    return value;
}



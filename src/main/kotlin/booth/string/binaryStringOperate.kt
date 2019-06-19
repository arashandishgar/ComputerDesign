private const val possitive = "0"
private val negetive = "1"
val map: HashMap<Boolean, String> by lazy {
    HashMap<Boolean, String>().apply {
        put(true, "0")
        put(false, "1")
    }
}

private fun binaryValueWithoutSign(number: Number, sb: StringBuilder): String {
    if (number.number == 0)
        return sb.toString()
    val remainder = number.number % 2
    sb.insert(0, remainder)
    number /= 2
    return binaryValueWithoutSign(number, sb)
}

private fun twoCoplimention(number: String): String {
    val sb = StringBuilder()
    var isFirstOne = true
    for (i in number.lastIndex downTo 0 step 1) {
        val value = number[i]
        if (isFirstOne) {
            if (value == '1') {
                isFirstOne = !isFirstOne
            }
            sb.insert(0, value)
            continue
        }
        sb.insert(0, (value.toInt() - 48) xor 1)
    }
    sb.insert(0, negetive)
    return sb.toString()
}
fun main() {
    
}

fun binaryValue(number: Number): String {
    val temp = number.number
    val value = StringBuilder()
    binaryValueWithoutSign(number, value)
    if (!number.isPositive) {
        number.number = temp
        return twoCoplimention(value.toString())
    }
    number.number = temp
    return value.insert(0, possitive).toString()
}

fun complementWithCustomBasic(n: Int, number: String, isPositive: Boolean): String {
    val sb = StringBuilder()
    val value = map.get(isPositive)
    for (i in 1..n) {
        sb.append(value)
    }
    sb.append(number)
    return sb.toString()
}

fun shiftRight(string: String): String {
    val sb = java.lang.StringBuilder()
    sb += string[0]
    for (i in 1..string.lastIndex) {
        sb += string[i - 1]
    }
    return sb.toString()
}

fun showTwoComplement(number: String): Int {
    var v = 0
    val lastIndex = number.lastIndex
    for (i in number.lastIndex downTo 1) {
        v = v + ((2 pow (lastIndex - i)) * (number[i].toInt() - 48))
    }
    if (number[0].toString() == "1") {
        val temp = 2 pow number.lastIndex
        return (temp - v) * -1
    }
    return v
}

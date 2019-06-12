fun numberOfBit(number: Int): Int {
    var x = number
    x = Math.abs(x)
    var n = 0
    while (x != 0) {
        x = (x shr n)
        n++
    }
    return n + 1  //+1 for sign bit
}

fun plusBool(n1: Int, n2: Int?, numberOFBit: Int=31): Int {
    var carry = 0
    var s = 0
    for (i in 0 until 32) {
        val a = when (n1 and (1 shl i)) {
            0 -> 0
            else -> 1
        }
        val b = when (n2!!.and((1 shl i))) {
            0 -> 0
            else -> 1
        }

        s = s or ((a xor b xor carry) shl i)

        carry = (a and b) or (a and carry) or (b and carry)
    }

    return s
}

fun arethmathicShiftRight(int: Int, n: Int): Int {
    val i = (int shr 1)
    val l = when (int findDigit n) {
        0 -> 0
        else -> 1
    }.let {
        (it shl n) or i
    }
    return l
}

fun main() {
   println(arethmathicShiftRight(5, 4))
}


infix fun Int.findDigit(n2:Int):Int=when(this and(1 shl n2-1)){
    0 -> 0
    else ->1
}
infix fun Long.findDigit(n2:Int):Int=when(this and((1 shl n2-1).toLong())){
    0L-> 0
    else ->1
}
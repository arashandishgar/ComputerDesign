package util

val IntDigitRange = 1..Int.SIZE_BITS

fun numberOfBitWithSign(number: Int): Int {
    var x = number
    x = Math.abs(x)
    var n = 0
    while (x != 0) {
        x = (x shr n)
        n++
    }
    return n + 1  //+1 for sign bit
}

fun Int.getOppositeValue() = this.inv() + 1

infix fun Int.plusBool(n2: Int?): Int {
    var carry = 0
    var s = 0
    for (i in 1..32) {
        val a = this findDigit i
        val b = n2!! findDigit i
        //i number of digit you want and i-1 nunber of  shift
        s = concatBinaryValueTwoNumber(a xor b xor carry,s,i-1)
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
    val a=System.nanoTime()
    (10000 plusBool     10000).println()
    val b=System.nanoTime()
    println(b-a)
}


infix fun Int.findDigit(n2: Int): Int = when (this and (1 shl n2 - 1)) {
    0 -> 0
    else -> 1
}

fun sortByAbsValue(n1: Float, n2: Float): Pair<Float, Float> {
    val x = Math.abs(n1) - Math.abs(n2)
    when {
        x < 0 -> return Pair(n2, n1)
        else -> return Pair(n1, n2)
    }
}
fun showTwoBinaryRepresentation(n1:Int)=when(n1.isPositive()){
    true-> n1
    false -> n1.getOppositeValue()
}
@JvmName("showTwoBinaryRepresentation extend for int ")
fun Int.showTwoBinaryRepresentation()=showTwoBinaryRepresentation(this)

/**
 * conncctt two binary Value
 *
* @param i numberOfBit seccondNumber
*
* @return concat Binart
* @sample  concatBinaryValueTwoNumber (100 ,111 ,3) -> 100111
*/
fun concatBinaryValueTwoNumber(first: Int, seccond: Int, i: Int) = (first shl i) or seccond

fun isPositive(n1:Int)=(n1 findDigit 32)==0

@JvmName("isPositive extend for Int")
fun Int.isPositive()=isPositive(this)

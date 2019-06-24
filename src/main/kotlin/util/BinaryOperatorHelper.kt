package util

val Int_DIGIT_RANGE = 1..Int.SIZE_BITS

fun numberOfBitWithSign(number: Int): Int {
    var x = Math.abs(number)
    var n = 0
    do{
        n++
        x=x shr 1
    }while (x !=0)
    return n + 1  //+1 for sign bit
}

fun Int.getNagativeValue() = this.inv() + 1

infix fun Int.plusBool(n2: Int?): Int {
    var carry = 0
    var s = 0
    for (i in 1..32) {
        val a = this findDigit i
        val b = n2!! findDigit i
        //i number of digit you want and i-1 nunber of  shift
        s = concatBinaryValueTwoNumber(a xor b xor carry, s, i - 1)
        carry = (a and b) or (a and carry) or (b and carry)
    }

    return s
}


fun arethmathicShiftRight(int: Int, n: Int): Int {
    val i = (int shr 1)
    val l = (int findDigit n).let {
        (it shl n - 1) or i
    }
    return l
}

fun main() {
    "F".covertHexDesimalFormatDecimalInt().println()
}

infix fun Int.findDigit(n: Int): Int = when (this and (1 shl n - 1)) {
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

fun showTwoBinaryRepresentation(n1:Int,s: Int) = when (s) {
    0 -> n1
    else -> n1.getNagativeValue()
}

@JvmName("showTwoBinaryRepresentation extend for int ")
fun Int.showTwoBinaryRepresentation(s:Int) = showTwoBinaryRepresentation(this, s)

/**
 * conncctt two binary Value
 *
 * @param i numberOfBit seccondNumber
 *
 * @return concat Binart
 * @sample  concatBinaryValueTwoNumber (100 ,111 ,3) -> 100111
 */
fun concatBinaryValueTwoNumber(first: Int, seccond: Int, i: Int)
        = (first shl i) or seccond

fun isPositive(n1: Int) = (n1 findDigit 32) == 0

@JvmName("isPositive extend for Int")
fun Int.isPositive() = isPositive(this)

fun Int.abs():Int{
    val sing=this findDigit Int.SIZE_BITS
    var result=0
    for(i in Int_DIGIT_RANGE){
        val temp=(this findDigit i) xor sing
        result= concatBinaryValueTwoNumber(temp, result, i - 1)
    }
    result=result plusBool sing
    return result
}
fun String.covertHexDesimalFormatDecimalInt()= java.lang.Integer.parseInt(this.substringAfter("x"), 16);


package booth.binary

import util.abs
import util.findDigit
import util.getOppositeValue

fun main() {
    println("put multipicand betwee-5n -15 or 15")
    var multipicand = readLine()!!.toInt()
    println("put  multiplier 0 or 15")
    var multiplier = readLine()!!.toInt()
    if (multiplier.abs() > multipicand.abs()) {
        multipicand = multiplier.also {
            multiplier = multipicand
        }
    }
    val sign =multiplier findDigit Int.SIZE_BITS
    if (sign == 1) {
        multipicand =multipicand.getOppositeValue()
        multiplier = multiplier.getOppositeValue()
    }
    println(" multipicand : $multipicand -multiplier : $multiplier ")
    val a=System.nanoTime()
    println(calculate(multipicand, multiplier))
    val b=System.nanoTime()
    println(b-a)
}


package booth.binary

import util.abs
import util.findDigit
import util.getNagativeValue
import util.println

fun main() {
    println("put multipicand ")
    var multipicand = readLine()!!.toInt()
    println("put  multiplier")
    var multiplier = readLine()!!.toInt()
    if (multiplier.abs() > multipicand.abs()) {
        multipicand = multiplier.also {
            multiplier = multipicand
        }
    }
    val sign = multiplier findDigit Int.SIZE_BITS
    if (sign == 1) {
        multipicand = multipicand.getNagativeValue()
        multiplier = multiplier.getNagativeValue()
    }
    calculate(multipicand, multiplier).println()
}


package floatingpoint

import java.lang.Double.doubleToLongBits
import java.lang.Long.toBinaryString

fun main() {
    val a= Float.run { doubleToLongBits(.5) }
    println(Long.run {  toBinaryString(a)})
}
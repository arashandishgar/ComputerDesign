package floatingpoint

import util.println
import util.sortByAbsValue

fun main() {
    println("put first number")
    val temp1 = readLine()!!.toFloat()
    println("put first number")
    val temp2 = readLine()!!.toFloat()
    val (n1, n2) = sortByAbsValue(temp1, temp2)
     plusFloat(n1, n2).println()

}


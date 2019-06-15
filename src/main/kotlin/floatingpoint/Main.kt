package floatingpoint

import util.sortByAbsValue

fun main() {
    println("put first number")
    val temp1= readLine()!!.toFloat()
    println("put first number")
    val temp2= readLine()!!.toFloat()
    println("num 1 : $temp1   num2 : $temp2" )
    val (n1,n2)= sortByAbsValue(temp1, temp2)
    plusFloat(n1,n2)
}


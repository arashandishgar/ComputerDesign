package hazard

import util.standradAdderess

fun main() {
    println("put your path")
    val path= readLine()!!.standradAdderess()
    findHazard(path)
}
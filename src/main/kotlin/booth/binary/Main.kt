package booth.binary

fun main() {
    var canContinue = "y"
    println("put multipicand betwee-5n -15 or 15")
    var multipicand = readLine()!!.toInt()
    println("put  multiplier 0 or 15")
    var multiplier = readLine()!!.toInt()
    if (Math.abs(multiplier) > Math.abs(multipicand)) {
        multipicand = multiplier.also {
            multiplier = multipicand
        }
        if (multiplier < 0) {
            multipicand *= -1
            multiplier *= -1
        }
    }
    val a=System.nanoTime()
    println(calculate(multipicand, multiplier))
    val b=System.nanoTime()
    println(b-a)
}


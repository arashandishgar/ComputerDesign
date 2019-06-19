fun main() {
    var canContinue = "y"
    while (canContinue == "y") {
        println("put multipicand")
        var temp = readLine()!!.toInt()
        var multipicand = Number(temp >= 0, Math.abs(temp))
        println("put seccond multiplier")
        temp = readLine()!!.toInt()
        var multiplier = Number(temp >= 0, Math.abs(temp))
        if (multiplier.number > multipicand.number) {
            multipicand = multiplier.also { multiplier = multipicand }
        }
        var a=System.nanoTime()
        println(calculate(multipicand, multiplier))
        var b=System.nanoTime()
        print(b-a)
        println()
        println("continu!! y or press any key to exit")
        canContinue = readLine()!!
        print("SAS")
    }
}


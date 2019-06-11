private var booleanValue = true;

data class Number(var isPositive: Boolean, var number: Int) {
    operator fun divAssign(i: Int) {
        number /= i
    }
}

operator fun java.lang.StringBuilder.plusAssign(value: String) {
    this.append(value)
}

operator fun java.lang.StringBuilder.plusAssign(value: Char) {
    this.append(value)
}

infix fun String.plusBool(value: String): String {
    var c = 0
    val sb = StringBuilder()
    for (i in this.length - 1 downTo 0 step 1) {
        val a = this[i].toInt() - 48
        val b = value[i].toInt() - 48
        sb.insert(0, c xor a xor b)
        c = (a and b) or (a and c) or (b and c)
    }
    return sb.toString()
}

infix fun Int.pow(other:Int):Int{
    var v=1;
    for (i in 0 until  other){
        v*=this
    }
    return v
}

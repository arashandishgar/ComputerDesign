package util

fun Int.println() = println(this)


fun Int.printBinaryString() {
    for (i in IntDigitRange.reversed()) {
        print(this findDigit i)
    }
}
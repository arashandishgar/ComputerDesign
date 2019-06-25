package util

fun Any.println()=println(this)
fun Any.print()=print(this)

fun Int.printBinaryString() {
    for (i in Int_DIGIT_RANGE.reversed()) {
        print(this findDigit i)
    }
}
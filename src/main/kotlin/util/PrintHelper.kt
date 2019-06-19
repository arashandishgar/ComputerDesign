package util

fun Int.println() = println(this)


fun Int.printBinaryString() {
    for (i in Int_DIGIT_RANGE.reversed()) {
        print(this findDigit i)
    }
}
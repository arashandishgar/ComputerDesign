package util

const val COMMENT ="#"
const val LABEL_SIZE = 1
const val FORMAT_R = "R"
const val FORMAT_I = "I"
const val FORMAT_J = "J"
const val EXCEPTION_MESSAGE = "This instruction not exist In Data Base"

fun String.iterateLineOfAssembler()=substringBefore(COMMENT).split(" ", ",", "$", "(", ")", ":").filter { it.length != 0 }.
    map { it.substringAfter(" ") }
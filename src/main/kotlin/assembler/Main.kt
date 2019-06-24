package assembler

import util.DataBase


fun main() {
    println("put your assembler file path")
    val filePath = readLine()!!.replace("\\","/")
    println("put your DataBase Path")
    DataBase.path= readLine()!!.replace("\\","/")
    parseFileAssamblytoMachineCode(filePath)
}
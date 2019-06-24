package assembler

import util.DataBase


fun main() {
    //they can path assets folder
    println("put your assembler file path")
    val filePath = readLine()!!.replace("\\","/")
    println("put your DataBase Path")
    DataBase.path= readLine()!!.replace("\\","/")
    parseFileAssamblytoMachineCode(filePath)
}
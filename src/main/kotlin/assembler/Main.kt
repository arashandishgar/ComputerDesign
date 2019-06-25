package assembler

import util.DataBase
import util.standradAdderess


fun main() {
    //they can path assets folder
    println("put your assembler file path")
    val filePath = readLine()!!.standradAdderess()
    println("put your DataBase Path")
    DataBase.path= readLine()!!.standradAdderess()
    parseFileAssamblytoMachineCode(filePath)
}
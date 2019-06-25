package assembler

import util.*
import java.io.File

fun parseFileAssamblytoMachineCode(path: String) {
    val result = arrayListOf<BaseFormatParser>()
    var programCounter = 0
    val labelMap = mutableMapOf<String, Int>()
    File(path).forEachLine {
        programCounter = programCounter plusBool 4
        val list = it.iterateLineOfAssembler()
        if(list.size==0){
            //don not need any thing con not call continue here
            programCounter=programCounter plusBool -4
        }else if (list.size == LABEL_SIZE) {
            labelMap.put(list[0], programCounter)
        } else {
            val assemblerEntity = DataBase.findByMnemonicAssembler(list[0])!!
            when (assemblerEntity.type) {
                FORMAT_R -> result.add(parseR(assemblerEntity, list))
                FORMAT_I ->result.add (parseI(assemblerEntity, list, labelMap))
                FORMAT_J -> result.add(parseJ(assemblerEntity, list, labelMap))
                else -> throw Exception(EXCEPTION_MESSAGE)
            }
        }

    }
    labelMap.println()
    result.forEach {
        it.println()
    }
    result.map { it.showBinryString() }.forEach{
        it.println()
    }
}

private fun parseR(assemblerEntity: AssemblerEntity, list: List<String>): BaseFormatParser {
    var rd = 0
    var rs = 0
    //number or operand shift number value
    var shamt = 0
    var rt = 0
    when (list.size) {
        4 -> {
            rd = DataBase.findByNameRegister(list[1])!!.value
            rs = DataBase.findByNameRegister(list[2])!!.value
            val temp = list[3].substringAfter(" ").toIntOrNull()
            when (temp) {
                null -> {
                    rt = DataBase.findByNameRegister(list[3])!!.value
                    shamt = 0
                }
                else -> {
                    shamt = temp
                    rt = 0
                }
            }
        }
        3 -> {
            //for mult and other like format
            rs = DataBase.findByNameRegister(list[1])!!.value
            rt = DataBase.findByNameRegister(list[2])!!.value
            rd = 0
            shamt = 0
        }
        2 -> {
            // for jr
            rs = DataBase.findByNameRegister(list[1])!!.value
            rt = 0
            rd = 0
            shamt = 0
        }
    }
    val op = assemblerEntity.opcode.covertHexDesimalFormatDecimalInt()
    val funct = assemblerEntity.funct.covertHexDesimalFormatDecimalInt()

    return RFormat(op, rs, rt, rd, shamt, funct)

}

private fun parseI(
    assemblerEntity: AssemblerEntity,
    list: List<String>,
    labelMap: MutableMap<String, Int>
): BaseFormatParser {
    val rs=DataBase.findByNameRegister(list[1])!!.value
    var rt=0
    var imm=0
    val temp1=list[2].toIntOrNull()
    val temp2=list[3].toIntOrNull()
    when{
        temp1 is Int && temp2 == null ->{
            rt=DataBase.findByNameRegister(list[3])!!.value
            imm=temp1
        }
        temp1 == null && temp2 is Int ->{
            rt=DataBase.findByNameRegister(list[2])!!.value
            imm=temp2
        }
        // temp1==null && temp2==null
        // temp1==Int && temp2==Int never be happen
        else ->{
            rt=DataBase.findByNameRegister(list[2])!!.value
            labelMap.putIfAbsent(list[3],0)
            imm= labelMap[(list[3])]!!.div(4)
        }
    }
    return IFormat(assemblerEntity.opcode.covertHexDesimalFormatDecimalInt(),rs,rt,imm)

}

private fun parseJ(
    assemblerEntity: AssemblerEntity,
    list: List<String>,
    labelMap: MutableMap<String, Int>
): BaseFormatParser {
    labelMap.putIfAbsent(list[1],0)
    val programmeCounter = (labelMap.get(list[1]))?.div(4)
    return JFormat(assemblerEntity.opcode.covertHexDesimalFormatDecimalInt(), programmeCounter!!)
}

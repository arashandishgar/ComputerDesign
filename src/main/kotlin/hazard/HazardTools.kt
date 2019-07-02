package hazard

import util.getNagativeValue
import util.iterateLineOfAssembler
import util.plusBool
import util.println
import java.io.File

private const val HAZZARD_POTENTIAL_POINT = 3
private const val MIPS_PIPELINE_NUMBER_STEP = 5
private const val SW = "sw"
private const val INDEX_OPERATOR = 0
typealias MipsInstructLine = List<String>


fun findHazard(path: String) {
    val file = File(path)
    val instructeLineList = file.readLines().map { it.iterateLineOfAssembler() }.filter { it.canCauseHazard() }
    val lastIndex = instructeLineList.lastIndex
    val numberOfLineCheck = MIPS_PIPELINE_NUMBER_STEP plusBool  1.getNagativeValue()
    //until not prevent lastline for two times check
    for (i in 0 until instructeLineList.lastIndex) {
        val lastIndexCheck = i plusBool  numberOfLineCheck
        val range = if (lastIndexCheck > lastIndex) (i plusBool  1)..lastIndex else (i plusBool  1)..lastIndexCheck

        for (j in range) {
            var hasHazard = false
            hasHazard = hasHazard || checkForWAW(instructeLineList, i, j)
            hasHazard = hasHazard || checkForWAR(instructeLineList, i, j)
            hasHazard = hasHazard || checkForRAW(instructeLineList, i, j)
            if (hasHazard) {
                "line:${i plusBool  1} - ${instructeLineList[i]} and line: ${j plusBool  1} - ${instructeLineList[j]} has hazard".println()
            }
        }
    }
}


fun checkForWAW(listOfValueLine: List<MipsInstructLine>, writeIndex0: Int, writeIndex1: Int):Boolean {
    val index0 =if( listOfValueLine[writeIndex0, INDEX_OPERATOR].equals(SW)) 3 else 1
    val index1 = if( listOfValueLine[writeIndex1, INDEX_OPERATOR].equals(SW)) 3 else 1
   return listOfValueLine[writeIndex0, index0].equals(listOfValueLine[writeIndex1, index1])
}


fun checkForWAR(instructeList: List<MipsInstructLine>, readIndex: Int, writeIndex: Int) =
    checkHazardForReadandWrite(instructeList, writeIndex, readIndex)

fun checkForRAW(instructeLineList: List<List<String>>, wrieIndex: Int, readIndex: Int) =
    checkHazardForReadandWrite(instructeLineList, wrieIndex, readIndex)


fun checkHazardForReadandWrite(instructeList: List<List<String>>, writeIndex: Int, readIndex: Int): Boolean {
    val isSw = instructeList[writeIndex, INDEX_OPERATOR].substringAfter(" ").equals(SW)
    val registerWrite = if (isSw) instructeList[writeIndex, 3] else instructeList[writeIndex, 1]
    val numberOperand = instructeList[readIndex].getNumberOfOperand()
    when {
        instructeList[readIndex, INDEX_OPERATOR].substringAfter(" ").equals(SW) -> return instructeList[readIndex, 1].equals(
            registerWrite
        )
        numberOperand == 3 -> return (instructeList[readIndex, 2].equals(registerWrite) || instructeList[readIndex, 3].equals(
            registerWrite
        ))
        else -> return instructeList[readIndex, 2].equals(registerWrite)
    }
}

/*
fun checkForRAW(listOfValueLine: List<MipsInstructLine>, firstIndex: Int, seccondIndex: Int): Boolean {

}
*/


fun main() {
    findHazard("src/main/assets/assamblyFileExample.txt")

}

// less 3 contain jr jar label and not happed hazzard
private fun MipsInstructLine.canCauseHazard() = this.size >= HAZZARD_POTENTIAL_POINT

private operator fun List<MipsInstructLine>.get(i: Int, j: Int) =
    this.get(i).get(j)

private fun MipsInstructLine.getNumberOfOperand() =
    when (this.size) {
        4 -> 3
        // 3
        else -> 2
    }

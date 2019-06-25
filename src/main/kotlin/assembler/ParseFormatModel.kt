package assembler

import util.findDigit

sealed class BaseFormatParser() {
    abstract val opCode: Int
    abstract fun showBinryString():String
}

data class RFormat(override val opCode: Int, val rs: Int, val rt: Int, val rd: Int, val shamt: Int, val funct: Int) :
    BaseFormatParser() {

    companion object {
        val OPCODD_Size = 6
        val RS_SIZE = 5
        val RT_SIZE = 5
        val RD_SIZE = 5
        val SHAMT_SIZE = 5
        val FUNCT_SIZE = 6
    }
    override fun showBinryString(): String {
        val sb=StringBuilder()
        sb.append(opCode getRangeOfBitSizeToString OPCODD_Size)
        sb.append(rs getRangeOfBitSizeToString RS_SIZE)
        sb.append(rt getRangeOfBitSizeToString RT_SIZE)
        sb.append(rd getRangeOfBitSizeToString RD_SIZE)
        sb.append(shamt getRangeOfBitSizeToString SHAMT_SIZE)
        sb.append(funct getRangeOfBitSizeToString FUNCT_SIZE)
        return sb.toString()
    }


}

data class IFormat(override val opCode: Int, val rs: Int, val rt: Int, val imm: Int) : BaseFormatParser() {
    companion object {
        val OPCODD_Size = 6
        val RS_SIZE = 5
        val RT_SIZE = 5
        val IMM_SIZE = 16

    }
    override fun showBinryString(): String {
        val sb=StringBuilder()
        sb.append(opCode getRangeOfBitSizeToString OPCODD_Size)
        sb.append(rs getRangeOfBitSizeToString RS_SIZE)
        sb.append(rt getRangeOfBitSizeToString RT_SIZE)
        sb.append(imm getRangeOfBitSizeToString IMM_SIZE)
        return sb.toString()
    }
}

data class JFormat(override val opCode: Int, val address: Int) : BaseFormatParser() {
    companion object {
        val OPCODD_Size = 6
        val ADDRESS_SIZE = 26
    }
    override fun showBinryString(): String {
        val sb=StringBuilder()
        sb.append(opCode getRangeOfBitSizeToString OPCODD_Size)
        sb.append(address getRangeOfBitSizeToString ADDRESS_SIZE)
        return sb.toString()
    }
}
private  infix fun Int.getRangeOfBitSizeToString(range:Int):String{
    val sb=StringBuilder()
    for (i in range downTo 1){
        sb.append(this findDigit i)
    }
    return sb.toString()
}


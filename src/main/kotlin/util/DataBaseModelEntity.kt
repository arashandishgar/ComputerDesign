package util

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

sealed class MyIntEntity(id: EntityID<Int>) : IntEntity(id) {
    public abstract fun showString(): String
    override fun toString(): String = showString()
}


class RegisterEntity(id: EntityID<Int>) : MyIntEntity(id) {
    override fun showString(): String =
        "[name : $name , value : $value ,usage : $usage]"

    companion object : IntEntityClass<RegisterEntity>(Register)

    val name by Register.name
    val value by Register.value
    val usage by Register.usage

}

class AssemblerEntity(id: EntityID<Int>) : MyIntEntity(id) {
    companion object : IntEntityClass<AssemblerEntity>(Assembler)

    var mnemonic by Assembler.mnemonic
    var meaning by Assembler.meaning
    var type by Assembler.type
    var opcode by Assembler.opcode
    var funct by Assembler.funct
    override fun showString(): String =
        "[mnemonic : $mnemonic , meaning : $meaning , type : $type , opcode : $opcode, funct : $funct]"

}

package util

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column

//TableName with Table Class equal name
sealed class MyIntIdTable:IntIdTable()

object Assembler : MyIntIdTable() {
    val mnemonic: Column<String> = text("Mnemonic")
    val meaning: Column<String> = text("Meaning")
    val type: Column<String> = text("Type")
    val opcode: Column<String> = text("Opcode")
    val funct: Column<String> = text("Funct")
}

object Register : MyIntIdTable() {
    val name: Column<String> = text("Name")
    val value: Column<Int> = integer("Value")
    val usage: Column<String> = text("Usage")
}
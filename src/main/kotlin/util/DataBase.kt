package util

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

object DataBase {

    var path = ""
    val db by lazy {
        Database.connect("jdbc:sqlite:" + path, "org.sqlite.JDBC")
    }

    private fun <T, U> runDataBaseQuery(parameterName: T, a: (T) -> U): U? {
        db
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE
        var result: U? = null
        transaction {
            result = a(parameterName)
        }
        return result
    }

    fun findByMnemonicAssembler(mnemonic: String) =
        runDataBaseQuery(mnemonic) {
            AssemblerEntity.find { Assembler.mnemonic eq mnemonic.stringParameterStandardForDataBase() }.toList().get(0)
        }

    fun findByNameRegister(name: String) =
        runDataBaseQuery(name) {
            RegisterEntity.find { Register.name eq name.stringParameterStandardForDataBase() }.toList().get(0)
        }

    fun findByIdAssembler(id: Int) =
        runDataBaseQuery(id) {
            AssemblerEntity.find { Assembler.id eq id }.toList().get(0)
        }

    fun findByIdRegister(id: Int) =
        runDataBaseQuery(id) {
            RegisterEntity.find { Register.id eq id }.toList().get(0)
        }

}

private fun String.stringParameterStandardForDataBase() = this.replace(" ", "").toLowerCase()


package ru.welokot.monopoly.db.entity.gameMove

import android.util.Log
import ru.welokot.monopoly.models.TypeCapital
import java.io.Serializable

class GameMoveEntity (
    var id: Int = 0,
    val transferMoneyTo: MutableSet<Int> = mutableSetOf(),
    val transferMoneyFrom: MutableSet<Int> = mutableSetOf(),
    var transferAmount: String = "0",
    var transferTypeCapital: TypeCapital = TypeCapital.K,
    val isThisCancellation: Boolean = false,
    val numberOfCanceledMove: Int = 0
) : Serializable {

    fun addTransaction(id: Int, type: TypeTransaction) {
        when (type) {
            TypeTransaction.TO -> {
                transferMoneyTo.add(id)
            }
            TypeTransaction.FROM -> {
                transferMoneyFrom.add(id)
            }
            else -> Log.d("TAG", "Это не должно было произойти :)")
        }
    }

    fun deleteTransaction(id: Int) {
        transferMoneyTo.remove(id)
        transferMoneyFrom.remove(id)
    }

    fun getTypeTransaction(id: Int): TypeTransaction {
        return when (id) {
            in transferMoneyTo -> TypeTransaction.TO
            in transferMoneyFrom -> TypeTransaction.FROM
            else -> TypeTransaction.NOTHING
        }
    }
}

enum class TypeTransaction {
    TO,
    FROM,
    NOTHING
}
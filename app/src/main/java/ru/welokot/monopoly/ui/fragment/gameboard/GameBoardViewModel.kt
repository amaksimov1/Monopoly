package ru.welokot.monopoly.ui.fragment.gameboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.welokot.monopoly.db.Player
import javax.inject.Inject

class GameBoardViewModel
    @Inject constructor() : ViewModel() {

    private var playersList: MutableList<Player> = mutableListOf()
    private val transferMoneyTo: MutableSet<Int> = mutableSetOf()
    private val transferMoneyFrom: MutableSet<Int> = mutableSetOf()

    var playersListLiveData: MutableLiveData<MutableList<Player>> = MutableLiveData()

    fun setPlayersList(players: MutableList<Player>) {
        playersList = mutableListOf()
        playersList.addAll(players)
        playersListLiveData.postValue(playersList)
    }

    fun addTransaction(position: Int, type: TypeTransaction) {
        when (type) {
            TypeTransaction.TO -> {
                transferMoneyTo.add(position)
            }
            TypeTransaction.FROM -> {
                transferMoneyFrom.add(position)
            }
        }
    }

    fun deleteTransaction(position: Int) {
        transferMoneyTo.remove(position)
        transferMoneyFrom.remove(position)
    }

    fun getTypeTransaction(position: Int): TypeTransaction? {
        if (position in transferMoneyTo) return TypeTransaction.TO
        else if (position in transferMoneyFrom) return TypeTransaction.FROM
        return null
    }

    fun commitTransfer(transferAmount: Float) {
        transferMoneyTo.forEach {
            for (i in 0 until transferMoneyFrom.size) {
                playersList[it].capital += transferAmount
            }
        }

        transferMoneyFrom.forEach {
            for (i in 0 until transferMoneyTo.size) {
                playersList[it].capital -= transferAmount
            }
        }

        transferMoneyTo.clear()
        transferMoneyFrom.clear()

        playersListLiveData.postValue(playersList)
    }
}

enum class TypeTransaction {
    TO,
    FROM
}
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

    fun updatePlayersList(players: MutableList<Player>) {
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
}

enum class TypeTransaction {
    TO,
    FROM
}
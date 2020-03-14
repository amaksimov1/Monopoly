package ru.welokot.monopoly.ui.fragment.gameboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.welokot.monopoly.db.AppDatabase
import ru.welokot.monopoly.db.entity.gameMove.GameMoveEntity
import ru.welokot.monopoly.db.entity.gameMove.TypeTransaction
import ru.welokot.monopoly.db.entity.gameSession.GameSessionEntity
import ru.welokot.monopoly.db.entity.player.PlayerEntity
import ru.welokot.monopoly.models.TypeCapital
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameBoardViewModel
    @Inject constructor(
        val appDatabase: AppDatabase
    ) : ViewModel() {

    private lateinit var gameSession: GameSessionEntity
    private lateinit var gameMove: GameMoveEntity


    private val transferMoneyTo: MutableSet<Int> = mutableSetOf()
    private val transferMoneyFrom: MutableSet<Int> = mutableSetOf()

    var playersListLiveData: MutableLiveData<List<PlayerEntity>> = MutableLiveData()

    fun setGameSession(gameSession: GameSessionEntity) {
        this.gameSession = gameSession
        gameMove = gameSession.getNewMove()
        playersListLiveData.postValue(gameSession.playersList)
    }

    fun addTransaction(id: Int, type: TypeTransaction) {
        gameMove.addTransaction(id, type)
    }

    fun deleteTransaction(id: Int) {
        gameMove.deleteTransaction(id)
    }

    fun getTypeTransaction(id: Int): TypeTransaction {
        return gameMove.getTypeTransaction(id)
    }

    fun commitTransaction(transactionAmount: String, typeCapital: TypeCapital) {
        gameSession.saveGameMove(gameMove, transactionAmount, typeCapital)
        appDatabase.gameSessionDao().update(gameSession)
        gameMove = gameSession.getNewMove()
        playersListLiveData.postValue(gameSession.playersList)
    }
}
package ru.welokot.monopoly.ui.fragment.gameboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.welokot.monopoly.db.AppDatabase
import ru.welokot.monopoly.db.entity.gameMove.GameMoveEntity
import ru.welokot.monopoly.db.entity.gameMove.TypeTransaction
import ru.welokot.monopoly.db.entity.gameSession.GameSessionEntity
import ru.welokot.monopoly.db.entity.player.PlayerEntity
import ru.welokot.monopoly.models.TypeCapital
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class GameBoardViewModel
    @Inject constructor(
        private val appDatabase: AppDatabase
    ) : ViewModel(), CoroutineScope {

    private val job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private lateinit var gameSession: GameSessionEntity

    var gameSessionLiveData: MutableLiveData<GameSessionEntity> = MutableLiveData()

    fun setGameSession(gameSession: GameSessionEntity) = launch(Dispatchers.IO) {
        this@GameBoardViewModel.gameSession = gameSession
        this@GameBoardViewModel.gameSession.lastRun = Date().time
        appDatabase.gameSessionDao().update(gameSession)
        gameSessionLiveData.postValue(gameSession)
    }

    fun getGameSession() = gameSession

    fun commitTransaction(transactionAmount: String, typeCapital: TypeCapital) = launch(Dispatchers.IO) {
        if (toAndFromIsNotEmpty()) {
            gameSession.applyCurrentGameMove(transactionAmount, typeCapital)
            appDatabase.gameSessionDao().update(gameSession)
            gameSessionLiveData.postValue(gameSession)
        }
    }

    private fun toAndFromIsNotEmpty(): Boolean {
        return gameSession.getCurrentGameMove().transferMoneyTo.isNotEmpty() &&
                gameSession.getCurrentGameMove().transferMoneyFrom.isNotEmpty()
    }

    fun changeTypeTransaction(gameSession: GameSessionEntity, playerId: Int) {
        val currentGameMove = gameSession.getCurrentGameMove()
        when (currentGameMove.getTypeTransaction(playerId)) {
            TypeTransaction.NOTHING -> currentGameMove.addTransaction(
                playerId,
                TypeTransaction.FROM
            )
            TypeTransaction.FROM -> {
                currentGameMove.deleteTransaction(playerId)
                currentGameMove.addTransaction(playerId, TypeTransaction.TO)
            }
            TypeTransaction.TO -> currentGameMove.deleteTransaction(playerId)
        }
        this.gameSession = gameSession
        gameSessionLiveData.postValue(gameSession)
    }
}
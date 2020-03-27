package ru.welokot.monopoly.ui.fragment.gamemoves

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.welokot.monopoly.db.AppDatabase
import ru.welokot.monopoly.db.entity.gameSession.GameSessionEntity
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class GameMovesViewModel @Inject constructor(
    private val appDatabase: AppDatabase
) : ViewModel(), CoroutineScope {

    private val job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private var gameSession = GameSessionEntity()
    var gameSessionLiveData: MutableLiveData<GameSessionEntity> = MutableLiveData()

    fun setGameSession(_gameSession: GameSessionEntity) {
        gameSession = _gameSession
        gameSessionLiveData.postValue(gameSession)
    }

    fun getGameSession() = gameSession

    fun cancelGameMove(position: Int) = launch(Dispatchers.IO) {
        val cancellableGameMove = gameSession.getCurrentGameMove()
        cancellableGameMove.apply {
            isCancellation = true
            CanceledMoveId = position
            transferMoneyTo.addAll(gameSession.gameMovesList[position].transferMoneyFrom)
            transferMoneyFrom.addAll(gameSession.gameMovesList[position].transferMoneyTo)
        }
        gameSession.applyCurrentGameMove(
            gameSession.gameMovesList[position].transferAmount,
            gameSession.gameMovesList[position].transferTypeCapital
        )
        appDatabase.gameSessionDao().update(gameSession)
        gameSessionLiveData.postValue(gameSession)
    }
}
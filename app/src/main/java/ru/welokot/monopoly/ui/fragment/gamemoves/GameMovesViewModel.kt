package ru.welokot.monopoly.ui.fragment.gamemoves

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.welokot.monopoly.db.AppDatabase
import ru.welokot.monopoly.db.entity.gameMove.GameMoveEntity
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
    var gameMovesListLiveData: MutableLiveData<List<GameMoveEntity>> = MutableLiveData()

    fun setGameSession(gameSession: GameSessionEntity) {
        this.gameSession = gameSession
        gameMovesListLiveData.postValue(gameSession.gameMovesList)
    }
}
package ru.welokot.monopoly.ui.fragment.preparetogame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.welokot.monopoly.db.AppDatabase
import ru.welokot.monopoly.db.entity.gameSession.GameSessionEntity
import ru.welokot.monopoly.db.entity.player.PlayerEntity
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PrepareToGameViewModel @Inject constructor(
    private val appDatabase: AppDatabase
) : ViewModel(), CoroutineScope {

    private val job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private lateinit var gameSession: GameSessionEntity


    var playersListLiveData: MutableLiveData<MutableList<PlayerEntity>> = MutableLiveData()
    var gameSessionLiveData: MutableLiveData<GameSessionEntity> = MutableLiveData()

    fun init() {
        gameSession = GameSessionEntity()
        gameSession.addBank()
        playersListLiveData.postValue(gameSession.getPlayersList())
    }

    fun addPlayer(newPlayer: PlayerEntity) {
        gameSession.addPlayer(newPlayer)
        playersListLiveData.postValue(gameSession.getPlayersList())
    }

    fun deletePlayer(position: Int){
        gameSession.removePlayerByPosition(position)
    }

    fun saveNewGameSession() = launch(Dispatchers.IO) {
        val gameSessionId = appDatabase.gameSessionDao().insert(gameSession).toInt()
        gameSession.id = gameSessionId
        gameSessionLiveData.postValue(gameSession)
    }
}
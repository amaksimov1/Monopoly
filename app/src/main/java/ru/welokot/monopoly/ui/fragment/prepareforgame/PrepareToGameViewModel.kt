package ru.welokot.monopoly.ui.fragment.prepareforgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.welokot.monopoly.db.AppDatabase
import ru.welokot.monopoly.db.entity.gameSession.GameSessionEntity
import ru.welokot.monopoly.db.entity.player.PlayerEntity
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PrepareToGameViewModel @Inject constructor(
    private val appDatabase: AppDatabase
) : ViewModel(), CoroutineScope {

    private val job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private val playersList: MutableList<PlayerEntity> = mutableListOf()
    private var playersId: Int = 0

    var playersListLiveData: MutableLiveData<MutableList<PlayerEntity>> = MutableLiveData()
    var gameSessionLiveData: MutableLiveData<GameSessionEntity> = MutableLiveData()

    fun addPlayer(newPlayer: PlayerEntity) {
        newPlayer.id = playersId++
        playersList.add(newPlayer)
        playersListLiveData.postValue(playersList)
    }

    fun deletePlayer(position: Int){
        playersList.removeAt(position)
        playersListLiveData.postValue(playersList)
    }

    fun addNewGameSession() = launch(Dispatchers.IO) {
        val newSession =
            GameSessionEntity(
                lastRun = Date().time,
                playersList = playersList.toList()
            )

        val gameSessionId = appDatabase.gameSessionDao().insert(newSession).toInt()
        newSession.id = gameSessionId
        gameSessionLiveData.postValue(newSession)
    }
}
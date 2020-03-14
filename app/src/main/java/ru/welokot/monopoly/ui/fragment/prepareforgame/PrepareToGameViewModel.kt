package ru.welokot.monopoly.ui.fragment.prepareforgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.welokot.monopoly.db.AppDatabase
import ru.welokot.monopoly.db.entity.GameSessionEntity
import ru.welokot.monopoly.db.entity.PlayerEntity
import java.util.*
import javax.inject.Inject

class PrepareToGameViewModel @Inject constructor(
    val appDatabase: AppDatabase
) : ViewModel() {

    private val playersList: MutableList<PlayerEntity> = mutableListOf()
    private var playersId: Int = 0

    var playersListLiveData: MutableLiveData<MutableList<PlayerEntity>> = MutableLiveData()

    fun addPlayer(newPlayer: PlayerEntity) {
        newPlayer.id = playersId++
        playersList.add(newPlayer)
        playersListLiveData.postValue(playersList)
    }

    fun deletePlayer(position: Int){
        playersList.removeAt(position)
        playersListLiveData.postValue(playersList)
    }

    fun getPlayersList(): MutableList<PlayerEntity> {
        return playersList
    }

    fun addNewGameSession() {
        val newSession = GameSessionEntity(
            lastRun = Date().time,
            playersList = playersList
        )

    }
}
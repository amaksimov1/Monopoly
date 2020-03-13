package ru.welokot.monopoly.ui.fragment.prepareforgame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.welokot.monopoly.db.AppDatabase
import ru.welokot.monopoly.db.entity.PlayerEntity
import javax.inject.Inject

class PrepareToGameViewModel @Inject constructor(
    val appDatabase: AppDatabase
) : ViewModel() {

    private val playersList: MutableList<PlayerEntity> = mutableListOf()

    var playersListLiveData: MutableLiveData<MutableList<PlayerEntity>> = MutableLiveData()

    fun addPlayer(newPlayer: PlayerEntity) {
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
}
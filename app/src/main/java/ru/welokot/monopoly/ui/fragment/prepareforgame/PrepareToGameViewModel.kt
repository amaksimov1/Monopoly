package ru.welokot.monopoly.ui.fragment.prepareforgame

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.welokot.monopoly.db.AppDatabase
import ru.welokot.monopoly.db.Player
import javax.inject.Inject

class PrepareToGameViewModel @Inject constructor( val appDatabase: AppDatabase ) : ViewModel() {

    private val playersList: MutableList<Player> = mutableListOf()

    var playersListLiveData: MutableLiveData<MutableList<Player>> = MutableLiveData()

    fun addPlayer(newPlayer: Player) {
        playersList.add(newPlayer)
        playersListLiveData.postValue(playersList)
    }

    fun deletePlayer(position: Int){
        playersList.removeAt(position)
        playersListLiveData.postValue(playersList)
    }

    fun getPlayersList(): MutableList<Player> {
        return playersList
    }
}
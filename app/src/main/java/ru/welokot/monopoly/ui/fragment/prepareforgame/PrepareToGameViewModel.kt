package ru.welokot.monopoly.ui.fragment.prepareforgame

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.welokot.monopoly.db.AppDatabase
import ru.welokot.monopoly.db.Player
import javax.inject.Inject

class PrepareToGameViewModel @Inject constructor( val appDatabase: AppDatabase ) : ViewModel(), SwapAndDelete {

    private var playersList: MutableList<Player> = mutableListOf()
    var playersListLiveData: MutableLiveData<MutableList<Player>> = MutableLiveData()

    fun addPlayer(newPlayer: Player) {
        playersList.add(newPlayer)
        playersListLiveData.postValue(playersList)
        Log.d("TAG", playersList.toString())
    }

    override fun swapPlayer(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                playersList[i] = playersList.set(i+1, playersList[i])
            }
        } else {
            for (i in fromPosition..toPosition + 1) {
                playersList[i] = playersList.set(i-1, playersList[i])
            }
        }
        playersListLiveData.postValue(playersList)
        Log.d("TAG", playersList.toString())
    }

    override fun deletePlayer(position: Int){
        playersList.removeAt(position)
        playersListLiveData.postValue(playersList)
        Log.d("TAG", playersList.toString())
    }
}

package ru.welokot.monopoly.ui.fragment.previousgames

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.welokot.monopoly.db.AppDatabase
import ru.welokot.monopoly.db.entity.gameSession.GameSessionEntity
import java.text.FieldPosition
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class PreviousGamesViewModel @Inject constructor(
    private val appDatabase: AppDatabase
) : ViewModel(), CoroutineScope {

    private val job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private var previousGamesList = mutableListOf<GameSessionEntity>()

    var previousGamesListLiveData: MutableLiveData<List<GameSessionEntity>> = MutableLiveData()

    fun deletePreviousGame(position: Int) = launch (Dispatchers.IO) {
        appDatabase.gameSessionDao().delete(previousGamesList[position])
        previousGamesList.removeAt(position)
        previousGamesListLiveData.postValue(previousGamesList)
    }

    fun loadPreviousGames() = launch (Dispatchers.IO) {
        previousGamesList.addAll(appDatabase.gameSessionDao().getAll())
        previousGamesListLiveData.postValue(previousGamesList)
    }
}
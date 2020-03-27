package ru.welokot.monopoly.ui.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainActivityViewModel @Inject constructor() : ViewModel(), CoroutineScope {

    private val job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    val timerRunOutLivaData: MutableLiveData<Boolean> = MutableLiveData()

    fun startTimer() = launch(Dispatchers.IO) {
        delay(2000)
        timerRunOutLivaData.postValue(true)
    }
}
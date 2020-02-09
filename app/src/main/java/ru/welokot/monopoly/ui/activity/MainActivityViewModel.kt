package ru.welokot.monopoly.ui.activity

import androidx.lifecycle.ViewModel
import ru.welokot.monopoly.db.AppDatabase
import javax.inject.Inject

class MainActivityViewModel
@Inject constructor(
    val appDatabase: AppDatabase
) : ViewModel()
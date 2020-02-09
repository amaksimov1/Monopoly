package ru.welokot.monopoly.di.main

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.welokot.monopoly.di.ViewModelKey
import ru.welokot.monopoly.ui.activity.MainActivityViewModel

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindAuthViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

}
package ru.welokot.monopoly.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.welokot.monopoly.viewmodels.ViewModelProviderFactory

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

}
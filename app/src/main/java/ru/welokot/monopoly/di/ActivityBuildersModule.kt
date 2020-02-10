package ru.welokot.monopoly.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.welokot.monopoly.di.main.MainFragmentBuildersModule
import ru.welokot.monopoly.di.main.MainViewModelsModule
import ru.welokot.monopoly.ui.activity.MainActivity
import ru.welokot.monopoly.ui.activity.MainActivityViewModel

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            MainViewModelsModule::class,
            MainFragmentBuildersModule::class
        ]
    )
    abstract fun ContributeMainActivity(): MainActivity
}
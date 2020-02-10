package ru.welokot.monopoly.di.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.welokot.monopoly.ui.fragment.gameboardfragment.GameBoardFragment
import ru.welokot.monopoly.ui.fragment.prepareforgame.PrepareToGameFragment

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributePrepareToGameFragment(): PrepareToGameFragment

    @ContributesAndroidInjector
    abstract fun contributeGameBoardFragment(): GameBoardFragment
}
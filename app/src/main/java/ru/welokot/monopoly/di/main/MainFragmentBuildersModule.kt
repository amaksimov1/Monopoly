package ru.welokot.monopoly.di.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.welokot.monopoly.ui.fragment.gameboard.GameBoardFragment
import ru.welokot.monopoly.ui.fragment.prepareforgame.PrepareToGameFragment
import ru.welokot.monopoly.ui.fragment.previousgames.PreviousGamesFragment

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributePrepareToGameFragment(): PrepareToGameFragment

    @ContributesAndroidInjector
    abstract fun contributeGameBoardFragment(): GameBoardFragment

    @ContributesAndroidInjector
    abstract fun contributePreviousGamesFragment(): PreviousGamesFragment
}
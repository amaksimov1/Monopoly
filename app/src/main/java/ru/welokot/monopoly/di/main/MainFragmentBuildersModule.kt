package ru.welokot.monopoly.di.main

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.welokot.monopoly.ui.fragment.prepareforgame.PrepareToGameFragment
import ru.welokot.monopoly.ui.fragment.start.StartFragment

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeStartFragment(): StartFragment

    @ContributesAndroidInjector
    abstract fun contributePrepareToGameFragment(): PrepareToGameFragment
}
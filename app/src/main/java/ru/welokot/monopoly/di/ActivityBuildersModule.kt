package ru.welokot.monopoly.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.welokot.monopoly.ui.activity.MainActivity

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector
    abstract fun ContributeMainActivity(): MainActivity
}
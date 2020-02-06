package ru.welokot.monopoly

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.welokot.monopoly.di.DaggerAppComponent

class BaseApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }
}
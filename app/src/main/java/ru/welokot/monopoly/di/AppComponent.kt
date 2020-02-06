package ru.welokot.monopoly.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ru.welokot.monopoly.BaseApplication

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuildermodule::class,
        ViewmodelsModule::class
    ]
)

interface AppComponent: AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        a
    }
}
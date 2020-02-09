package ru.welokot.monopoly.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.welokot.monopoly.BaseApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class

    ]
)
interface AppComponent: AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}
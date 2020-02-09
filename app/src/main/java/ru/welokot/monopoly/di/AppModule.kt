package ru.welokot.monopoly.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.welokot.monopoly.db.AppDatabase
import ru.welokot.monopoly.db.AppDatabase.Companion.DATABASE_NAME
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(application: Application) =
        Room.databaseBuilder(
            application,
            AppDatabase::class.java, DATABASE_NAME
        ).build()
}
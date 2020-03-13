package ru.welokot.monopoly.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.welokot.monopoly.db.dao.PlayersDao
import ru.welokot.monopoly.db.entity.PlayerEntity

@Database(entities = [PlayerEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): PlayersDao

    companion object {
        const val DATABASE_NAME = "GamesSessionInformation"
    }
}
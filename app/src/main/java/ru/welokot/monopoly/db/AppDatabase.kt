package ru.welokot.monopoly.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Player::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): PlayerDao

    companion object {
        const val DATABASE_NAME = "PlayersInformation"
    }
}
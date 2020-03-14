package ru.welokot.monopoly.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.welokot.monopoly.db.dao.GameSessionDao
import ru.welokot.monopoly.db.entity.GameMoveEntity
import ru.welokot.monopoly.db.entity.GameSessionEntity
import ru.welokot.monopoly.db.entity.PlayerEntity

@Database (
    entities = [
        GameSessionEntity::class,
        GameMoveEntity::class,
        PlayerEntity::class
    ],
    version = 2
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun gameSessionDao(): GameSessionDao

    companion object {
        const val DATABASE_NAME = "GamesSessionInformation"
    }
}
package ru.welokot.monopoly.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.welokot.monopoly.db.dao.GameSessionDao
import ru.welokot.monopoly.db.entity.gameMove.GameMoveEntity
import ru.welokot.monopoly.db.entity.gameSession.GameSessionEntity
import ru.welokot.monopoly.db.entity.player.PlayerEntity

@Database (
    entities = [
        GameSessionEntity::class
    ],
    version = 2
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun gameSessionDao(): GameSessionDao

    companion object {
        const val DATABASE_NAME = "GamesSessionInformation"
    }
}
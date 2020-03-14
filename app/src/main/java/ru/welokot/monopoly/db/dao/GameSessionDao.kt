package ru.welokot.monopoly.db.dao

import androidx.room.*
import ru.welokot.monopoly.db.entity.gameSession.GameSessionEntity

@Dao
interface GameSessionDao {

    @Query("SELECT * FROM gameSession")
    fun getAll(): List<GameSessionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gameSession: GameSessionEntity): Long

    @Update
    fun update(gameSession: GameSessionEntity)

    @Delete
    fun delete(gameSession: GameSessionEntity)
}
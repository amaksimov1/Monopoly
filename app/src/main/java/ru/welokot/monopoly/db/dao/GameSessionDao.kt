package ru.welokot.monopoly.db.dao

import androidx.room.*
import ru.welokot.monopoly.db.entity.GameSessionEntity

@Dao
//@TypeConverter
interface GameSessionDao {

    @Query("SELECT * FROM gameSession")
    fun getAll(): List<GameSessionEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(gameSession: GameSessionEntity)

    @Update
    fun update(gameSession: GameSessionEntity)

    @Delete
    fun delete(gameSession: GameSessionEntity)
}
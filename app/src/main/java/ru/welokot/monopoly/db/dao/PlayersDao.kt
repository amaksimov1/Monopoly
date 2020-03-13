package ru.welokot.monopoly.db.dao

import androidx.room.*
import ru.welokot.monopoly.db.entity.PlayerEntity

@Dao
interface PlayersDao {
    @Query("SELECT * FROM players")
    fun getAll(): List<PlayerEntity>

    @Insert
    fun insertAll(vararg players: PlayerEntity)

    @Update
    fun updateAll(vararg players: PlayerEntity)

}
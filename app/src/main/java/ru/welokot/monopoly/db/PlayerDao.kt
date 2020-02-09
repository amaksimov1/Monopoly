package ru.welokot.monopoly.db

import androidx.room.*

@Dao
interface PlayerDao {
    @Query("SELECT * FROM players")
    fun getAll(): List<Player>

    @Insert
    fun insertAll(vararg players: Player)

    @Update
    fun updateAll(vararg players: Player)

}
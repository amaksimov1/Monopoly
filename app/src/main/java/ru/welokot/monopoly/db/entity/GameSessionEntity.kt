package ru.welokot.monopoly.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "gameSession")
class GameSessionEntity (
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val lastRun: Long = 0,
    val playersList: List<PlayerEntity> = mutableListOf(),
    val gameMovesList: List<GameMoveEntity> = mutableListOf()
) : Serializable
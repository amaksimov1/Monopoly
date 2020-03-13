package ru.welokot.monopoly.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class GameSessionEntity (
    @PrimaryKey
    val data : Long,
    val playersList: List<PlayerEntity>,
    val gameMovesList: List<GameMoveEntity>
) : Serializable
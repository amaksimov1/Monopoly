package ru.welokot.monopoly.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "gameMove")
class GameMoveEntity (
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val transferMoneyTo: MutableSet<Int> = mutableSetOf(),
    val transferMoneyFrom: MutableSet<Int> = mutableSetOf(),
    val transferAmount: String = "0",
    val isThisCancellation: Boolean = false,
    val numberOfCanceledMove: Int = 0
) : Serializable
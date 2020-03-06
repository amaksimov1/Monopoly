package ru.welokot.monopoly.db

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "players")
data class Player(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "cash")
    var capital: Double,

    @ColumnInfo(name = "icon")
    val icon: Int = 0
): Serializable
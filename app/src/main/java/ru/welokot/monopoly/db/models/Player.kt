package ru.welokot.monopoly.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "players")
class Player(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "user_id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "cash") val cash: Double
): Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (id != other.id) return false
        if (name != other.name) return false
        if (cash != other.cash) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + cash.hashCode()
        return result
    }

    override fun toString(): String {
        return "Player(id=$id, name='$name', cash=$cash)"
    }
}
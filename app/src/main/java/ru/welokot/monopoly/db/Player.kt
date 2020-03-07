package ru.welokot.monopoly.db

import androidx.room.*
import java.io.Serializable
import kotlin.math.abs
import kotlin.math.roundToInt

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

): Serializable {

    fun getFormattedCapital() : String {
        var str = "$ "
        str += capital.toInt()
        str += " - "
        val decimal = capital - capital.toInt()
        str += if (abs(decimal) < 0.001) " 000" else (decimal*1000).roundToInt()
        return str
    }
}
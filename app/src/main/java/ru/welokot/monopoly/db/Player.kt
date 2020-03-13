package ru.welokot.monopoly.db

import androidx.room.*
import ru.welokot.monopoly.models.TypeCapital
import java.io.Serializable
import kotlin.math.abs
import kotlin.math.roundToInt

@Entity(tableName = "players")
data class Player(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String = "name",

    @ColumnInfo(name = "capital")
    private var capital: Int = 0,

    @ColumnInfo(name = "icon")
    var icon: Int = 0

): Serializable {

    fun getFormattedCapital() : String {
        var str = "$ "
        str += capital / 1000
        str += " - "
        str += when {
            capital < 10 -> "00$capital"
            capital < 100 -> "0$capital"
            else -> capital % 1000
        }
        return str
    }

    fun setCapital(capital: String, typeCapital: TypeCapital) {
        this.capital = when (typeCapital) {
            TypeCapital.K -> capital.toIntOrNull() ?: 0
            else -> (capital.toIntOrNull() ?: 0) * 1000
        }
    }

    fun plusCapital(capital: String, typeCapital: TypeCapital) {
        this.capital += when (typeCapital) {
            TypeCapital.K -> capital.toIntOrNull() ?: 0
            else -> (capital.toIntOrNull() ?: 0) * 1000
        }
    }

    fun minusCapital(capital: String, typeCapital: TypeCapital) {
        this.capital -= when (typeCapital) {
            TypeCapital.K -> capital.toIntOrNull() ?: 0
            else -> (capital.toIntOrNull() ?: 0) * 1000
        }
    }

    fun getCapital() = capital
}
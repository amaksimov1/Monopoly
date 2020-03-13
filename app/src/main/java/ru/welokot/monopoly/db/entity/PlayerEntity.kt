package ru.welokot.monopoly.db.entity

import androidx.room.*
import ru.welokot.monopoly.models.TypeCapital
import java.io.Serializable

@Entity(tableName = "players")
data class PlayerEntity(

    //TODO (Добавление уникальных id)

    @PrimaryKey
    var id: Int = 0,
    var name: String = "name",
    private var capital: Int = 0,
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
package ru.welokot.monopoly.db.entity.player

import androidx.room.*
import ru.welokot.monopoly.models.TypeCapital
import java.io.Serializable

data class PlayerEntity(
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
            (capital % 1000) == 0 -> "000"
            (capital % 1000) < 10 -> "00${capital % 1000}"
            (capital % 1000) < 100 -> "0${capital % 1000}"
            else -> capital % 1000
        }
        return str
    }

    fun setCapital(capital: String, typeCapital: TypeCapital) {
        this.capital = when (typeCapital) {
            TypeCapital.K -> ((capital.toFloatOrNull() ?: 0.0) as Float).toInt()
            else -> ((capital.toFloatOrNull() ?: 0.0) as Float * 1000).toInt()
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
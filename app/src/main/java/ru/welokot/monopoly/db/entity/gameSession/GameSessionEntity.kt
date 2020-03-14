package ru.welokot.monopoly.db.entity.gameSession

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.welokot.monopoly.db.entity.gameMove.GameMoveEntity
import ru.welokot.monopoly.db.entity.player.PlayerEntity
import ru.welokot.monopoly.models.TypeCapital
import java.io.Serializable

@Entity(tableName = "gameSession")
@TypeConverters(GameSessionConverter::class)
class GameSessionEntity (
    @PrimaryKey (autoGenerate = true)
    var id: Int = 0,
    var lastRun: Long = 0,
    val playersList: List<PlayerEntity> = listOf(),
    val gameMovesList: MutableList<GameMoveEntity> = mutableListOf()
) : Serializable {

    fun getNewMove(): GameMoveEntity {
        val newMove = GameMoveEntity()
        newMove.id = gameMovesList.size
        return newMove
    }

    fun saveGameMove(gameMove: GameMoveEntity, transactionAmount: String, typeCapital: TypeCapital) {
        gameMove.transferMoneyTo.forEach {
            for (i in 0 until gameMove.transferMoneyFrom.size) {
                playersList[it].plusCapital(transactionAmount, typeCapital)
            }
        }

        gameMove.transferMoneyFrom.forEach {
            for (i in 0 until gameMove.transferMoneyTo.size) {
                playersList[it].minusCapital(transactionAmount, typeCapital)
            }
        }
        gameMovesList.add(gameMove)
    }
}

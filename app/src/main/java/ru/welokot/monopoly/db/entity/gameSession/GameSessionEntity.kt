package ru.welokot.monopoly.db.entity.gameSession

import androidx.room.Entity
import androidx.room.Ignore
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
    private var lastPlayerId: Int = 0,
    private val playersList: MutableList<PlayerEntity> = mutableListOf(),
    val gameMovesList: MutableList<GameMoveEntity> = mutableListOf()
) : Serializable {

    @Ignore
    private var currentGameMove: GameMoveEntity? = null

    fun getCurrentGameMove(): GameMoveEntity {
        if (currentGameMove == null) {
            currentGameMove = GameMoveEntity()
            currentGameMove!!.id = gameMovesList.size
        }
        return currentGameMove!!
    }

    fun applyCurrentGameMove(transactionAmount: String, typeCapital: TypeCapital) {
        currentGameMove?.let {
            it.transferMoneyTo.forEach {id ->
                addCapitalToPlayerWithId(id, transactionAmount, typeCapital)
            }

            it.transferMoneyFrom.forEach {id ->
                takeAwayCapitalFromPlayerWithId(id, transactionAmount, typeCapital)
            }
            it.transferAmount = transactionAmount
            it.transferTypeCapital = typeCapital
            gameMovesList.add(it)
        }

        currentGameMove = null
    }

    private fun addCapitalToPlayerWithId(id: Int, transactionAmount: String, typeCapital: TypeCapital) {
        playersList.forEach {
            if (it.id == id) {
                val multiplier = currentGameMove!!.transferMoneyFrom.size
                for (i in 0 until multiplier) {
                    it.plusCapital(transactionAmount, typeCapital)
                }
            }
        }
    }

    private fun takeAwayCapitalFromPlayerWithId(id: Int, transactionAmount: String, typeCapital: TypeCapital) {
        playersList.forEach {
            if (it.id == id) {
                val multiplier = currentGameMove!!.transferMoneyTo.size
                for (i in 0 until multiplier) {
                    it.minusCapital(transactionAmount, typeCapital)
                }
            }
        }
    }

    fun getLastPlayerId() = lastPlayerId

    fun addBank() {
        playersList.add(PlayerEntity(lastPlayerId, "Банк", 10000, 0, true))
    }

    fun addPlayer(playerEntity: PlayerEntity) {
        playerEntity.id = lastPlayerId++
        playersList.add(playerEntity)
    }

    fun removePlayerById(id: Int): Boolean {
        var position = 0
        playersList.forEach {
            if (it.id == id) {
                playersList.removeAt(position)
                return true
            }
            position++
        }
        return false
    }

    fun removePlayerByPosition(position: Int): Boolean {
        if (position < playersList.size) {
            playersList.removeAt(position)
            return true
        } else return false
    }

    fun getPlayersList() = playersList

    fun getPlayerById(id: Int): PlayerEntity? {
        playersList.forEach {
            if (it.id == id) {
                return it
            }
        }
        return null
    }

    fun getPlayerByPosition(position: Int): PlayerEntity? {
        return if (position < playersList.size) {
            playersList[position]
        } else null
    }
}

package ru.welokot.monopoly.ui.fragment.gamemoves

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.game_move_item.view.*
import kotlinx.android.synthetic.main.previous_game_item.view.tv_id
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.entity.gameMove.GameMoveEntity
import ru.welokot.monopoly.db.entity.gameMove.TypeTransaction
import ru.welokot.monopoly.db.entity.gameSession.GameSessionEntity

class GameMovesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var gameSession: GameSessionEntity
    private var gameMovesList = listOf<GameMoveEntity>()

    fun setGameMoves(_gameSession: GameSessionEntity) {
        gameSession = _gameSession
        gameMovesList = gameSession.gameMovesList.reversed()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_move_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = gameMovesList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.tv_id.text = "Ход: ${gameMovesList[position].id + 1}"
        holder.itemView.tv_from_players.text = "${getPlayerNames(position, TypeTransaction.FROM)}"
        holder.itemView.tv_to_players.text = "${getPlayerNames(position, TypeTransaction.TO)}"
        holder.itemView.tv_transfer_amount.text = gameMovesList[position].getFormattedTransferAmount()
        holder.itemView.tv_cancel_info.text = if (gameMovesList[position].isCancellation) {
            "Отмена хода ${gameMovesList[position].CanceledMoveId + 1}"
        } else {
            ""
        }
    }

    private fun getPlayerNames(position: Int, type: TypeTransaction): String {
        var names = ""
        when(type) {
            TypeTransaction.FROM -> {
                gameMovesList[position].transferMoneyFrom.forEach {
                    names += "${gameSession.getPlayerById(it)?.name} "
                }
            }
            TypeTransaction.TO -> {
                gameMovesList[position].transferMoneyTo.forEach {
                    names += "${gameSession.getPlayerById(it)?.name} "
                }
            }
        }
        return names
    }
    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
package ru.welokot.monopoly.ui.fragment.gamemoves

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.previous_game_item.view.*
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.entity.gameMove.GameMoveEntity
import ru.welokot.monopoly.db.entity.gameSession.GameSessionEntity

class GameMovesAdapter(
    private val context: Context,
    private val onGameMoveClickListener: OnGameMoveClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var gameMovesList = mutableListOf<GameMoveEntity>()

    fun setGameMoves(gameMoves: List<GameMoveEntity>) {
        gameMovesList.addAll(gameMoves.reversed())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.previous_game_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = gameMovesList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.tv_id.text = "ID: ${gameMovesList[position].id}"
        holder.itemView.tv_date.text = "${gameMovesList[position].transferMoneyTo}"
        holder.itemView.tv_players_list.text = "${gameMovesList[position].transferMoneyFrom} ${gameMovesList[position].transferAmount}"
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

interface OnGameMoveClickListener {
    fun onGameMoveClick(position: Int)
}
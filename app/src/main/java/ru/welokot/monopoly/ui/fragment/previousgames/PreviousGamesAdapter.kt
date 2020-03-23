package ru.welokot.monopoly.ui.fragment.previousgames

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.previous_game_item.view.*
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.entity.gameSession.GameSessionEntity

class PreviousGamesAdapter(
    private val context: Context,
    private val onPreviousGamesClickListener: OnPreviousGamesClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var previousGamesList = listOf<GameSessionEntity>()

    fun setPreviousGames(previousGames: List<GameSessionEntity>) {
        previousGamesList = previousGames
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.previous_game_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = previousGamesList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.tv_id.text = "ID: ${previousGamesList[position].id}"
        holder.itemView.tv_date.text = "Дата: ${previousGamesList[position].lastRun}"

        val playersNames = getPlayersNames(position)
        holder.itemView.tv_players_list.text = playersNames

        holder.itemView.setOnClickListener {
            onPreviousGamesClickListener.onPreviousGamesClick(previousGamesList[position])
        }
    }

    private fun getPlayersNames(position: Int): String {
        var playersNames = ""
        previousGamesList[position].getPlayersList().forEach {
            if (!it.isBank) playersNames += "${it.name} "
        }
        return playersNames
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

interface OnPreviousGamesClickListener {
    fun onPreviousGamesClick(gameSession: GameSessionEntity)
}
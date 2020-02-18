package ru.welokot.monopoly.ui.fragment.gameboard

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_swipe.view.*
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.Player

class GameBoardAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var playersList = mutableListOf<Player>()

    fun updatePlayersList(newPlayers: MutableList<Player>) {
        playersList = newPlayers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_swipe, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = playersList.size

    @SuppressLint("Recycle")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.tvName.text = playersList[position].name
        holder.itemView.tvCapital.text = playersList[position].capital.toString()
        holder.itemView.ivImage.setImageDrawable(context.resources.obtainTypedArray(R.array.player_icon).getDrawable(playersList[position].icon))
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

package ru.welokot.monopoly.ui.fragment.prepareforgame

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.player_item.view.*
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.entity.player.PlayerEntity

class PrepareToGameAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var playersList = mutableListOf<PlayerEntity>()

    fun setPlayers(newPlayers: MutableList<PlayerEntity>) {
        playersList = newPlayers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = playersList.size

    @SuppressLint("Recycle")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.tv_name.text = playersList[position].name
        holder.itemView.tv_capital.text = playersList[position].getFormattedCapital()
        holder.itemView.iv_image.setImageDrawable(context.resources.obtainTypedArray(R.array.player_icon).getDrawable(playersList[position].icon))
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}


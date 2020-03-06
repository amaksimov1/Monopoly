package ru.welokot.monopoly.ui.fragment.prepareforgame

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_swipe.view.*
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.Player
import kotlin.math.roundToInt
import kotlin.math.roundToLong


class PrepareToGameAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
        holder.itemView.tvCapital.text = parse(playersList[position].capital)
        holder.itemView.ivImage.setImageDrawable(context.resources.obtainTypedArray(R.array.player_icon).getDrawable(playersList[position].icon))
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private fun parse(number: Double) : String {
        var str = "$ "
        str += number.toInt()
        str += " - "
        var decimal = number - number.toInt()
        decimal = if (decimal == 0.0) decimal+1000 else decimal*1000
        str += if (decimal>=1000) " 000" else  decimal.roundToInt()
        return str
    }
}


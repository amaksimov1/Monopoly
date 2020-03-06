package ru.welokot.monopoly.ui.fragment.gameboard

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_swipe.view.*
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.Player
import kotlin.math.roundToInt


class GameBoardAdapter(

    private val viewModel: GameBoardViewModel,
    private val context: Context

) : RecyclerView.Adapter<GameBoardAdapter.Holder>() {

    private var playersList = mutableListOf<Player>()

    fun updatePlayersList(newPlayers: MutableList<Player>) {
        playersList = newPlayers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_swipe, parent, false)
        return Holder(view)
    }

    override fun getItemCount() = playersList.size

    @SuppressLint("Recycle", "ResourceAsColor")
    override fun onBindViewHolder(holder: Holder, position: Int) {

        when (viewModel.getTypeTransaction(position)) {
            TypeTransaction.TO -> setGreenIcon(holder)
            TypeTransaction.FROM -> setRedIcon(holder)
            else -> setGreyIcon(holder)
        }

        holder.setIsRecyclable(false)

        holder.itemView.apply {
            tvName.text = playersList[position].name
            tvCapital.text = parse(playersList[position].capital)
            ivImage.setImageDrawable(
                context.resources.obtainTypedArray(R.array.player_icon).getDrawable(
                    playersList[position].icon
                )
            )
        }

        holder.itemView.setOnClickListener {
            when {
                viewModel.getTypeTransaction(position) == null -> {
                    setRedIcon(holder)
                    viewModel.addTransaction(position, TypeTransaction.FROM)
                }

                viewModel.getTypeTransaction(position) == TypeTransaction.FROM -> {
                    setGreenIcon(holder)
                    viewModel.deleteTransaction(position)
                    viewModel.addTransaction(position, TypeTransaction.TO)
                }

                viewModel.getTypeTransaction(position) == TypeTransaction.TO -> {
                    setGreyIcon(holder)
                    viewModel.deleteTransaction(position)
                }
            }
        }
    }

    private fun setGreenIcon(holder: RecyclerView.ViewHolder) {
        holder.itemView.ivAction.setImageDrawable(context.getDrawable(R.drawable.ic_call_made_green_24dp))
        holder.itemView.ivAction.setColorFilter(ContextCompat.getColor(context, R.color.green_A700))
    }

    private fun setRedIcon(holder: RecyclerView.ViewHolder) {
        holder.itemView.ivAction.setImageDrawable(context.getDrawable(R.drawable.ic_call_received_red_24dp))
        holder.itemView.ivAction.setColorFilter(ContextCompat.getColor(context, R.color.red_A700))
    }
    private fun setGreyIcon(holder: RecyclerView.ViewHolder) {
        holder.itemView.ivAction.setImageDrawable(context.getDrawable(R.drawable.ic_fingerprint_black_24dp))
        holder.itemView.ivAction.setColorFilter(ContextCompat.getColor(context, R.color.grey_800))
    }

    private fun parse(number: Double) : String {
        var str = "$ "
        str += number.toInt()
        str += " - "
        var decimal = number - number.toInt()
        decimal = if (decimal == 0.0) decimal+1000 else decimal*1000
        str += if (decimal>=1000) " 000" else  decimal.roundToInt()
        return str
    }


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

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
            tvCapital.text = playersList[position].getFormattedCapital()
            ivImage.setImageDrawable(
                context.resources.obtainTypedArray(R.array.player_icon).getDrawable(
                    playersList[position].icon
                )
            )
        }

        holder.itemView.setOnClickListener {
            when (viewModel.getTypeTransaction(position)) {
                TypeTransaction.NOTHING -> {
                    setRedIcon(holder)
                    viewModel.addTransaction(position, TypeTransaction.FROM)
                }

                TypeTransaction.FROM -> {
                    setGreenIcon(holder)
                    viewModel.deleteTransaction(position)
                    viewModel.addTransaction(position, TypeTransaction.TO)
                }

                TypeTransaction.TO -> {
                    setGreyIcon(holder)
                    viewModel.deleteTransaction(position)
                }
            }
        }
    }

    private fun setGreenIcon(holder: RecyclerView.ViewHolder) {
        holder.itemView.ivAction.setImageDrawable(context.getDrawable(R.drawable.ic_call_received_green_24dp))
        holder.itemView.ivAction.setColorFilter(ContextCompat.getColor(context, R.color.green_A700))
    }

    private fun setRedIcon(holder: RecyclerView.ViewHolder) {
        holder.itemView.ivAction.setImageDrawable(context.getDrawable(R.drawable.ic_call_made_red_24dp))
        holder.itemView.ivAction.setColorFilter(ContextCompat.getColor(context, R.color.red_A700))
    }
    private fun setGreyIcon(holder: RecyclerView.ViewHolder) {
        holder.itemView.ivAction.setImageDrawable(context.getDrawable(R.drawable.ic_fingerprint_black_24dp))
        holder.itemView.ivAction.setColorFilter(ContextCompat.getColor(context, R.color.grey_700))
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

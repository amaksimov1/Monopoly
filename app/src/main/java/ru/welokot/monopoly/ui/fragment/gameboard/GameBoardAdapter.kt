package ru.welokot.monopoly.ui.fragment.gameboard

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.entity.gameMove.GameMoveEntity
import ru.welokot.monopoly.db.entity.gameMove.TypeTransaction
import ru.welokot.monopoly.db.entity.gameSession.GameSessionEntity
import ru.welokot.monopoly.db.entity.player.PlayerEntity

class GameBoardAdapter(
    private val context: Context,
    private val onItemClickListener: OnItemClickListener

) : RecyclerView.Adapter<GameBoardAdapter.Holder>() {

    private var gameSession = GameSessionEntity()

    fun updatePlayersList(gameSession: GameSessionEntity) {
        this.gameSession = gameSession
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return Holder(view)
    }

    override fun getItemCount() = gameSession.playersList.size

    @SuppressLint("Recycle", "ResourceAsColor")
    override fun onBindViewHolder(holder: Holder, position: Int) {

        val player = gameSession.playersList[position]
        val currentGameMove = gameSession.getCurrentGameMove()

        when (currentGameMove.getTypeTransaction(player.id)) {
            TypeTransaction.TO -> setGreenIcon(holder)
            TypeTransaction.FROM -> setRedIcon(holder)
            else -> setGreyIcon(holder)
        }

        holder.setIsRecyclable(false)

        holder.itemView.apply {
            tvName.text = player.name
            tvCapital.text = player.getFormattedCapital()
            ivImage.setImageDrawable(
                context.resources.obtainTypedArray(R.array.player_icon).getDrawable(
                    player.icon
                )
            )
        }

        holder.itemView.setOnClickListener {
            onItemClickListener.changeTypeTransaction(gameSession, player.id)
        }
    }

    private fun setGreenIcon(holder: RecyclerView.ViewHolder) {
        holder.itemView.ivAction.setImageDrawable(context.getDrawable(R.drawable.ic_call_received_24dp))
        holder.itemView.ivAction.setColorFilter(ContextCompat.getColor(context, R.color.green_A700))
    }

    private fun setRedIcon(holder: RecyclerView.ViewHolder) {
        holder.itemView.ivAction.setImageDrawable(context.getDrawable(R.drawable.ic_call_made_24dp))
        holder.itemView.ivAction.setColorFilter(ContextCompat.getColor(context, R.color.red_A700))
    }

    private fun setGreyIcon(holder: RecyclerView.ViewHolder) {
        holder.itemView.ivAction.setImageDrawable(context.getDrawable(R.drawable.ic_fingerprint_black_24dp))
        holder.itemView.ivAction.setColorFilter(ContextCompat.getColor(context, R.color.grey_700))
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

interface OnItemClickListener {
    fun changeTypeTransaction(gameSession: GameSessionEntity, playerId: Int)
}

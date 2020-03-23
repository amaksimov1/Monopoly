package ru.welokot.monopoly.ui.fragment.gameboard

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.player_item.view.*
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.entity.gameMove.TypeTransaction
import ru.welokot.monopoly.db.entity.gameSession.GameSessionEntity
import ru.welokot.monopoly.db.entity.player.PlayerEntity

class GameBoardAdapter(
    private val context: Context,
    private val onPlayerClickListener: OnPlayerClickListener

) : RecyclerView.Adapter<GameBoardAdapter.Holder>() {

    private var gameSession = GameSessionEntity()

    fun updatePlayersList(gameSession: GameSessionEntity) {
        this.gameSession = gameSession
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount() = gameSession.getPlayersList().size

    @SuppressLint("Recycle", "ResourceAsColor")
    override fun onBindViewHolder(holder: Holder, position: Int) {

        val player = gameSession.getPlayerByPosition(position) ?: PlayerEntity()
        val currentGameMove = gameSession.getCurrentGameMove()

        when (currentGameMove.getTypeTransaction(player.id)) {
            TypeTransaction.TO -> setGreenIcon(holder)
            TypeTransaction.FROM -> setRedIcon(holder)
            else -> setGreyIcon(holder)
        }

        holder.setIsRecyclable(false)

        holder.itemView.apply {
            tv_name.text = player.name
            tv_capital.text = player.getFormattedCapital()
            iv_image.setImageDrawable(
                context.resources.obtainTypedArray(R.array.player_icon).getDrawable(
                    player.icon
                )
            )
        }

        holder.itemView.setOnClickListener {
            onPlayerClickListener.changeTypeTransaction(gameSession, player.id)
        }
    }

    private fun setGreenIcon(holder: RecyclerView.ViewHolder) {
        holder.itemView.iv_action.setImageDrawable(context.getDrawable(R.drawable.ic_call_received_24dp))
        holder.itemView.iv_action.setColorFilter(ContextCompat.getColor(context, R.color.green_A700))
    }

    private fun setRedIcon(holder: RecyclerView.ViewHolder) {
        holder.itemView.iv_action.setImageDrawable(context.getDrawable(R.drawable.ic_call_made_24dp))
        holder.itemView.iv_action.setColorFilter(ContextCompat.getColor(context, R.color.red_A700))
    }

    private fun setGreyIcon(holder: RecyclerView.ViewHolder) {
        holder.itemView.iv_action.setImageDrawable(context.getDrawable(R.drawable.ic_fingerprint_black_24dp))
        holder.itemView.iv_action.setColorFilter(ContextCompat.getColor(context, R.color.grey_700))
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

interface OnPlayerClickListener {
    fun changeTypeTransaction(gameSession: GameSessionEntity, playerId: Int)
}

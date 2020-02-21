package ru.welokot.monopoly.ui.fragment.gameboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SimpleSwipeListener
import com.daimajia.swipe.SwipeLayout
import kotlinx.android.synthetic.main.item_swipe.view.*
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.Player


class GameBoardAdapter(private val viewModel: GameBoardViewModel) : RecyclerView.Adapter<GameBoardAdapter.Holder>() {

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

        holder.setIsRecyclable(false)

        holder.itemView.apply {
            tvName.text = playersList[position].name
            tvCapital.text = playersList[position].capital.toString()
            ivImage.setImageDrawable(
                context.resources.obtainTypedArray(R.array.player_icon).getDrawable(
                    playersList[position].icon
                )
            )
        }

        holder.itemView.swipeItem.apply {
            showMode = SwipeLayout.ShowMode.PullOut
            addDrag(
                SwipeLayout.DragEdge.Left,
                this.findViewById(R.id.leftBottomLayout)
            )
            addDrag(
                SwipeLayout.DragEdge.Right,
                this.findViewById(R.id.rightBottomLayout)
            )
            addSwipeListener(object: SimpleSwipeListener() {
                override fun onOpen(layout: SwipeLayout?) {
                    if (layout?.dragEdge == SwipeLayout.DragEdge.Left) {
                        viewModel.addTransaction(position, TypeTransaction.TO)
                    } else if (layout?.dragEdge == SwipeLayout.DragEdge.Right) {
                        viewModel.addTransaction(position, TypeTransaction.FROM)
                    }
                }
                override fun onClose(layout: SwipeLayout?) {
                    viewModel.deleteTransaction(position)
                }
            })
        }

        when (viewModel.getTypeTransaction(position)) {
            TypeTransaction.TO -> {
                holder.itemView.swipeItem.open(SwipeLayout.DragEdge.Left)
            }
            TypeTransaction.FROM -> {
                holder.itemView.swipeItem.open(SwipeLayout.DragEdge.Right)
            }
            null -> {}
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

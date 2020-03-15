package ru.welokot.monopoly.ui.fragment.preparetogame

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class DeleteCallback(private val onSwipedListener: OnSwipedListener) : ItemTouchHelper.SimpleCallback(
    0,
    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onSwipedListener.onSwiped(viewHolder.adapterPosition)
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipeFlags = onSwipedListener.canSwiped(viewHolder.adapterPosition)
        return makeMovementFlags(0, swipeFlags)
    }
}

interface OnSwipedListener {
    fun onSwiped(position: Int)
    fun canSwiped(position: Int): Int
}
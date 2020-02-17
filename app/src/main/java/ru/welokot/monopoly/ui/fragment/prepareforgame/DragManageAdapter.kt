package ru.welokot.monopoly.ui.fragment.prepareforgame

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class DragManageAdapter(
    private val viewModel: PrepareToGameViewModel,
    dragDirs: Int,
    swipeDirs: Int
): ItemTouchHelper.SimpleCallback(
    dragDirs,
    swipeDirs
) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean { return true }

    override fun onSwiped(
        viewHolder: RecyclerView.ViewHolder,
        direction: Int
    ) {
        viewModel.deletePlayer(viewHolder.adapterPosition)
    }
}
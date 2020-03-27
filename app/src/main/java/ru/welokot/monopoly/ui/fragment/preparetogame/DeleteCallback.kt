package ru.welokot.monopoly.ui.fragment.preparetogame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.welokot.monopoly.R


class DeleteCallback(
    private val onSwipedListener: OnSwipedListener,
    private val context: Context
) : ItemTouchHelper.SimpleCallback(
    0,
    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
) {

    private var background = context.getDrawable(R.color.colorPrimary)
    private var icon = context.getDrawable(R.drawable.ic_delete_forever_24dp)

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

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        drawBackground(itemView, dX, c)
        drawIcon(itemView, dX, c)
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun drawBackground(itemView: View, dX: Float, c: Canvas) {
        if (dX < 0) { background?.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom) }
        else { background?.setBounds(itemView.left, itemView.top, itemView.right + dX.toInt(), itemView.bottom) }
        background?.draw(c)
    }

    private fun drawIcon(itemView: View, dX: Float, c: Canvas) {
        val itemHeight = itemView.bottom - itemView.top
        icon?.let {
            val intrinsicWidth = it.intrinsicWidth
            val intrinsicHeight = it.intrinsicWidth
            val iconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
            val iconBottom = iconTop + intrinsicHeight
            val margin = itemView.top - iconTop
            val iconLeft: Int
            val iconRight: Int
            if (dX < 0) {
                iconLeft = itemView.right - intrinsicWidth + margin
                iconRight = itemView.right + margin
            } else {
                iconLeft = itemView.left - margin
                iconRight = itemView.left + intrinsicWidth - margin
            }
            it.setBounds(iconLeft, iconTop, iconRight, iconBottom)
        }
        icon?.draw(c)
    }

    interface OnSwipedListener {
        fun onSwiped(position: Int)
        fun canSwiped(position: Int): Int
    }
}

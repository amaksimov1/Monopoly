package ru.welokot.monopoly.ui.fragment.prepareforgame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.welokot.monopoly.R


class PrepareToGameAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list = mutableListOf<Int>()

    fun addPlayer() {
        list.add(0)
        notifyItemInserted(list.size-1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_swipe, parent, false)
        return Holder(v)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) { }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
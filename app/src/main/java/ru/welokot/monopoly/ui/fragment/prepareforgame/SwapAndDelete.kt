package ru.welokot.monopoly.ui.fragment.prepareforgame

interface SwapAndDelete {
    fun swapPlayer(fromPosition: Int, toPosition: Int)
    fun deletePlayer(position: Int)
}
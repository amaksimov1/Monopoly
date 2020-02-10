package ru.welokot.monopoly.ui.fragment.gameboardfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import ru.welokot.monopoly.R

class GameBoardFragment: DaggerFragment() {

    companion object {
        fun newInstance() = GameBoardFragment()
    }

    private lateinit var viewModel: GameBoardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.prepare_to_game_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}
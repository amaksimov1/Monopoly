package ru.welokot.monopoly.ui.fragment.prepareforgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import ru.welokot.monopoly.R

class PrepareToGameFragment : DaggerFragment() {

    companion object {
        fun newInstance() = PrepareToGameFragment()
    }

    private lateinit var viewModel: PrepareToGameViewModel

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



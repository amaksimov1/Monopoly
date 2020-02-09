package ru.welokot.monopoly.ui.fragment.prepareforgame

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ru.welokot.monopoly.R

class PrepareToGameFragment : Fragment() {

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
        viewModel = ViewModelProviders.of(this).get(PrepareToGameViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

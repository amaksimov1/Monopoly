package ru.welokot.monopoly.ui.fragment.gameboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.game_board_fragment.*
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.Player
import java.io.Serializable
import javax.inject.Inject

class GameBoardFragment: DaggerFragment() {

    companion object {
        private const val CODE_KEY = "GameBoardFragment"
        fun newInstance(players: MutableList<Player>): GameBoardFragment {
            val fragment = GameBoardFragment()
            val bundle = Bundle()
            bundle.putSerializable(CODE_KEY, players as Serializable)
            fragment.arguments = bundle
            return fragment
        }
    }

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    private lateinit var viewModel: GameBoardViewModel
    private lateinit var adapter: GameBoardAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this, providerFactory).get(GameBoardViewModel::class.java)

        return inflater.inflate(R.layout.game_board_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initListeners()
        initAdapters()
        initRecyclerView()
        initFab()
        initObservers()

        viewModel.playersListLiveData.postValue(arguments!!.getSerializable(CODE_KEY) as MutableList<Player>)
    }

    private fun initListeners() {
        fabMoneyTransfer.setOnClickListener {
            Toast.makeText(context, "Скоро будет", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initAdapters() {
        adapter = GameBoardAdapter(context!!)
    }

    private fun initRecyclerView() {
        rvPlayersList.layoutManager = LinearLayoutManager(context)
        rvPlayersList.adapter = adapter

        val callback = DragManageAdapter(
            viewModel,
            0,
            ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)
        )
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(rvPlayersList)
    }

    private fun initFab() {
        Toast.makeText(context, "Скоро будет", Toast.LENGTH_SHORT).show()
    }

    private fun initObservers() {
        viewModel.playersListLiveData.observe(this as LifecycleOwner, Observer {
            adapter.updatePlayersList(it)
        })
    }
}
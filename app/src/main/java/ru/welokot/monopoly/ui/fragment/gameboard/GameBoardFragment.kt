package ru.welokot.monopoly.ui.fragment.gameboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.game_board_fragment.*
import kotlinx.android.synthetic.main.toolbar.view.*
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.entity.gameSession.GameSessionEntity
import ru.welokot.monopoly.models.TypeCapital
import ru.welokot.monopoly.ui.dialog.MainDialog
import javax.inject.Inject

class GameBoardFragment: DaggerFragment(), TransactionCommiter, OnPlayerClickListener {

    companion object {
        private const val CODE_KEY = "GameBoardFragment"
        fun newInstance(gameSession: GameSessionEntity): GameBoardFragment {
            val fragment = GameBoardFragment()
            val bundle = Bundle()
            bundle.putSerializable(CODE_KEY, gameSession)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setGameSession(arguments!!.getSerializable(CODE_KEY) as GameSessionEntity)

        setTitle(view)
        initListeners()
        initAdapters()
        initRecyclerView(view)
        initObservers()
    }

    private fun setTitle(view: View) {
        with(view) {
            tvTitle.text = context.getString(R.string.transfer_money)
        }
    }

    private fun initListeners() {
        fab_money_transfer.setOnClickListener {
            val addingPlayer = CommitTransaction(this@GameBoardFragment)
            val mainDialog = MainDialog(addingPlayer)
            mainDialog.show(childFragmentManager, mainDialog.TAG)
        }
    }

    private fun initAdapters() {
        val gameBoardAdapter = GameBoardAdapter(context!!, this)
        adapter = gameBoardAdapter
    }

    private fun initRecyclerView(view: View) {
        with(view) {
            rv_players_list.layoutManager = LinearLayoutManager(context)
            rv_players_list.setHasFixedSize(true)
            rv_players_list.adapter = adapter
        }
    }

    private fun initObservers() {
        viewModel.gameSessionLiveData.observe(viewLifecycleOwner, Observer {
            adapter.updatePlayersList(it)
        })
    }

    override fun commitTransaction(transactionAmount: String, typeCapital: TypeCapital) {
        viewModel.commitTransaction(transactionAmount, typeCapital)
    }

    override fun changeTypeTransaction(gameSession: GameSessionEntity, playerId: Int) {
        viewModel.changeTypeTransaction(gameSession, playerId)
    }
}
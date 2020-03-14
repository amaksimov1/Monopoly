package ru.welokot.monopoly.ui.fragment.prepareforgame

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.prepare_to_game_fragment.*
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.entity.player.PlayerEntity
import ru.welokot.monopoly.ui.Router
import ru.welokot.monopoly.ui.dialog.MainDialog
import javax.inject.Inject


class PrepareToGameFragment : DaggerFragment(), OnSwipedListener, PlayerAdder {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    private lateinit var viewModel: PrepareToGameViewModel
    private lateinit var adapter: PrepareToGameAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this, providerFactory).get(PrepareToGameViewModel::class.java)
        return inflater.inflate(R.layout.prepare_to_game_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initClicks()
        initAdapters()
        initRecyclerView()
        initListeners()
        initObservers()
    }

    private fun initClicks() {
        btn_start_game.setOnClickListener {
            viewModel.addNewGameSession()
        }

        btn_load_previous_games.setOnClickListener {
            Toast.makeText(context, "Экран загрузки предыдущих игр", Toast.LENGTH_SHORT).show()
        }

        fab_add_player.setOnClickListener {
            val addingPlayer = AddingPlayer(this, context!!)
            val mainDialog = MainDialog(addingPlayer)
            mainDialog.show(childFragmentManager, mainDialog.TAG)
        }
    }

    private fun initAdapters() {
        adapter = PrepareToGameAdapter(context!!)
    }

    private fun initRecyclerView() {
        rv_players_list.layoutManager = LinearLayoutManager(context)
        rv_players_list.adapter = this.adapter
        rv_players_list.isNestedScrollingEnabled = true

        val deleteCallback = DeleteCallback(this)
        val helper = ItemTouchHelper(deleteCallback)
        helper.attachToRecyclerView(rv_players_list)
    }

    private fun initListeners() {
        viewModel.playersListLiveData.observe(viewLifecycleOwner, Observer{
            adapter.setPlayers(it)
        })
    }

    private fun initObservers() {
        viewModel.playersListLiveData.observe(viewLifecycleOwner, Observer {
            adapter.setPlayers(it)
            btn_start_game.isEnabled = it.size >=2
        })

        viewModel.gameSessionLiveData.observe(viewLifecycleOwner, Observer {
            Router.showGameBoardFragment(childFragmentManager, it)
        })
    }

    override fun onSwiped(position: Int) {
        viewModel.deletePlayer(position)
    }

    override fun addPlayer(newPlayer: PlayerEntity) {
        viewModel.addPlayer(newPlayer)
    }
}
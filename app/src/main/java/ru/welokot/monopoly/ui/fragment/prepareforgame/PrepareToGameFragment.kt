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
import kotlinx.android.synthetic.main.toolbar.view.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(view)

        initClicks(view)
        initAdapters()
        initRecyclerView(view)
        initListeners()
        initObservers(view)

        viewModel.addBank()
    }

    private fun setTitle(view: View) {
        with(view) {
            tvTitle.text = context.getString(R.string.adding_players)
        }
    }

    private fun initClicks(view: View) {
        with(view) {
            btn_start_game.setOnClickListener {
                viewModel.addNewGameSession()
            }

            btn_load_previous_games.setOnClickListener {
                Toast.makeText(context, "Экран загрузки предыдущих игр", Toast.LENGTH_SHORT).show()
            }

            fab_add_player.setOnClickListener {
                val addingPlayer = AddingPlayer(this@PrepareToGameFragment, context!!)
                val mainDialog = MainDialog(addingPlayer)
                mainDialog.show(childFragmentManager, mainDialog.TAG)
            }
        }
    }

    private fun initAdapters() {
        adapter = PrepareToGameAdapter(context!!)
    }

    private fun initRecyclerView(view: View) {
        with(view) {
            rv_players_list.layoutManager = LinearLayoutManager(context)
            rv_players_list.adapter = this@PrepareToGameFragment.adapter
            rv_players_list.isNestedScrollingEnabled = false

            val deleteCallback = DeleteCallback(this@PrepareToGameFragment)
            val helper = ItemTouchHelper(deleteCallback)
            helper.attachToRecyclerView(rv_players_list)
        }
    }

    private fun initListeners() {
        viewModel.playersListLiveData.observe(viewLifecycleOwner, Observer{
            adapter.setPlayers(it)
        })
    }

    private fun initObservers(view: View) {
        with(view) {
            viewModel.playersListLiveData.observe(viewLifecycleOwner, Observer {
                adapter.setPlayers(it)
                btn_start_game.isEnabled = it.size >=3
            })

            viewModel.gameSessionLiveData.observe(viewLifecycleOwner, Observer {
                Router.showGameBoardFragment(activity!!.supportFragmentManager, it)
            })
        }
    }

    override fun onSwiped(position: Int) {
        viewModel.deletePlayer(position)
    }

    override fun addPlayer(newPlayer: PlayerEntity) {
        viewModel.addPlayer(newPlayer)
    }
}
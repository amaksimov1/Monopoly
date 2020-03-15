package ru.welokot.monopoly.ui.fragment.previousgames

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.prepare_to_game_fragment.*
import kotlinx.android.synthetic.main.previous_games_fragment.*
import kotlinx.android.synthetic.main.toolbar.view.*
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.entity.gameSession.GameSessionEntity
import ru.welokot.monopoly.ui.Router
import ru.welokot.monopoly.ui.fragment.prepareforgame.DeleteCallback
import ru.welokot.monopoly.ui.fragment.prepareforgame.OnSwipedListener
import javax.inject.Inject

class PreviousGamesFragment : DaggerFragment(), OnSwipedListener, OnPreviousGamesClickListener {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    private lateinit var viewModel: PreviousGamesViewModel
    private lateinit var adapter: PreviousGamesAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this, providerFactory).get(PreviousGamesViewModel::class.java)
        return inflater.inflate(R.layout.previous_games_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(view)

        initAdapters()
        initRecyclerView(view)
        initObservers()

        viewModel.loadPreviousGames()
    }

    private fun setTitle(view: View) {
        with(view) {
            tvTitle.text = context.getString(R.string.previous_games)
        }
    }

    private fun initAdapters() {
        adapter = PreviousGamesAdapter(context!!, this)
    }

    private fun initRecyclerView(view: View) {
        with(view) {
            rv_previous_games.layoutManager = LinearLayoutManager(context)
            rv_previous_games.adapter = this@PreviousGamesFragment.adapter
            rv_previous_games.isNestedScrollingEnabled = false

            val deleteCallback = DeleteCallback(this@PreviousGamesFragment)
            val helper = ItemTouchHelper(deleteCallback)
            helper.attachToRecyclerView(rv_previous_games)
        }
    }

    private fun initObservers() {
        viewModel.previousGamesListLiveData.observe(viewLifecycleOwner, Observer{
            adapter.setPreviousGames(it)
        })
    }

    override fun onSwiped(position: Int) {
        viewModel.deletePreviousGame(position)
    }

    override fun onPreviousGamesClick(gameSession: GameSessionEntity) {
        Router.showGameBoardFragment(activity!!.supportFragmentManager, gameSession)
    }
}
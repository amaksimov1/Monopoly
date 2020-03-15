package ru.welokot.monopoly.ui.fragment.previousgames

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.previous_games_fragment.*
import kotlinx.android.synthetic.main.toolbar.view.*
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.entity.gameSession.GameSessionEntity
import ru.welokot.monopoly.ui.Router
import ru.welokot.monopoly.ui.fragment.preparetogame.DeleteCallback
import ru.welokot.monopoly.ui.fragment.preparetogame.OnSwipedListener
import javax.inject.Inject

class PreviousGamesFragment : DaggerFragment(), OnSwipedListener, OnPreviousGamesClickListener {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    private lateinit var viewModel: PreviousGamesViewModel
    private lateinit var adapter: PreviousGamesAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, providerFactory).get(PreviousGamesViewModel::class.java)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapters()
        viewModel.loadPreviousGames()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.previous_games_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(view)
        initClicks(view)
        initRecyclerView(view)
        initObservers()
    }

    private fun initToolbar(view: View) {
        with(view) {
            tv_title.text = context.getString(R.string.previous_games)
            ib_back.visibility = View.VISIBLE
        }
    }

    private fun initClicks(view: View) {
        with(view) {
            ib_back.setOnClickListener {
                activity?.onBackPressed()
            }
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

    override fun canSwiped(position: Int): Int {
        return ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    }

    override fun onPreviousGamesClick(gameSession: GameSessionEntity) {
        Router.showGameBoardFragment(activity?.supportFragmentManager, gameSession)
    }
}
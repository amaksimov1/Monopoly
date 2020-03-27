package ru.welokot.monopoly.ui.fragment.gamemoves

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.game_moves_fragment.view.*
import kotlinx.android.synthetic.main.toolbar.view.*
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.entity.gameSession.GameSessionEntity
import javax.inject.Inject

class GameMovesFragment : DaggerFragment(), OnGameMoveClickListener {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    private lateinit var viewModel: GameMovesViewModel
    private lateinit var adapter: GameMovesAdapter

    companion object {
        private const val CODE_KEY = "GameBoardFragment"
        fun newInstance(gameSession: GameSessionEntity): GameMovesFragment {
            val fragment = GameMovesFragment()
            val bundle = Bundle()
            bundle.putSerializable(CODE_KEY, gameSession)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProvider(this, providerFactory).get(GameMovesViewModel::class.java)
        viewModel.setGameSession(arguments!!.getSerializable(CODE_KEY) as GameSessionEntity)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapters()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.game_moves_fragment, container, false)
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
            tv_title.text = context.getString(R.string.game_moves)
            ib_back.visibility = View.VISIBLE
            ib_undo.visibility = View.GONE
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
        adapter = GameMovesAdapter(context!!, this)
    }

    private fun initRecyclerView(view: View) {
        with(view) {
            rv_game_moves.layoutManager = LinearLayoutManager(context)
            rv_game_moves.adapter = this@GameMovesFragment.adapter
            rv_game_moves.isNestedScrollingEnabled = false
        }
    }

    private fun initObservers() {
        viewModel.gameMovesListLiveData.observe(viewLifecycleOwner, Observer{
            adapter.setGameMoves(it)
        })
    }

    override fun onGameMoveClick(position: Int) {  }
}
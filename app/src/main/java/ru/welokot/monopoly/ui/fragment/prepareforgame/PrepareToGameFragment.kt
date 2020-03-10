package ru.welokot.monopoly.ui.fragment.prepareforgame

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.dialog_main.*
import kotlinx.android.synthetic.main.prepare_to_game_fragment.*
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.Player
import ru.welokot.monopoly.ui.Router
import ru.welokot.monopoly.utils.ViewAnimation
import javax.inject.Inject
import kotlin.random.Random


class PrepareToGameFragment : DaggerFragment() {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    private lateinit var viewModel: PrepareToGameViewModel
    private lateinit var adapter: PrepareToGameAdapter

    private var rotate = false

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
            if (it.isEnabled) {
                Toast.makeText(context, "Начало игры", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Добавьте по крайней мере 2 игроков", Toast.LENGTH_SHORT).show()
            }
        }

        btn_load_previous_games.setOnClickListener {
            Toast.makeText(context, "Экран загрузки предыдущих игр", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initAdapters() {
        adapter = PrepareToGameAdapter(context!!)
    }

    private fun initRecyclerView() {
        rv_players_list.layoutManager = LinearLayoutManager(context)
        rv_players_list.adapter = this.adapter
        rv_players_list.isNestedScrollingEnabled = true
    }

    private fun initListeners() {
        viewModel.playersListLiveData.observe(viewLifecycleOwner, Observer{
            adapter.setPlayers(it)
        })
    }

    private fun initObservers() {

    }


}
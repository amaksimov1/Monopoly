package ru.welokot.monopoly.ui.fragment.prepareforgame

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.prepare_to_game_fragment.*
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.Player
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

        initListeners()
        initAdapters()
        initRecyclerView()
        initFab()
        initObservers()
    }

    private fun initListeners() {
        fabAddNewPlayer.setOnLongClickListener {
            toggleFabMode(it)
            true
        }

        fabAddNewPlayer.setOnClickListener {
            if (!rotate) {
                showDialogAddNewPlayer()
            }
            else toggleFabMode(it)
        }

        fabAddOldPlayers.setOnClickListener {
            Toast.makeText(
                activity,
                "Скоро будет",
                Toast.LENGTH_SHORT
            ).show()
        }

        fabStartGame.setOnClickListener {
            Toast.makeText(
                activity,
                "Скоро будет",
                Toast.LENGTH_SHORT
            ).show()
        }

        back_drop.setOnClickListener {
            toggleFabMode(fabAddNewPlayer)
        }
    }

    private fun initAdapters() {
        adapter = PrepareToGameAdapter(context!!)
    }

    private fun initRecyclerView() {
        rvPlayersList.layoutManager = LinearLayoutManager(context)
        rvPlayersList.adapter = adapter

        val callback = DragManageAdapter(
            viewModel,
            ItemTouchHelper.UP.or(ItemTouchHelper.DOWN),
            ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)
        )
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(rvPlayersList)
    }

    private fun initObservers() {
        viewModel.playersListLiveData.observe(viewLifecycleOwner, Observer {
            adapter.updatePlayersList(it)
        })

    }

    @SuppressLint("Recycle")
    private fun showDialogAddNewPlayer() {
        val dialog = Dialog(context!!)
        dialog.setContentView(R.layout.dialog_add_player)
        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        dialog.window!!.attributes.windowAnimations = R.style.Animation_Design_BottomSheetDialog

        dialog.findViewById<AppCompatButton>(R.id.btnAddPlayer).setOnClickListener {
            viewModel.addPlayer(
                Player(
                    name = dialog.findViewById<TextInputEditText>(R.id.tiedName).text.toString(),
                    capital = dialog.findViewById<TextInputEditText>(R.id.tiedCapital).text.toString().toDouble(),
                    icon = Random.nextInt(context!!.resources.obtainTypedArray(R.array.player_icon).length())
                )
            )
            dialog.findViewById<TextInputEditText>(R.id.tiedName).setText("")
        }

        dialog.findViewById<AppCompatButton>(R.id.btnCloseDialog).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun initFab() {
        ViewAnimation.initShowOut(clStartGame)
        ViewAnimation.initShowOut(clAddOldPlayers)
        back_drop.visibility = View.GONE
    }

    private fun toggleFabMode(v: View) {
        rotate = ViewAnimation.rotateFab(v, !rotate)
        if (rotate) {
            ViewAnimation.showIn(clStartGame)
            ViewAnimation.showIn(clAddOldPlayers)
            back_drop.visibility = View.VISIBLE
        } else {
            ViewAnimation.showOut(clStartGame)
            ViewAnimation.showOut(clAddOldPlayers)
            back_drop.visibility = View.GONE
        }
    }
}
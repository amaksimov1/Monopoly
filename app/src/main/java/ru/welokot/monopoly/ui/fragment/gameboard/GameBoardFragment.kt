package ru.welokot.monopoly.ui.fragment.gameboard

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
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
        initObservers()

        viewModel.setPlayersList(arguments!!.getSerializable(CODE_KEY) as MutableList<Player>)
    }

    private fun initListeners() {
        fabMoneyTransfer.setOnClickListener {
            showDialogCommitTransfer()
        }
    }

    private fun initAdapters() {
        val gameBoardAdapter = GameBoardAdapter(viewModel, context!!)
        adapter = gameBoardAdapter
    }

    private fun initRecyclerView() {
        rvPlayersList.layoutManager = LinearLayoutManager(context)
        rvPlayersList.setHasFixedSize(true)
        rvPlayersList.adapter = adapter
    }

    private fun initObservers() {
        viewModel.playersListLiveData.observe(this as LifecycleOwner, Observer {
            adapter.updatePlayersList(it)
        })
    }

    @SuppressLint("Recycle")
    private fun showDialogCommitTransfer() {
        val dialog = Dialog(context!!)
        //dialog.setContentView(R.layout.dialog_start_transfer_money)
        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        dialog.window!!.attributes.windowAnimations = R.style.Animation_Design_BottomSheetDialog

//        dialog.findViewById<AppCompatButton>(R.id.btnCommitTransfer).setOnClickListener {
//            val capital = dialog.findViewById<TextInputEditText>(R.id.tiedCapital).text!!
//            if (capital.isNotEmpty()) {
//                viewModel.commitTransfer(capital.toString().toFloat())
//                dialog.dismiss()
//            } else {
//                dialog.findViewById<TextInputEditText>(R.id.tiedCapital).error = "Введите капитал"
//            }
//        }

        dialog.findViewById<AppCompatButton>(R.id.btnClose).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
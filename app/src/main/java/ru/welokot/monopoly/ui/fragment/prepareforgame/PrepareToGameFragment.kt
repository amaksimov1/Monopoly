package ru.welokot.monopoly.ui.fragment.prepareforgame

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.prepare_to_game_fragment.*
import javax.inject.Inject
import android.view.inputmethod.InputMethodManager.HIDE_IMPLICIT_ONLY
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.WindowManager
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.welokot.monopoly.R


class PrepareToGameFragment : DaggerFragment() {

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

        //initListeners()
        initAdapters()
        initRecyclerView()
    }

//    private fun initListeners() {
//        btnAddNewPlayer.setOnClickListener {
//            tvHint.visibility = View.GONE
//            adapter.addPlayer()
//        }
//
//        btnLoadGame.setOnClickListener {
//            Toast.makeText(
//                activity,
//                "Скоро будет",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//
//        btnStartGame.setOnClickListener {
//            tvHint.visibility = View.GONE
//            showDialogAddNewPlayer()
//        }
//    }

    private fun initAdapters() {
        adapter = PrepareToGameAdapter()
    }

    private fun initRecyclerView() {
        rvPlayersList.layoutManager = LinearLayoutManager(context)
        rvPlayersList.adapter = adapter
    }

    private fun showDialogAddNewPlayer() {
        val dialog = Dialog(context!!)
        dialog.setContentView(R.layout.dialog_add_player)
        dialog.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        dialog.window!!.attributes.windowAnimations = R.style.Animation_Design_BottomSheetDialog
        dialog.show()
    }
}



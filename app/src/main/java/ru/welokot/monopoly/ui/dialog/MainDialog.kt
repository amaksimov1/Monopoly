package ru.welokot.monopoly.ui.dialog

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_main.*
import ru.welokot.monopoly.R

class MainDialog() : DialogFragment() {

    companion object {
        const val TAG = "main_dialog"
        const val FRAGMENT_TYPE = "fragment_type"

        fun createInstance(fragmentType: String): MainDialog {
            val bundle = Bundle()
            bundle.putString(FRAGMENT_TYPE, fragmentType)
            val fragment =
                MainDialog()
            fragment.arguments = bundle
            return fragment
        }
    }

    var fragmentType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fragmentType = it.getString(
                FRAGMENT_TYPE
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val parent = parentFragment as InitMainDialog
        parent.initDialog()
        initListeners()
    }

    private fun initListeners() {
        tiedName.addTextChangedListener(getTextWatcher())
        tiedCapital.addTextChangedListener(getTextWatcher())

        btnClose.setOnClickListener {
            dismiss()
        }

        btnAction.setOnClickListener {
            if (btnAction.isEnabled) {
                val parent = parentFragment as OnDialogActionPressed
                val name = tiedName.text.toString()
                val capital = tiedCapital.text.toString()
                parent.actionPressed(name, capital)
            }
        }
    }

    private fun getTextWatcher() : TextWatcher {
        val parent = parentFragment as MainDialogTextWatcher.AllFieldsMustBeFilledIn
        return MainDialogTextWatcher(parent)
    }

    interface OnDialogActionPressed {
        fun actionPressed(name: String, capital: String)
    }

    interface InitMainDialog {
        fun initDialog()
    }
}
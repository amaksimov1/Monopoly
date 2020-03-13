package ru.welokot.monopoly.ui.dialog

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_main.*
import ru.welokot.monopoly.R
import ru.welokot.monopoly.models.TypeCapitalSwitcher


class MainDialog : DialogFragment() {

    val TAG = "main_dialog"

    lateinit var  typeCapitalSwitcher: TypeCapitalSwitcher
    private lateinit var parent: WorkerWithMainDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initListeners()
    }

    private fun init() {
        setTypeCapital()

        parent = parentFragment as WorkerWithMainDialog
        parent.initDialog(this)
    }

    private fun setTypeCapital() {
        typeCapitalSwitcher = TypeCapitalSwitcher()
        btnCapitalType.text = typeCapitalSwitcher.getType().name
    }

    private fun initListeners() {
        tiedName.addTextChangedListener(getTextWatcher())
        tiedCapital.addTextChangedListener(getTextWatcher())

        tiedName.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                dialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
            }
        }

        btnClose.setOnClickListener {
            dismiss()
        }

        btnAction.setOnClickListener {
            parent.actionButtonPressed(this)
        }

        btnCapitalType.setOnClickListener {
            typeCapitalSwitcher.switchType()
            btnCapitalType.text = typeCapitalSwitcher.getType().name
        }
    }

    private fun getTextWatcher() : TextWatcher {
        return MainDialogTextWatcher(parent, this)
    }
}

interface WorkerWithMainDialog {
    fun initDialog(dialog: MainDialog)
    fun actionButtonPressed(dialog: MainDialog)
    fun onTextChanged(dialog: MainDialog)
}
package ru.welokot.monopoly.ui.fragment.gameboard

import android.content.Context
import android.view.View
import kotlinx.android.synthetic.main.dialog_main.*
import ru.welokot.monopoly.models.TypeCapital
import ru.welokot.monopoly.ui.dialog.MainDialog
import ru.welokot.monopoly.ui.dialog.WorkerWithMainDialog

class CommitTransaction (
    private val transactionCommiter: TransactionCommiter
) : WorkerWithMainDialog {

    override fun initDialog(dialog: MainDialog) {
        dialog.tilName.visibility = View.GONE
    }

    override fun actionButtonPressed(dialog: MainDialog) {
        val capital = dialog.tiedCapital.text.toString()
        val typeCapital = dialog.typeCapitalSwitcher.getType()
        transactionCommiter.commitTransaction(capital, typeCapital)
        dialog.dismiss()
    }

    override fun onTextChanged(dialog: MainDialog) {
        val capitalFieldWasFilledIn = dialog.tiedCapital.text!!.isNotEmpty()
        dialog.btnAction.isEnabled = capitalFieldWasFilledIn
    }
}

interface TransactionCommiter {
    fun commitTransaction(transactionAmount: String, typeCapital: TypeCapital)
}
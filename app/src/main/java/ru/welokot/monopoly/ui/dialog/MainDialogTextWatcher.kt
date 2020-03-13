package ru.welokot.monopoly.ui.dialog

import android.text.Editable
import android.text.TextWatcher

class MainDialogTextWatcher(private val parent: WorkerWithMainDialog, private val dialog: MainDialog) : TextWatcher {
    override fun afterTextChanged(s: Editable?) {}
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        parent.onTextChanged(dialog)
    }
}


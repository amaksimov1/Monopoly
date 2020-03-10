package ru.welokot.monopoly.ui.dialog

import android.text.Editable
import android.text.TextWatcher

class MainDialogTextWatcher(private val `object`: AllFieldsMustBeFilledIn) : TextWatcher {
    override fun afterTextChanged(s: Editable?) {}
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        `object`.areAllFieldsFilledIn()
    }

    interface AllFieldsMustBeFilledIn {
        fun areAllFieldsFilledIn()
    }
}


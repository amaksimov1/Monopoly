package ru.welokot.monopoly.ui.fragment.prepareforgame

import android.annotation.SuppressLint
import android.content.Context
import kotlinx.android.synthetic.main.dialog_main.*
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.entity.player.PlayerEntity
import ru.welokot.monopoly.ui.dialog.MainDialog
import ru.welokot.monopoly.ui.dialog.WorkerWithMainDialog
import kotlin.random.Random

class AddingPlayer(
    private val playerAdder: PlayerAdder,
    private val context: Context
) : WorkerWithMainDialog {

    override fun initDialog(dialog: MainDialog) {}

    override fun actionButtonPressed(dialog: MainDialog) {
        val player = getPlayerFrom(dialog)
        playerAdder.addPlayer(player)
        dialog.tiedName.text?.clear()
        dialog.tiedName.requestFocus()
    }

    @SuppressLint("Recycle")
    private fun getPlayerFrom(dialog: MainDialog) : PlayerEntity {
        val player = PlayerEntity()
        player.name = dialog.tiedName.text.toString()
        player.icon = Random.nextInt(context.resources.obtainTypedArray(R.array.player_icon).length())
        val capital = dialog.tiedCapital.text.toString()
        val typeCapital = dialog.typeCapitalSwitcher.getType()
        player.setCapital(capital, typeCapital)
        return player
    }

    override fun onTextChanged(dialog: MainDialog) {
        val nameFieldWasFilledIn = dialog.tiedName.text!!.isNotEmpty()
        val capitalFieldWasFilledIn = dialog.tiedCapital.text!!.isNotEmpty()
        dialog.btnAction.isEnabled = nameFieldWasFilledIn && capitalFieldWasFilledIn
    }
}

interface PlayerAdder {
    fun addPlayer(newPlayer: PlayerEntity)
}
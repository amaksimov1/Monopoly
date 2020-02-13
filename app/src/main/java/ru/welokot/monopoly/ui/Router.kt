package ru.welokot.monopoly.ui

import androidx.fragment.app.FragmentManager
import ru.welokot.monopoly.R
import ru.welokot.monopoly.ui.fragment.prepareforgame.PrepareToGameFragment

object Router {

    fun showGameBoard(supportFragmentManager: FragmentManager){
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                PrepareToGameFragment()
            )
            .commitNow()
    }

    fun showPrepareToGameFragment(supportFragmentManager: FragmentManager) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                PrepareToGameFragment()
            )
            .commitNow()
    }

    fun showCalculatorDialog(){}
}
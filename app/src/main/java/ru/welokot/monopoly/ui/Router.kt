package ru.welokot.monopoly.ui

import androidx.fragment.app.FragmentManager
import ru.welokot.monopoly.R
import ru.welokot.monopoly.ui.fragment.prepareforgame.PrepareToGameFragment
import ru.welokot.monopoly.ui.fragment.start.StartFragment
import javax.inject.Inject

object Router {

    fun showStartFragment(supportFragmentManager: FragmentManager){
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                StartFragment.newInstance()
            )
            .commitNow()
    }

    fun showPrepareToGameFragment(supportFragmentManager: FragmentManager) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                PrepareToGameFragment.newInstance()
            )
            .commitNow()
    }

    fun showCalculatorDialog(){}
}
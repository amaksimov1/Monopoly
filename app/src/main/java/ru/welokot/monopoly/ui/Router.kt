package ru.welokot.monopoly.ui

import androidx.fragment.app.FragmentManager
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.Player
import ru.welokot.monopoly.ui.fragment.gameboard.GameBoardFragment
import ru.welokot.monopoly.ui.fragment.prepareforgame.PrepareToGameFragment

object Router {

    fun showGameBoardFragment(supportFragmentManager: FragmentManager, players: MutableList<Player>){
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                GameBoardFragment.newInstance(players),
                GameBoardFragment::class.java.simpleName
            )
            .commit()
    }

    fun showPrepareToGameFragment(supportFragmentManager: FragmentManager) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                PrepareToGameFragment(),
                PrepareToGameFragment::class.java.simpleName
            )
            .commitNow()
    }
}
package ru.welokot.monopoly.ui

import androidx.fragment.app.FragmentManager
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.entity.PlayerEntity
import ru.welokot.monopoly.ui.fragment.gameboard.GameBoardFragment
import ru.welokot.monopoly.ui.fragment.prepareforgame.PrepareToGameFragment

object Router {

    fun showGameBoardFragment(supportFragmentManager: FragmentManager, players: MutableList<PlayerEntity>){
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
            .replace(
                R.id.container,
                GameBoardFragment.newInstance(players),
                GameBoardFragment::class.java.simpleName
            )
            .commit()
    }

    fun showPrepareToGameFragment(supportFragmentManager: FragmentManager) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
            .replace(
                R.id.container,
                PrepareToGameFragment(),
                PrepareToGameFragment::class.java.simpleName
            )
            .commitNow()
    }
}
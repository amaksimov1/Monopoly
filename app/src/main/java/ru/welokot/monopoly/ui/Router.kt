package ru.welokot.monopoly.ui

import androidx.fragment.app.FragmentManager
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.entity.gameSession.GameSessionEntity
import ru.welokot.monopoly.db.entity.player.PlayerEntity
import ru.welokot.monopoly.ui.fragment.gameboard.GameBoardFragment
import ru.welokot.monopoly.ui.fragment.prepareforgame.PrepareToGameFragment
import ru.welokot.monopoly.ui.fragment.previousgames.PreviousGamesFragment

object Router {

    fun showGameBoardFragment(supportFragmentManager: FragmentManager, gameSession: GameSessionEntity){
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
            .replace(
                R.id.container,
                GameBoardFragment.newInstance(gameSession),
                GameBoardFragment::class.java.simpleName
            )
            .commitNow()
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

    fun showPreviousGamesFragment(supportFragmentManager: FragmentManager) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
            .replace(
                R.id.container,
                PreviousGamesFragment(),
                PreviousGamesFragment::class.java.simpleName
            )
            .commitNow()
    }
}
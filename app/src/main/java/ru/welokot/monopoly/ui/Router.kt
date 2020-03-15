package ru.welokot.monopoly.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.welokot.monopoly.R
import ru.welokot.monopoly.db.entity.gameSession.GameSessionEntity
import ru.welokot.monopoly.ui.fragment.gameboard.GameBoardFragment
import ru.welokot.monopoly.ui.fragment.gamemoves.GameMovesFragment
import ru.welokot.monopoly.ui.fragment.preparetogame.PrepareToGameFragment
import ru.welokot.monopoly.ui.fragment.previousgames.PreviousGamesFragment

object Router {

    private const val BACK_STACK_TAG = "back_stack_tag"

    fun showGameBoardFragment(fm: FragmentManager?, gameSession: GameSessionEntity) {
        clearBackStack(fm)
        showFragment(GameBoardFragment.newInstance(gameSession), fm, addToBackStack = false)
    }

    fun showPrepareToGameFragment(fm: FragmentManager?) {
        showFragment(PrepareToGameFragment(), fm, addToBackStack = false)
    }

    fun showPreviousGamesFragment(fm: FragmentManager?) {
        showFragment(PreviousGamesFragment(), fm, addToBackStack = true)
    }

    fun showGameMovesFragment(fm: FragmentManager?, gameSession: GameSessionEntity) {
        showFragment(GameMovesFragment.newInstance(gameSession), fm, addToBackStack = true)
    }

    private fun showFragment(
        currentFragment: Fragment,
        fm: FragmentManager?,
        container: Int = R.id.container,
        addToBackStack: Boolean
    ) {
        if (fm != null) {
            val transaction = fm.beginTransaction()
            transaction.setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            transaction.replace(container, currentFragment, currentFragment::class.java.simpleName)
            if (addToBackStack)
                transaction.addToBackStack(BACK_STACK_TAG)
            transaction.commit()
        }
    }

    private fun clearBackStack(fm: FragmentManager?) {
        if (fm != null) {
            if (fm.backStackEntryCount > 0) {
                val first = fm.getBackStackEntryAt(0)
                fm.popBackStack(
                    first.id,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
            }
        }
    }

    //.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
}
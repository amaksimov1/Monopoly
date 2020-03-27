package ru.welokot.monopoly.ui.activity

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import ru.welokot.monopoly.R
import ru.welokot.monopoly.ui.Router.showPrepareToGameFragment
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory
    lateinit var viewModel: MainActivityViewModel
    var backButtonWasPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        viewModel = ViewModelProvider(this, providerFactory).get(MainActivityViewModel::class.java)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        if (savedInstanceState == null) {
            showPrepareToGameFragment(supportFragmentManager)
        }
        initObserver()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            if (backButtonWasPressed) {
                super.onBackPressed()
            } else {
                Toast.makeText(this, "Нажмите еще раз, чтобы выйти", Toast.LENGTH_SHORT).show()
                backButtonWasPressed = true
                viewModel.startTimer()
            }
        } else {
            super.onBackPressed()
        }
    }

    private fun initObserver() {
        viewModel.timerRunOutLivaData.observe(this, Observer {
            backButtonWasPressed = false
        })
    }
}

package ru.welokot.monopoly.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import ru.welokot.monopoly.R
import ru.welokot.monopoly.ui.Router.showStartFragment
import ru.welokot.monopoly.ui.fragment.start.StartFragment
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewModel = ViewModelProvider(this, providerFactory).get(MainActivityViewModel::class.java)

        if (savedInstanceState == null) {
            showStartFragment(supportFragmentManager)
        }
    }
}

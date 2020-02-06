package ru.welokot.monopoly.ui.activity

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import ru.welokot.monopoly.R
import ru.welokot.monopoly.ui.fragment.MainFragment

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,
                    MainFragment.newInstance()
                )
                .commitNow()
        }
    }
}

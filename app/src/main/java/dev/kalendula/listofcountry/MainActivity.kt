package dev.kalendula.listofcountry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commitNow
import dagger.hilt.android.AndroidEntryPoint
import dev.kalendula.listofcountry.ui.ListFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                replace(R.id.container, ListFragment.newInstance())
            }
        }
    }
}
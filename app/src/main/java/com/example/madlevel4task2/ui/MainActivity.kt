package com.example.madlevel4task2.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.madlevel4task2.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in arrayOf(R.id.gameFragment)) {
                Log.i("TAG", "is gameFragment")
                toggleActionBarForGameFragment(menu)
            } else {
                Log.i("TAG", "is gameHistoryFragment: " + (destination.id in arrayOf(R.id.gameHistoryFragment)).toString())
                toggleActionBarForGameHistoryFragment(menu)
            }
        }

        return true
    }

    private fun toggleActionBarForGameFragment(menu: Menu) {
        menu.findItem(R.id.action_gameHistory).isVisible = true
        menu.findItem(R.id.action_clear).isVisible = false

        supportActionBar?.title = getString(R.string.game_fragment_action_bar_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
    }

    private fun toggleActionBarForGameHistoryFragment(menu: Menu) {
        menu.findItem(R.id.action_gameHistory).isVisible = false
        menu.findItem(R.id.action_clear).isVisible = true

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(R.string.game_history_fragment_action_bar_title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_gameHistory -> {
                navController.navigate(R.id.action_gameFragment_to_gameHistoryFragment)

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
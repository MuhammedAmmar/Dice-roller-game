package com.moworks.diceroller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.moworks.diceroller.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

  lateinit var drawerLayout : DrawerLayout
  lateinit var navController: NavController
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main)

        val navigationView  = binding.navigationView
        drawerLayout = binding.drawer
        navController  = findNavController(R.id.myNavHost)

        NavigationUI.setupActionBarWithNavController(this , navController , drawerLayout)
        NavigationUI.setupWithNavController(navigationView , navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == controller.graph.startDestination) {
                supportActionBar?.title = getString(R.string.home_screen_title)
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                supportActionBar?.title = getString(R.string.game_title)
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        NavigationUI.navigateUp(navController , drawerLayout )

        return super.onSupportNavigateUp()
    }
}
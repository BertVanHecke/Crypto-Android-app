package com.bertvanhecke.cryptocurrencyapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.bertvanhecke.cryptocurrencyapp.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.plant(Timber.DebugTree())



        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val drawerLayout = binding.drawerLayout
        val bottomNavigation = binding.bottomNavigationView
        val navigationView = binding.navigationView



        //Bottom tab navigator
        navController = findNavController(R.id.myNavHostFragment)
        bottomNavigation.setupWithNavController(navController)

        //Hide tab bar on about fragment
        navController.addOnDestinationChangedListener{_, nd: NavDestination, _->
            if(nd.id == R.id.aboutFragment){
                bottomNavigation.visibility = View.GONE
            }else{
                bottomNavigation.visibility = View.VISIBLE
            }
        }

        //Drawer navigator
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        NavigationUI.setupWithNavController(navigationView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}
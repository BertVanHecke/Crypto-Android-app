package com.bertvanhecke.cryptocurrencyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController

class UserActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        navController = findNavController(R.id.myNavHostUserFragment)
        val graph = navController.navInflater.inflate(R.navigation.navigation_user)


        val user = UserSingelton.instance().user

        if (user != null) {
            graph.setStartDestination(R.id.profileFragment)
        } else {
            graph.setStartDestination(R.id.loginFragment)
        }

        navController.setGraph(graph, intent.extras)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
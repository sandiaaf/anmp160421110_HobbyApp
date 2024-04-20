package com.sandiarta.hobbyapp_160421110.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.sandiarta.hobbyapp_160421110.R
import com.sandiarta.hobbyapp_160421110.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController:NavController

    var isLogin = false
    var id_account = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this,navController)

        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        binding.bottomNav.setupWithNavController(navController)

        var sharedFile = "com.sandiarta.hobbyapp_160421110"
        var shared: SharedPreferences = this.getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        this.id_account = shared.getInt("ID_ACCOUNT",0)
        if (id_account == 0) {
            val action = HomeFragmentDirections.actionLoginFragment()
            navController.navigate(action)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.drawerLayout) || super.onSupportNavigateUp()
    }
    fun OffMenu() {
        binding.bottomNav.isVisible = false
        binding.navView.isVisible = false
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    fun OnMenu() {
        binding.bottomNav.isVisible = true
        binding.navView.isVisible = true
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

    }

}
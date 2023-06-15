package com.programmsoft.procurrency

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.navigation.NavigationView
import com.programmsoft.procurrency.databinding.ActivityMainBinding
import com.programmsoft.utils.Components


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationUI()
        Components.clickMenu(binding, this, supportFragmentManager)
    }

    private fun navigationUI() {
        navController = findNavController(R.id.nav_host_fragment_content_main)
        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val bottomNavigationView = binding.appBarMain.contentMain.btnNav
        navView.itemIconTintList = null
        bottomNavigationView.elevation = 0F
        bottomNavigationView.itemIconTintList = null
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_main,
                R.id.nav_currencies,
                R.id.nav_calculator,
                R.id.nav_share,
                R.id.nav_about
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, _, _ ->
            binding.appBarMain.toolbar.setNavigationIcon(R.drawable.menu_hamburger)
        }
        val actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, findViewById(R.id.toolbar), R.string.app_name, R.string.app_name)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.drawerArrowDrawable.color = ContextCompat.getColor(this,R.color.black)
        actionBarDrawerToggle.syncState()
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}
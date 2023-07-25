package com.programmsoft.procurrency

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.programmsoft.procurrency.databinding.ActivityMainBinding
import com.programmsoft.utils.Components
import com.programmsoft.utils.Functions
import com.programmsoft.utils.SharedPreference
import com.programmsoft.viewmodels.CurrenciesViewModel


class MainActivity : AppCompatActivity() {
    //  private val binding: ActivityMainBinding by viewBinding()
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController

    companion object {
        lateinit var binding: ActivityMainBinding
        var topSpinnerPosition = 0
        var bottomSpinnerPosition = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        SharedPreference.init(this)
        navigationUI()
//        if (SharedPreference.downloadCurrency == 0) {
//            uploadData()
//        }
        Functions.statusAndNavigationBars(this, window)
    }

    private fun uploadData() {
        val currenciesViewModel: CurrenciesViewModel =
            ViewModelProvider(this)[CurrenciesViewModel::class.java]
        currenciesViewModel.getCurrencies().observe(this)
        {
            if (it) {
                Toast.makeText(
                    this,
                    Components.db.currencyDao().getAllCurrency().size.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                SharedPreference.downloadCurrency = 1
            }
        }

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
            ActionBarDrawerToggle(
                this,
                drawerLayout,
                findViewById(R.id.toolbar),
                R.string.app_name,
                R.string.app_name
            )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.drawerArrowDrawable.color =
            ContextCompat.getColor(this, R.color.black)
        actionBarDrawerToggle.syncState()
        Components.clickMenu(binding, this, supportFragmentManager)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}
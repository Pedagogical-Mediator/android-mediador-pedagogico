package com.ufms.mediadorpedagogico.presentation.main

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ActivityMainBinding
import com.ufms.mediadorpedagogico.presentation.util.extensions.*
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseActivity
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by inject()
    private val navController by lazy { findNavController(R.id.main_navigation_fragment) }
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        lifecycle.addObserver(viewModel)

        setupCustomizedToolbar(
            binding.toolbarCustomized,
            true,
            getString(R.string.activity_homework_label)
        )
//        val navController = findNavController(R.id.main_navigation_fragment)

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupNavigation(navController) //setup navigation
        setupActionBar(navController, appBarConfiguration) // setup action bar
        //hear for event changes
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            val dest: String = try {
//                resources.getResourceName(destination.id)
//            } catch (e: Resources.NotFoundException) {
//                destination.id.toString()
//            }
//        }
    }

    private fun setupActionBar(
        navController: NavController,
        appBarConfig: AppBarConfiguration
    ) {
//        setupActionBarWithNavController(navController, appBarConfig)
    }

    private fun setupNavigation(navController: NavController) {
//        val sideNavView = findViewById<NavigationView>(R.id.nav_view)
//        sideNavView?.setupWithNavController(navController)
        NavigationUI.setupWithNavController(binding.navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)

//        //fragments load from here but how ?
//        appBarConfiguration = AppBarConfiguration(
//            setOf(R.id.fragment1, R.id.fragment2),
//            drawerLayout
//        )
    }
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val retValue = super.onCreateOptionsMenu(menu)
//        val navigationView = findViewById<NavigationView>(R.id.nav_view)
//        if (navigationView == null) {
//            //android needs to know what menu I need
//            menuInflater.inflate(R.menu.navigation_menu, menu)
//            return true
//        }
//        return retValue
//    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //I need to open the drawer onClick
        when (item!!.itemId) {
            android.R.id.home ->
                drawer_layout.openDrawer(GravityCompat.START)
        }
        return item.onNavDestinationSelected(findNavController(R.id.main_navigation_fragment))
                || super.onOptionsItemSelected(item)
    }
    override fun onBackPressed() {
        //the code is beautiful enough without comments
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
package com.ufms.mediadorpedagogico.presentation.main.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ActivityDashboardBinding
import com.ufms.mediadorpedagogico.presentation.main.MainViewModel
import com.ufms.mediadorpedagogico.presentation.util.extensions.setupCustomizedToolbar
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseActivity
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import org.koin.android.ext.android.inject

class DashboardActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: ActivityDashboardBinding
    private val viewModel: MainViewModel by inject()
    private val navController by lazy { findNavController(R.id.main_navigation_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        lifecycle.addObserver(viewModel)
        setupCustomizedToolbar(
            binding.toolbarCustomized,
            true
        )
        setupNavigation(navController)
    }

    private fun setupNavigation(navController: NavController) {
        NavigationUI.setupWithNavController(binding.navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
        navController.addOnDestinationChangedListener { _, _, _ ->
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_white)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return item?.onNavDestinationSelected(findNavController(R.id.main_navigation_fragment)) ?: false
                || super.onOptionsItemSelected(item)
    }

    fun onNextTitle(title: String?) {
        title?.let {
            setupCustomizedToolbar(
                binding.toolbarCustomized,
                true,
                it
            )
        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, DashboardActivity::class.java)
        }
    }
}
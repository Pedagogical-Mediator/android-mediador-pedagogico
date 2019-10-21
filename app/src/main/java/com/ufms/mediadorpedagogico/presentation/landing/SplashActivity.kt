package com.ufms.mediadorpedagogico.presentation.landing

import android.os.Bundle
import android.view.MenuItem
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeAction
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseActivity
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.Navigator
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    private val viewModel: SplashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        lifecycle.addObserver(viewModel)
        super.onCreate(savedInstanceState)
    }

    override fun subscribeUi() {
        super.subscribeUi()
        viewModel.goToMain.observeAction(this, ::onNextGoToMainValue)
        viewModel.goToLogin.observeAction(this, ::onNextGoToLoginValue)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onNextGoToMainValue(shouldGo: Boolean?) {
        shouldGo?.let { if (it) Navigator.goToMain(this, true) }
    }

    private fun onNextGoToLoginValue(shouldGo: Boolean?) {
        shouldGo?.let { if (it) Navigator.goToLogin(this, true) }
    }
}
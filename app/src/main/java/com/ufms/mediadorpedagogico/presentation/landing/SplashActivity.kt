package com.ufms.mediadorpedagogico.presentation.landing

import android.os.Bundle
import com.ufms.mediadorpedagogico.presentation.util.extensions.observe
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
        viewModel.goToMain.observe(this, ::onNextGoToMainValue)
        viewModel.goToLogin.observe(this, ::onNextGoToLoginValue)
    }

    private fun onNextGoToMainValue(shouldGo: Boolean?) {
        shouldGo?.let { if (it) Navigator.goToMain(this, true) }
    }

    private fun onNextGoToLoginValue(shouldGo: Boolean?) {
        shouldGo?.let { if (it) Navigator.goToLogin(this, true) }
    }
}
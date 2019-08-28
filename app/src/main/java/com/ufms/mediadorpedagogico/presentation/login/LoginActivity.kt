package com.ufms.mediadorpedagogico.presentation.login

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ActivityLoginBinding
import com.ufms.mediadorpedagogico.domain.extensions.then
import com.ufms.mediadorpedagogico.presentation.structure.base.BaseActivity
import com.ufms.mediadorpedagogico.presentation.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.structure.navigation.Navigator
import com.ufms.mediadorpedagogico.presentation.util.extensions.observe
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeChanges
import com.ufms.mediadorpedagogico.presentation.util.extensions.setOnClickListener
import org.koin.android.ext.android.inject

class LoginActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    private val viewModel: LoginViewModel by inject()
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        lifecycle.addObserver(viewModel)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setupUi()
        super.onCreate(savedInstanceState)
    }

    override fun subscribeUi() {
        super.subscribeUi()
        viewModel.showEmailFieldError.observe(this, this::onNextEmailError)
        viewModel.showPasswordFieldError.observe(this, this::onNextPasswordError)
        viewModel.goToMain.observe(this, this::onNextGoToMain)
    }

    private fun setupUi() {
        binding.emailInput.observeChanges(viewModel::onEmailChanged)
        binding.passwordInput.observeChanges(viewModel::onPasswordChanged)
        binding.facebookButton.setOnClickListener(viewModel::onFacebookButtonClicked)
        binding.googleButton.setOnClickListener(viewModel::onGoogleButtonClicked)
        binding.registerButton.setOnClickListener(viewModel::onSignUpClicked)
        binding.submitButton.setOnClickListener(viewModel::onSubmitClicked)
    }

    private fun onNextGoToMain(shouldGo: Boolean?) {
        shouldGo?.let { Navigator.goToMain(this, true) }
    }

    private fun onNextEmailError(shouldShowError: Boolean?) {
        shouldShowError?.let {
            binding.emailInput.error = it then getString(R.string.error_invalid_email)
        }
    }

    private fun onNextPasswordError(shouldShowError: Boolean?) {
        shouldShowError?.let {
            binding.passwordInput.error = it then getString(R.string.error_invalid_password)

        }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}

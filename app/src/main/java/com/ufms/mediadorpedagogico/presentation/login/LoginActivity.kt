package com.ufms.mediadorpedagogico.presentation.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ActivityLoginBinding
import com.ufms.mediadorpedagogico.domain.extensions.then
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeAction
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeChanges
import com.ufms.mediadorpedagogico.presentation.util.extensions.setOnClickListener
import com.ufms.mediadorpedagogico.presentation.util.extensions.shouldClearTask
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseActivity
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.Navigator
import org.koin.android.ext.android.inject

class LoginActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    private val viewModel: LoginViewModel by inject()
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        lifecycle.addObserver(viewModel)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setupUi()
        super.onCreate(savedInstanceState)
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            showGroupFieldError.observeAction(this@LoginActivity, ::onNextGroupError)
            showNameFieldError.observeAction(this@LoginActivity, ::onNextNameError)
            goToMain.observeAction(this@LoginActivity, ::onNextGoToMain)
        }
    }

    private fun setupUi() {
        with(binding) {
            textInputClassCode.observeChanges(viewModel::onClassKeyChanged)
            textInputName.observeChanges(viewModel::onNameChanged)
            submitButton.setOnClickListener(viewModel::onSubmitClicked)
        }
    }

    /**
     * TODO Botão de logout
     * TODO habilitação de notificações
     *
     */

    private fun onNextGoToMain(shouldGo: Boolean?) {
        shouldGo?.let { Navigator.goToMain(this, true) }
    }

    private fun onNextGroupError(shouldShowError: Boolean?) {
        shouldShowError?.let {
            binding.textInputClassCode.error = it then getString(R.string.error_invalid_group)
        }
    }

    private fun onNextNameError(shouldShowError: Boolean?) {
        shouldShowError?.let {
            binding.textInputName.error = it then getString(R.string.error_invalid_name)

        }
    }

    companion object {
        fun createIntent(context: Context, shouldClearTask: Boolean): Intent {
            return Intent(context, LoginActivity::class.java).apply {
                shouldClearTask(shouldClearTask)
            }
        }
    }
}

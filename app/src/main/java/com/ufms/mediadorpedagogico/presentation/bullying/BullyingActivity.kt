package com.ufms.mediadorpedagogico.presentation.bullying

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ActivityBullyingBinding
import com.ufms.mediadorpedagogico.presentation.util.extensions.observe
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseActivity
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import org.koin.android.ext.android.inject

class BullyingActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: ActivityBullyingBinding
    private val viewModel: BullyingViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bullying)
        lifecycle.addObserver(viewModel)
        setupUi()
        super.onCreate(savedInstanceState)
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            placeholder.observe(this@BullyingActivity, ::onNextPlaceholder)
        }
    }

    private fun setupUi() {

    }

    private fun onNoContentReturned(noContentReturned: Boolean?) {

    }

    private fun onNextPlaceholder(placeholder: Placeholder?) {
        placeholder?.let { binding.loadingPlaceholder.placeholder = it }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, BullyingActivity::class.java)
        }
    }
}
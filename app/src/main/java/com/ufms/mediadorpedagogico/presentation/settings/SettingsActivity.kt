package com.ufms.mediadorpedagogico.presentation.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ActivitySettingsBinding
import com.ufms.mediadorpedagogico.domain.entity.notice.Notice
import com.ufms.mediadorpedagogico.presentation.util.extensions.setupCustomizedToolbar
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseActivity
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import org.koin.android.ext.android.inject

class SettingsActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: ActivitySettingsBinding
    private val viewModel: SettingsViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings)
        setupCustomizedToolbar(
            binding.toolbarCustomized,
            true,
            getString(R.string.activity_settings_label)
        )
        lifecycle.addObserver(viewModel)
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun createIntent(context: Context, notice: Notice): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }
}
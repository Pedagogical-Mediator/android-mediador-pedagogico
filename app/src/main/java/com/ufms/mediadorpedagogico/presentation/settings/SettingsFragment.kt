package com.ufms.mediadorpedagogico.presentation.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.FragmentSettingsBinding
import com.ufms.mediadorpedagogico.domain.entity.notice.Notice
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseFragment
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import org.koin.android.ext.android.inject

class SettingsFragment : BaseFragment() {
    override val toolbarTitle: String
        get() = getString(R.string.activity_settings_label)

    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: FragmentSettingsBinding
    private val viewModel: SettingsViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        return binding.root
    }

    companion object {
        fun createIntent(context: Context, notice: Notice): Intent {
            return Intent(context, SettingsFragment::class.java)
        }
    }
}
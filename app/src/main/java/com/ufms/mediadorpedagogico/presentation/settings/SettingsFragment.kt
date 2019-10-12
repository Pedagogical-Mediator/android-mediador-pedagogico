package com.ufms.mediadorpedagogico.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.FragmentSettingsBinding
import com.ufms.mediadorpedagogico.presentation.util.extensions.observe
import com.ufms.mediadorpedagogico.presentation.util.extensions.onChecked
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

    override fun onResume() {
        super.onResume()
        setupUi()
    }

    override fun subscribeUi() {
        super.subscribeUi()
        viewModel.subscribedNews.observe(viewLifecycleOwner, ::onSubscribedNews)
        viewModel.subscribedNotices.observe(viewLifecycleOwner, ::onSubscribedNotices)
    }

    private fun setupUi() {
        with(binding) {
            cmpNews.switchNotification.onChecked(viewModel::onNewsSwitch)
            cmpNotices.switchNotification.onChecked(viewModel::onNoticesSwitch)
        }
    }

    private fun onSubscribedNews(isSubscribed: Boolean?) {
        isSubscribed?.let(binding.cmpNews.switchNotification::setChecked)
    }

    private fun onSubscribedNotices(isSubscribed: Boolean?) {
        isSubscribed?.let(binding.cmpNotices.switchNotification::setChecked)
    }
}

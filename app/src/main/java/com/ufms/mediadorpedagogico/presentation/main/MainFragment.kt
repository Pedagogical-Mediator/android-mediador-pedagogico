package com.ufms.mediadorpedagogico.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ufms.mediadorpedagogico.databinding.FragmentMainBinding
import com.ufms.mediadorpedagogico.presentation.util.extensions.observe
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeEvent
import com.ufms.mediadorpedagogico.presentation.util.extensions.setOnClickListener
import com.ufms.mediadorpedagogico.presentation.util.extensions.setVisible
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseFragment
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import org.koin.android.ext.android.inject

class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        setupUi()
        return binding.root
    }

    fun subscribeUi() {
//        viewModel.dialog.observeEvent(this, ::onNextDialog)
//        viewModel.goTo.observeEvent(this, ::onNextNavigation)
//        viewModel.toast.observeEvent(this, ::onNextToast)
        with(viewModel) {
            placeholder.observe(this@MainFragment, ::onNextPlaceholder)
            noContentReturned.observeEvent(this@MainFragment, ::onNoContentReturned)
        }
    }

    private fun setupUi() {
        with(binding) {
            cardViewHomework.setOnClickListener(viewModel::goToHomework)
            cardViewNotice.setOnClickListener(viewModel::goToNotice)
            cardViewNews.setOnClickListener(viewModel::goToNews)
            cardViewBullying.setOnClickListener(viewModel::goToBullying)
        }
    }

    private fun onNoContentReturned(noContentReturned: Boolean?) {
        noContentReturned?.let {
            binding.includedPlaceholderNoResults.root.setVisible(true)
        }
    }

    private fun onNextPlaceholder(placeholder: Placeholder?) {
        placeholder?.let { binding.loadingPlaceholder.placeholder = it }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MainFragment::class.java)
        }
    }
}
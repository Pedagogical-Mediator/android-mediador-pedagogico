package com.ufms.mediadorpedagogico.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ufms.mediadorpedagogico.databinding.FragmentMainBinding
import com.ufms.mediadorpedagogico.presentation.util.extensions.observe
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeEvent
import com.ufms.mediadorpedagogico.presentation.util.extensions.setOnClickListener
import com.ufms.mediadorpedagogico.presentation.util.extensions.setVisible
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseFragment
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.navigateSafe
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import org.koin.android.ext.android.inject

class MainFragment : BaseFragment() {
    override val toolbarTitle: String
        get() = ""
    override val baseViewModel: BaseViewModel
        get() = viewModel

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by inject()
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMainBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        setupUi()
        return binding.root
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            placeholder.observe(this@MainFragment, ::onNextPlaceholder)
            noContentReturned.observeEvent(this@MainFragment, ::onNoContentReturned)
        }
    }

    private fun setupUi() {
        with(binding) {
            cardViewHomework.setOnClickListener(viewModel::goToHomework)
            cardViewNotice.setOnClickListener(::goToNotice)
            cardViewNews.setOnClickListener(viewModel::goToNews)
            cardViewBullying.setOnClickListener(viewModel::goToBullying)
        }
    }

    private fun goToNotice() {
        navController.navigateSafe(MainFragmentDirections.actionMainFragmentToNoticeListFragment())
    }

    private fun onNoContentReturned(noContentReturned: Boolean?) {
        noContentReturned?.let {
            binding.includedPlaceholderNoResults.root.setVisible(true)
        }
    }

    private fun onNextPlaceholder(placeholder: Placeholder?) {
        placeholder?.let { binding.loadingPlaceholder.placeholder = it }
    }
}
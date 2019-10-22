package com.ufms.mediadorpedagogico.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ufms.mediadorpedagogico.databinding.FragmentMainBinding
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeAction
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
            placeholder.observeAction(this@MainFragment, ::onNextPlaceholder)
            noContentReturned.observeEvent(this@MainFragment, ::onNoContentReturned)
        }
    }

    private fun setupUi() {
        with(binding) {
            cardViewHomework.setOnClickListener(::goToHomework)
            cardViewNotice.setOnClickListener(::goToNotice)
            cardViewNews.setOnClickListener(::goToNews)
            cardViewSettings.setOnClickListener(::goToSettings)
            cardViewBullying.setOnClickListener(::goToBullying)
        }
    }

    /**
     * TODO arrumar para enviar a turma nas requisições
     * TODO verificar porque não está abrindo no navegador as notícias
     * TODO arrumar layout das configurações
     *
     * */
    private fun setupCache(subscribe: Boolean?) {
    }

    private fun goToNotice() {
        navController.navigateSafe(MainFragmentDirections.actionMainFragmentToNoticeListFragment())
    }

    private fun goToNews() {
        navController.navigateSafe(MainFragmentDirections.actionMainFragmentToNewsListFragment())
    }

    private fun goToHomework() {
        navController.navigateSafe(MainFragmentDirections.actionMainFragmentToHomeworkListFragment())
    }

    private fun goToSettings() {
        navController.navigateSafe(MainFragmentDirections.actionMainFragmentToSettingsFragment())
    }

    private fun goToBullying() {
        navController.navigateSafe(MainFragmentDirections.actionMainFragmentToBullyingFragment())
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
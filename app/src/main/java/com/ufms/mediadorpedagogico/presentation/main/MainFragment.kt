package com.ufms.mediadorpedagogico.presentation.main

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.FragmentMainBinding
import com.ufms.mediadorpedagogico.domain.entity.Calendar
import com.ufms.mediadorpedagogico.presentation.util.extensions.*
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_question, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_question_mark -> {
                navController.navigateSafe(MainFragmentDirections.actionMainFragmentToHelpBottomSheet("Ajuda", "Descrição da ajuda"))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            placeholder.observeAction(viewLifecycleOwner, ::onNextPlaceholder)
            noContentReturned.observeEvent(viewLifecycleOwner, ::onNoContentReturned)
            calendarReceived.observeAction(viewLifecycleOwner, ::onCalendarReceived)
        }
    }

    private fun setupUi() {
        with(binding) {
            cardViewHomework.setOnClickListener(::goToHomework)
            cardViewNotice.setOnClickListener(::goToNotice)
            cardViewNews.setOnClickListener(::goToNews)
            cardViewSettings.setOnClickListener(::goToSettings)
            cardViewBullying.setOnClickListener(::goToBullying)
            cardViewGuild.setOnClickListener(::goToGuild)
            cardViewAbout.setOnClickListener(::goToAbout)
            cardViewCalendar.setOnClickListener(viewModel::onCalendarClicked)
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

    private fun onCalendarReceived(calendar: Calendar?) {
        loadPage(calendar?.link)
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

    private fun goToBullying() {
        navController.navigateSafe(MainFragmentDirections.actionMainFragmentToBullyingFragment())
    }

    private fun goToGuild() {
        navController.navigateSafe(MainFragmentDirections.actionMainFragmentToGuildFragment())
    }

    private fun goToSettings() {
        navController.navigateSafe(MainFragmentDirections.actionMainFragmentToSettingsFragment())
    }

    private fun goToAbout() {
        navController.navigateSafe(MainFragmentDirections.actionMainFragmentToAboutFragment())
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
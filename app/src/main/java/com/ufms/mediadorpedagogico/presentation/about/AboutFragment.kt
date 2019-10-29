package com.ufms.mediadorpedagogico.presentation.about

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.FragmentAboutBinding
import com.ufms.mediadorpedagogico.domain.entity.About
import com.ufms.mediadorpedagogico.presentation.main.MainFragmentDirections
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeAction
import com.ufms.mediadorpedagogico.presentation.util.structure.base.Base2
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.navigateSafe
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import org.koin.android.ext.android.inject

class AboutFragment : Base2() {
    override val titleHelp: String get() = getString(R.string.help_about_title)
    override val descriptionHelp: String get() = getString(R.string.help_about_description)
    override val toolbarTitle: String get() = getString(R.string.about)
    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: FragmentAboutBinding
    private val viewModel: AboutViewModel by inject()
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        return binding.root
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            placeholder.observeAction(viewLifecycleOwner, ::onNextPlaceholder)
            aboutReceived.observeAction((viewLifecycleOwner), ::onAboutReceived)
        }
    }

    override fun openHelp() {
        navController.navigateSafe(AboutFragmentDirections.actionAboutFragmentToHelpBottomSheet(titleHelp, descriptionHelp))
    }

    private fun onAboutReceived(about: About?) {
        about?.let {
            binding.about = it
        }
    }

    private fun onNextPlaceholder(placeholder: Placeholder?) {
        placeholder?.let { binding.loadingPlaceholder.placeholder = it }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, AboutFragment::class.java)
        }
    }
}
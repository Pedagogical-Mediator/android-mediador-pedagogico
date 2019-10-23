package com.ufms.mediadorpedagogico.presentation.guild

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.FragmentGuildBinding
import com.ufms.mediadorpedagogico.domain.entity.Guild
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeAction
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseFragment
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import org.koin.android.ext.android.inject

class GuildFragment : BaseFragment() {

    override val toolbarTitle: String get() = getString(R.string.guild_fragment_label)
    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: FragmentGuildBinding
    private val viewModel: GuildViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentGuildBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        setupUi()
        return binding.root
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            placeholder.observeAction(viewLifecycleOwner, ::onNextPlaceholder)
            guildReceived.observeAction((viewLifecycleOwner), ::onGuildReceived)
        }
    }

    private fun onGuildReceived(guild: Guild?) {
        guild?.let {
            binding.guild = it
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
            return Intent(context, GuildFragment::class.java)
        }
    }
}
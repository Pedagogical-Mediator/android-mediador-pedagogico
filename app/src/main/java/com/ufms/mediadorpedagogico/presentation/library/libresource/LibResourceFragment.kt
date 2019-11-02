package com.ufms.mediadorpedagogico.presentation.library.libresource

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.FragmentLibresourcesBinding
import com.ufms.mediadorpedagogico.domain.entity.library.LibResource
import com.ufms.mediadorpedagogico.presentation.util.extensions.ifNull
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeAction
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeEvent
import com.ufms.mediadorpedagogico.presentation.util.extensions.setVisible
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseFragment
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.navigateSafe
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LibResourceFragment : BaseFragment() {

    override val baseViewModel: BaseViewModel get() = viewModel
    override val toolbarTitle: String get() = args.title
    override val titleHelp: String get() = getString(R.string.library_topics_title)
    override val descriptionHelp: String get() = getString(R.string.library_topics_description)

    private val navController by lazy { findNavController() }
    private lateinit var binding: FragmentLibresourcesBinding
    private var libResourcesAdapter: LibResourceAdapter? = null
    private val viewModel: LibResourceViewModel by viewModel { parametersOf(args.topicId) }
    private val args: LibResourceFragmentArgs by navArgs()

    override fun openHelp() {
        navController.navigateSafe(
            LibResourceFragmentDirections.actionLibResourceFragmentToHelpBottomSheet(
                titleHelp,
                descriptionHelp
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentLibresourcesBinding.inflate(inflater, container, false)
        setupAdapter()
        return binding.root
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            libResources.observeEvent(viewLifecycleOwner, ::onLibResources)
            placeholder.observeAction(viewLifecycleOwner, ::onNextPlaceholder)
            noContentReturned.observeEvent(viewLifecycleOwner, ::onNoContentReturned)
        }
    }

    private fun onLibResources(libResources: List<LibResource>?) {
        libResources?.let {
            libResourcesAdapter?.run {
                setItems(it)
            }
        }
    }

    private fun setupAdapter() {
        libResourcesAdapter.ifNull {
            libResourcesAdapter = LibResourceAdapter()
            binding.topicsList.adapter.ifNull {
                binding.topicsList.apply {
                    adapter = libResourcesAdapter
                    layoutManager = LinearLayoutManager(context)
                }
            }
        }
    }

    fun onNoContentReturned(unit: Unit?) {
        binding.includedPlaceholderNoResults.root.setVisible(true)
    }

    private fun onNextPlaceholder(placeholder: Placeholder?) {
        placeholder?.let { binding.includedLoadingPlaceholder.placeholder = it }
    }
}
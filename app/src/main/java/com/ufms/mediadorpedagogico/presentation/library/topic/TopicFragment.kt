package com.ufms.mediadorpedagogico.presentation.library.topic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.FragmentTopicsBinding
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseFragment
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class TopicFragment: BaseFragment() {
    override val baseViewModel: BaseViewModel
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val toolbarTitle: String get() = getString(R.string.topics)
    override val titleHelp: String get() = getString(R.string.library_topics_title)
    override val descriptionHelp: String get() = getString(R.string.library_topics_description)

    private val viewModel: TopicViewModel by inject()
    private val navController by lazy { findNavController() }
    private lateinit var binding: FragmentTopicsBinding

    override fun openHelp() {
        // TODO abrir o fragmento de help
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentTopicsBinding.inflate(inflater, container, false)
        setupUi()
        return binding.root
    }

    private fun setupUi() {

    }
}
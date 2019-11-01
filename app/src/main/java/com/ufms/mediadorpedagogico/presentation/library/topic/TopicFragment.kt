package com.ufms.mediadorpedagogico.presentation.library.topic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.FragmentTopicsBinding
import com.ufms.mediadorpedagogico.domain.entity.Topic
import com.ufms.mediadorpedagogico.presentation.util.extensions.*
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseFragment
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.navigateSafe
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import org.koin.android.ext.android.inject

class TopicFragment : BaseFragment() {

    override val baseViewModel: BaseViewModel get() = viewModel
    override val toolbarTitle: String get() = getString(R.string.topics)
    override val titleHelp: String get() = getString(R.string.library_topics_title)
    override val descriptionHelp: String get() = getString(R.string.library_topics_description)

    private val viewModel: TopicViewModel by inject()
    private val navController by lazy { findNavController() }
    private lateinit var binding: FragmentTopicsBinding
    private var topicAdapter: TopicAdapter? = null

    override fun openHelp() {
        navController.navigateSafe(
            TopicFragmentDirections.actionTopicFragmentToHelpBottomSheet(
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
        binding = FragmentTopicsBinding.inflate(inflater, container, false)
        setupAdapter()
        return binding.root
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            topics.observeEvent(viewLifecycleOwner, ::onTopics)
            placeholder.observeAction(viewLifecycleOwner, ::onNextPlaceholder)
            noContentReturned.observeEvent(viewLifecycleOwner, ::onNoContentReturned)
        }
    }

    private fun onTopics(topics: List<Topic>?) {
        topics?.let {
            topicAdapter?.run {
                setItems(it)
            }
        }
    }

    private fun setupAdapter() {
        topicAdapter.ifNull {
            topicAdapter = TopicAdapter(::goToLibResources)
            binding.topicsList.adapter.ifNull {
                binding.topicsList.apply {
                    adapter = topicAdapter
                    layoutManager = LinearLayoutManager(context)
                }
            }
        }
    }

    fun onNoContentReturned(unit: Unit?) {
        binding.includedPlaceholderNoResults.root.setVisible(true)
    }

    fun goToLibResources(topic: Topic) {
        safeLet(topic.id, topic.name) { _id, _name ->
            navController.navigateSafe(
                TopicFragmentDirections.actionTopicFragmentToLibResourceFragment(
                    _id,
                    _name
                )
            )
        }
    }

    private fun onNextPlaceholder(placeholder: Placeholder?) {
        placeholder?.let { binding.includedLoadingPlaceholder.placeholder = it }
    }
}
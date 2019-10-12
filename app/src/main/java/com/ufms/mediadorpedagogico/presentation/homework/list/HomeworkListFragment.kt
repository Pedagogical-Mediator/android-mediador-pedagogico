package com.ufms.mediadorpedagogico.presentation.homework.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.FragmentHomeworkListBinding
import com.ufms.mediadorpedagogico.domain.entity.homework.Homework
import com.ufms.mediadorpedagogico.presentation.util.extensions.observe
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeEvent
import com.ufms.mediadorpedagogico.presentation.util.extensions.setVisible
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseFragment
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.navigateSafe
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import org.koin.android.ext.android.inject

class HomeworkListFragment : BaseFragment() {
    override val toolbarTitle: String
        get() = getString(R.string.activity_homework_label)
    override val baseViewModel: BaseViewModel get() = viewModel

    lateinit var homeworkListAdapter: HomeworkListAdapter
    private var moreHomeworksToBeLoaded = true
    private var isLoadingMoreHomework = false
    lateinit var binding: FragmentHomeworkListBinding
    private val viewModel: HomeworkListViewModel by inject()
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentHomeworkListBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        setupAdapter()
        setupRecycler()
        return binding.root
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            placeholder.observe(this@HomeworkListFragment, ::onNextPlaceholder)
            homeworkContent.observeEvent(this@HomeworkListFragment, ::onHomeworkContentLoaded)
            noContentReturned.observeEvent(this@HomeworkListFragment, ::onNoContentReturned)
        }
    }

    private fun setupAdapter() {
        homeworkListAdapter = HomeworkListAdapter(::setupOnItemClicked)
    }

    private fun setupOnItemClicked(homework: Homework) {
        navController.navigateSafe(
            HomeworkListFragmentDirections
                .actionHomeworkListFragmentToHomeworkDetailsFragment()
                .setHomework(homework)
        )
    }

    private fun setupRecycler() {
        with(binding.recyclerViewHomework) {
            layoutManager = LinearLayoutManager(context)
            adapter = homeworkListAdapter
            addOnScrollListener(setLoadMoreNoticesOnScroll())
        }
    }

    private fun setLoadMoreNoticesOnScroll(): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                with(binding.recyclerViewHomework) {
                    val totalItemCount = layoutManager?.itemCount
                    var lastVisibleItem =
                        (layoutManager as? LinearLayoutManager)?.findLastVisibleItemPosition()
                    lastVisibleItem = lastVisibleItem?.run { this + 1 }
                    if (totalItemCount == lastVisibleItem && moreHomeworksToBeLoaded && !isLoadingMoreHomework) {
                        isLoadingMoreHomework = true
                        viewModel.loadMoreHomework()
                    }
                }
            }
        }
    }

    private fun onHomeworkContentLoaded(homeworkContent: List<Homework>?) {
        homeworkContent?.run(homeworkListAdapter::setItems)
        isLoadingMoreHomework = false
    }

    fun onNoContentReturned(noContentReturned: Boolean?) {
        noContentReturned?.let {
            if (homeworkListAdapter.itemCount == 0) {
                binding.includedPlaceholderNoResults.root.setVisible(true)
            } else {
                moreHomeworksToBeLoaded = false
            }
        }
    }

    private fun onNextPlaceholder(placeholder: Placeholder?) {
        placeholder?.let { binding.placeholder = it }
    }
}
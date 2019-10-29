package com.ufms.mediadorpedagogico.presentation.homework.list

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
import com.ufms.mediadorpedagogico.presentation.util.extensions.*
import com.ufms.mediadorpedagogico.presentation.util.structure.base.Base2
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.navigateSafe
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import org.koin.android.ext.android.inject

class HomeworkListFragment : Base2() {

    override val titleHelp: String get() = getString(R.string.help_homework_list_title)
    override val descriptionHelp: String get() = getString(R.string.help_homework_list_description)
    override val toolbarTitle: String get() = getString(R.string.activity_homework_label)
    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: FragmentHomeworkListBinding
    private var homeworkListAdapter: HomeworkListAdapter? = null
    private var moreHomeworksToBeLoaded = true
    private var isLoadingMoreHomework = false
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
        setupRecyclerView()
        return binding.root
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            placeholder.observeAction(viewLifecycleOwner, ::onNextPlaceholder)
            homeworkContent.observeEvent(viewLifecycleOwner, ::onHomeworkContentLoaded)
            noContentReturned.observeEvent(viewLifecycleOwner, ::onNoContentReturned)
        }
    }

    override fun openHelp() {
        navController.navigateSafe(
            HomeworkListFragmentDirections.actionHomeworkListFragmentToHelpBottomSheet(
                titleHelp,
                descriptionHelp
            )
        )
    }

    private fun setupRecyclerView() {
        homeworkListAdapter.ifNull {
            homeworkListAdapter = HomeworkListAdapter(::setupOnItemClicked)
        }
        with(binding.recyclerViewHomework) {
            adapter.ifNull {
                layoutManager = LinearLayoutManager(context)
                adapter = homeworkListAdapter
                addOnScrollListener(setLoadMoreNoticesOnScroll())
            }
        }
    }

    private fun setupOnItemClicked(homework: Homework) {
        navController.navigateSafe(
            HomeworkListFragmentDirections
                .actionHomeworkListFragmentToHomeworkDetailsFragment()
                .setHomework(homework)
        )
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
        safeLet(homeworkContent, homeworkListAdapter) { homeworks, adapter ->
            adapter.setItems(homeworks)
            isLoadingMoreHomework = false
        }
    }

    fun onNoContentReturned(noContentReturned: Boolean?) {
        noContentReturned?.let {
            if (homeworkListAdapter?.itemCount == 0) {
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
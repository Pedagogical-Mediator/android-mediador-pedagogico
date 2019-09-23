package com.ufms.mediadorpedagogico.presentation.homework.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ActivityHomeworkListBinding
import com.ufms.mediadorpedagogico.domain.entity.homework.Homework
import com.ufms.mediadorpedagogico.presentation.util.extensions.observe
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeEvent
import com.ufms.mediadorpedagogico.presentation.util.extensions.setVisible
import com.ufms.mediadorpedagogico.presentation.util.extensions.setupCustomizedToolbar
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseActivity
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import org.koin.android.ext.android.inject

class HomeworkListActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    lateinit var homeworkListAdapter: HomeworkListAdapter
    private var moreHomeworksToBeLoaded = true
    private var isLoadingMoreHomework = false
    lateinit var binding: ActivityHomeworkListBinding
    private val viewModel: HomeworkListViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_homework_list)
        setupCustomizedToolbar(
            binding.toolbarCustomized,
            true,
            getString(R.string.activity_homework_label)
        )
        lifecycle.addObserver(viewModel)
        setupUi()
        setupAdapter()
        setupRecycler()
        super.onCreate(savedInstanceState)
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            placeholder.observe(this@HomeworkListActivity, ::onNextPlaceholder)
            homeworkContent.observeEvent(this@HomeworkListActivity, ::onHomeworkContentLoaded)
            noContentReturned.observeEvent(this@HomeworkListActivity, ::onNoContentReturned)
        }
    }

    private fun setupAdapter() {
        homeworkListAdapter = HomeworkListAdapter(viewModel::setupOnItemClicked)
    }

    private fun setupRecycler() {
        with(binding.recyclerViewHomework) {
            layoutManager = LinearLayoutManager(this@HomeworkListActivity)
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

    private fun setupUi() {
        //TODO botar click
    }

    private fun onNextPlaceholder(placeholder: Placeholder?) {
        placeholder?.let { binding.placeholder = it }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, HomeworkListActivity::class.java)
        }
    }
}
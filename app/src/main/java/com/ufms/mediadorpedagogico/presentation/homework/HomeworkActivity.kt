package com.ufms.mediadorpedagogico.presentation.homework

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ActivityHomeworkBinding
import com.ufms.mediadorpedagogico.databinding.ActivityRegisterBinding
import com.ufms.mediadorpedagogico.domain.entity.Homework
import com.ufms.mediadorpedagogico.presentation.structure.base.BaseActivity
import com.ufms.mediadorpedagogico.presentation.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.extensions.observe
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeEvent
import com.ufms.mediadorpedagogico.presentation.util.extensions.setVisible
import com.ufms.mediadorpedagogico.presentation.util.extensions.setupToolbar
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import io.reactivex.disposables.Disposable
import org.koin.android.ext.android.inject

class HomeworkActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    private lateinit var binding: ActivityHomeworkBinding
    lateinit var homeworkAdapter: HomeworkAdapter
    private val viewModel: HomeworkViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_homework)
        lifecycle.addObserver(viewModel)
        setupUi()
        setupAdapter()
        setupRecycler()
        super.onCreate(savedInstanceState)
    }

    override fun subscribeUi() {
        super.subscribeUi()
        viewModel.placeholder.observe(this, ::onNextPlaceholder)
        viewModel.homeworkContent.observeEvent(this, ::onHomeworkContentLoaded)
        viewModel.noContentReturned.observeEvent(this, ::onNoContentReturned)
    }

    private fun setupAdapter() {
        homeworkAdapter = HomeworkAdapter(viewModel::setupOnItemClicked)
    }

    private fun setupRecycler() {
        with(binding.recyclerViewHomework) {
            layoutManager = LinearLayoutManager(this@HomeworkActivity)
            adapter = homeworkAdapter
            addOnScrollListener(setLoadMoreNoticesOnScroll())
        }
    }

    private fun setLoadMoreNoticesOnScroll(): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                with(binding.recyclerViewHomework) {
                    val totalItemCount = layoutManager?.itemCount
                    var lastVisibleItem = (layoutManager as? LinearLayoutManager)?.findLastVisibleItemPosition()
                    lastVisibleItem = lastVisibleItem?.run { this + 1}
                    if (totalItemCount == lastVisibleItem) {
                        viewModel.loadMoreHomework()
                    }
                }
            }
        }

    }

    private fun onHomeworkContentLoaded(homeworkContent: List<Homework>?) {
        homeworkContent?.run(homeworkAdapter::setItems)
    }

    private fun onNoContentReturned(noContentReturned: Boolean?) {
        noContentReturned?.let {
            binding.includedPlaceholderNoResults.root.setVisible(true)
        }
    }

    private fun setupUi() {
        with(binding) {
        }
    }

    private fun onNextPlaceholder(placeholder: Placeholder?) {
//        placeholder?.let { binding.includedLoading.placeholder = it }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, HomeworkActivity::class.java)
        }
    }
}
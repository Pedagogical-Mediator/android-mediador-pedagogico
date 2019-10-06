package com.ufms.mediadorpedagogico.presentation.notice.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.FragmentNoticeListBinding
import com.ufms.mediadorpedagogico.domain.entity.notice.Notice
import com.ufms.mediadorpedagogico.presentation.main.MainViewModel
import com.ufms.mediadorpedagogico.presentation.main.dashboard.DashboardActivity
import com.ufms.mediadorpedagogico.presentation.util.extensions.observe
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeEvent
import com.ufms.mediadorpedagogico.presentation.util.extensions.setVisible
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseFragment
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import org.koin.android.ext.android.inject

class NoticeListFragment : BaseFragment() {
    override val toolbarTitle: String
        get() = getString(R.string.activity_notice_label)
    override val baseViewModel: BaseViewModel
        get() = viewModel

    lateinit var noticeListAdapter: NoticeListAdapter
    private var moreNoticesToBeLoaded = true
    private var isLoadingMoreNotice = false
    lateinit var binding: FragmentNoticeListBinding
    private val viewModel: NoticeListViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentNoticeListBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        setupAdapter()
        setupRecycler()
        subscribeUi()
        return binding.root
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            placeholder.observe(this@NoticeListFragment, ::onNextPlaceholder)
            noticeContent.observeEvent(this@NoticeListFragment, ::onNoticeContentLoaded)
            noContentReturned.observeEvent(this@NoticeListFragment, ::onNoContentReturned)
        }
    }

    private fun setupAdapter() {
        noticeListAdapter = NoticeListAdapter(viewModel::setupOnItemClicked)
    }

    private fun setupRecycler() {
        with(binding.recyclerViewNotice) {
            layoutManager = LinearLayoutManager(this@NoticeListFragment.context)
            adapter = noticeListAdapter
            addOnScrollListener(setLoadMoreNoticesOnScroll())
        }
    }

    private fun setLoadMoreNoticesOnScroll(): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                with(binding.recyclerViewNotice) {
                    val totalItemCount = layoutManager?.itemCount
                    var lastVisibleItem =
                        (layoutManager as? LinearLayoutManager)?.findLastVisibleItemPosition()
                    lastVisibleItem = lastVisibleItem?.run { this + 1 }
                    if (totalItemCount == lastVisibleItem && moreNoticesToBeLoaded && !isLoadingMoreNotice) {
                        isLoadingMoreNotice = true
                        viewModel.loadMoreNotice()
                    }
                }
            }
        }
    }

    private fun onNoticeContentLoaded(noticeContent: List<Notice>?) {
        noticeContent?.run(noticeListAdapter::setItems)
        isLoadingMoreNotice = false
    }

    fun onNoContentReturned(noContentReturned: Boolean?) {
        noContentReturned?.let {
            if (noticeListAdapter.itemCount == 0) {
                binding.includedPlaceholderNoResults.root.setVisible(true)
            } else {
                moreNoticesToBeLoaded = false
            }
        }
    }

    private fun onNextPlaceholder(placeholder: Placeholder?) {
        placeholder?.let { binding.placeholder = it }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, NoticeListActivity::class.java)
        }
    }
}
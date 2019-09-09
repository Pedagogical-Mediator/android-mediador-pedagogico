package com.ufms.mediadorpedagogico.presentation.notice.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ActivityNoticeListBinding
import com.ufms.mediadorpedagogico.domain.entity.notice.Notice
import com.ufms.mediadorpedagogico.presentation.util.extensions.observe
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeEvent
import com.ufms.mediadorpedagogico.presentation.util.extensions.setVisible
import com.ufms.mediadorpedagogico.presentation.util.extensions.setupCustomizedToolbar
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseActivity
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import org.koin.android.ext.android.inject

class NoticeListActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    lateinit var noticeListAdapter: NoticeListAdapter
    private var moreNoticesToBeLoaded = true
    private var isLoadingMoreNotice = false
    lateinit var binding: ActivityNoticeListBinding
    private val viewModel: NoticeListViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notice_list)
        setupCustomizedToolbar(binding.toolbarCustomized, true, getString(R.string.activity_notice_label))
        lifecycle.addObserver(viewModel)
        setupUi()
        setupAdapter()
        setupRecycler()
        super.onCreate(savedInstanceState)
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            placeholder.observe(this@NoticeListActivity, ::onNextPlaceholder)
            noticeContent.observeEvent(this@NoticeListActivity, ::onNoticeContentLoaded)
            noContentReturned.observeEvent(this@NoticeListActivity, ::onNoContentReturned)
        }
    }

    private fun setupAdapter() {
        noticeListAdapter = NoticeListAdapter(viewModel::setupOnItemClicked)
    }

    private fun setupRecycler() {
        with(binding.recyclerViewNotice) {
            layoutManager = LinearLayoutManager(this@NoticeListActivity)
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
                    var lastVisibleItem = (layoutManager as? LinearLayoutManager)?.findLastVisibleItemPosition()
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

    private fun setupUi() {
        //TODO botar click
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
package com.ufms.mediadorpedagogico.presentation.news

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ActivityNewsListBinding
import com.ufms.mediadorpedagogico.domain.entity.news.News
import com.ufms.mediadorpedagogico.presentation.util.extensions.observe
import com.ufms.mediadorpedagogico.presentation.util.extensions.observeEvent
import com.ufms.mediadorpedagogico.presentation.util.extensions.setVisible
import com.ufms.mediadorpedagogico.presentation.util.extensions.setupCustomizedToolbar
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseActivity
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import org.koin.android.ext.android.inject

class NewsListActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel

    lateinit var newsListAdapter: NewsListAdapter
    private var moreNewsToBeLoaded = true
    private var isLoadingMoreNews = false
    lateinit var binding: ActivityNewsListBinding
    private val viewModel: NewsListViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_list)
        setupCustomizedToolbar(
            binding.toolbarCustomized,
            true,
            getString(R.string.activity_news_label)
        )
        lifecycle.addObserver(viewModel)
        setupAdapter()
        setupRecycler()
        super.onCreate(savedInstanceState)
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            placeholder.observe(this@NewsListActivity, ::onNextPlaceholder)
            newsContent.observeEvent(this@NewsListActivity, ::onNewsContentLoaded)
            noContentReturned.observeEvent(this@NewsListActivity, ::onNoContentReturned)
        }
    }

    private fun setupAdapter() {
        newsListAdapter = NewsListAdapter(::setupOnItemClicked)
    }

    private fun setupOnItemClicked(news: News) {
        var url = news.link ?: ""
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://$url"
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    private fun setupRecycler() {
        with(binding.recyclerViewNews) {
            layoutManager = LinearLayoutManager(this@NewsListActivity)
            adapter = newsListAdapter
            addOnScrollListener(setLoadMoreNewsOnScroll())
        }
    }

    private fun setLoadMoreNewsOnScroll(): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                with(binding.recyclerViewNews) {
                    val totalItemCount = layoutManager?.itemCount
                    var lastVisibleItem =
                        (layoutManager as? LinearLayoutManager)?.findLastVisibleItemPosition()
                    lastVisibleItem = lastVisibleItem?.run { this + 1 }
                    if (totalItemCount == lastVisibleItem && moreNewsToBeLoaded && !isLoadingMoreNews) {
                        isLoadingMoreNews = true
                        viewModel.loadMoreNews()
                    }
                }
            }
        }
    }

    private fun onNewsContentLoaded(newsContent: List<News>?) {
        newsContent?.run(newsListAdapter::setItems)
        isLoadingMoreNews = false
    }

    fun onNoContentReturned(noContentReturned: Boolean?) {
        noContentReturned?.let {
            if (newsListAdapter.itemCount == 0) {
                binding.includedPlaceholderNoResults.root.setVisible(true)
            } else {
                moreNewsToBeLoaded = false
            }
        }
    }

    private fun onNextPlaceholder(placeholder: Placeholder?) {
        placeholder?.let { binding.placeholder = it }
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, NewsListActivity::class.java)
        }
    }
}
package com.ufms.mediadorpedagogico.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.FragmentNewsListBinding
import com.ufms.mediadorpedagogico.domain.entity.news.News
import com.ufms.mediadorpedagogico.presentation.util.extensions.*
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseFragment
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.structure.navigation.navigateSafe
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import org.koin.android.ext.android.inject

class NewsListFragment : BaseFragment() {

    override val titleHelp: String get() = getString(R.string.help_news_list_title)
    override val descriptionHelp: String get() = getString(R.string.help_news_list_description)
    override val toolbarTitle: String get() = getString(R.string.activity_news_label)
    override val baseViewModel: BaseViewModel get() = viewModel

    var newsListAdapter: NewsListAdapter? = null
    private var moreNewsToBeLoaded = true
    private var isLoadingMoreNews = false
    private lateinit var binding: FragmentNewsListBinding
    private val viewModel: NewsListViewModel by inject()
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    override fun subscribeUi() {
        super.subscribeUi()
        with(viewModel) {
            placeholder.observeAction(viewLifecycleOwner, ::onNextPlaceholder)
            newsContent.observeEvent(viewLifecycleOwner, ::onNewsContentLoaded)
            noContentReturned.observeEvent(viewLifecycleOwner, ::onNoContentReturned)
        }
    }

    override fun openHelp() {
        navController.navigateSafe(
            NewsListFragmentDirections.actionNewsListFragmentToHelpBottomSheet(
                titleHelp,
                descriptionHelp
            )
        )
    }

    private fun setupRecyclerView() {
        newsListAdapter.ifNull {
            newsListAdapter = NewsListAdapter(::setupOnItemClicked)
        }
        with(binding.recyclerViewNews) {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(context)
                adapter = newsListAdapter
                addOnScrollListener(setLoadMoreNewsOnScroll())
            }
        }
    }

    private fun setupOnItemClicked(news: News) {
        loadPage(news.link)
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
        safeLet(newsContent, newsListAdapter) { news, adapter ->
            adapter.setItems(news)
            isLoadingMoreNews = false
        }
    }

    fun onNoContentReturned(noContentReturned: Boolean?) {
        noContentReturned?.let {
            if (newsListAdapter?.itemCount == 0) {
                binding.includedPlaceholderNoResults.root.setVisible(true)
            } else {
                moreNewsToBeLoaded = false
            }
        }
    }

    private fun onNextPlaceholder(placeholder: Placeholder?) {
        placeholder?.let { binding.placeholder = it }
    }
}
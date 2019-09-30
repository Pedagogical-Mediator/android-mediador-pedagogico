package com.ufms.mediadorpedagogico.presentation.news

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.domain.entity.news.News

class NewsListAdapter(
    private val onItemClickedCallback: (News) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var newsList: ArrayList<News> = arrayListOf()
    val listGet get() = newsList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        NewsListViewHolder.inflate(
            parent,
            ::onItemClicked
        )

    override fun getItemCount() = newsList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val news = newsList[position]
        (holder as? NewsListViewHolder)?.setupBinding(news)
    }

    fun setItems(newsList: List<News>) {
        this.newsList.addAll(newsList)
        notifyDataSetChanged()
    }

    fun removeAll() {
        this.newsList = arrayListOf()
    }

    private fun onItemClicked(news: News?) {
        news?.let {
            onItemClickedCallback.invoke(it)
        }
    }
}
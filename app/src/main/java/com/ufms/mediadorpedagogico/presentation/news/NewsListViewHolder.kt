package com.ufms.mediadorpedagogico.presentation.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ItemListResourceBinding
import com.ufms.mediadorpedagogico.domain.entity.news.News
import com.ufms.mediadorpedagogico.domain.util.removeHtmlTags
import com.ufms.mediadorpedagogico.domain.util.safeSlice

class NewsListViewHolder(
    private var binding: ItemListResourceBinding,
    private val onItemClickedCallback: (News) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(news: News) {
        with(binding) {
            news.run {
                textViewTitle.text = title
                textViewDescription.text = description.removeHtmlTags()
                textViewDate.text = createdAt
                constraintLayoutItem.setOnClickListener { onItemClickedCallback.invoke(this) }
            }
        }
        with(binding) {
            news.description.removeHtmlTags().let {
                textViewDescription.text = it.safeSlice(0, 50)
            }
            news.title?.let {
                textViewTitle.text = it.safeSlice(0, 50)
            }
        }
    }

    companion object {
        fun inflate(parent: ViewGroup?, onItemClickedCallback: (News) -> Unit) =
            NewsListViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent?.context),
                    R.layout.item_list_resource,
                    parent,
                    false
                ), onItemClickedCallback
            )
    }
}
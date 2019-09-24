package com.ufms.mediadorpedagogico.presentation.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ItemListResourceBinding
import com.ufms.mediadorpedagogico.domain.entity.news.News

class NewsListViewHolder(
    private var binding: ItemListResourceBinding,
    private val onItemClickedCallback: (News) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(news: News) {
        with(binding) {
            news.run {
                textViewTitle.text = title
                textViewDescription.text = description
                textViewDate.text = createdAt
                constraintLayoutItem.setOnClickListener { onItemClickedCallback.invoke(this) }
            }
        }
        with(binding) {
            news.description?.let {
                if (it.length > 50) {
                    textViewDescription.text =
                        root.context.getString(
                            R.string.summarize_three_dots_template,
                            it.slice(0..50)
                        )
                }
            }
            news.title?.let {
                if (it.length > 50) {
                    textViewTitle.text =
                        root.context.getString(
                            R.string.summarize_three_dots_template,
                            it.slice(0..50)
                        )
                }
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
package com.ufms.mediadorpedagogico.presentation.notice.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ItemListResourceBinding
import com.ufms.mediadorpedagogico.domain.entity.notice.Notice

class NoticeListViewHolder(
    private var binding: ItemListResourceBinding,
    private val onItemClickedCallback: (Notice) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(notice: Notice) {
        with(binding) {
            notice.run {
                textViewTitle.text = title
                textViewDescription.text = description
                textViewDate.text = createdAt
                constraintLayoutItem.setOnClickListener { onItemClickedCallback.invoke(this) }
            }
        }
        with(binding) {
            notice.description?.let {
                if (it.length > 50) {
                    textViewDescription.text =
                        root.context.getString(R.string.summarize_three_dots_template, it.slice(0..50))
                }
            }
            notice.title?.let {
                if (it.length > 50) {
                    textViewTitle.text =
                        root.context.getString(R.string.summarize_three_dots_template, it.slice(0..50))
                }
            }
        }
    }

    companion object {
        fun inflate(parent: ViewGroup?, onItemClickedCallback: (Notice) -> Unit) =
            NoticeListViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent?.context),
                    R.layout.item_list_resource,
                    parent,
                    false
                ), onItemClickedCallback
            )
    }
}
package com.ufms.mediadorpedagogico.presentation.homework.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ItemListHomeworkDetailsBinding
import com.ufms.mediadorpedagogico.domain.entity.HomeworkLink

class HomeworkDetailsViewHolder(
        private var binding: ItemListHomeworkDetailsBinding
)
    : RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(link: HomeworkLink) {
        with(binding) {
            homework.run {
                textViewTitle.text = title
                textViewDescription.text = description
                textViewDate.text = createdAt
                constraintLayoutItem.setOnClickListener { onItemClickedCallback.invoke(this) }
            }
        }
        with(binding) {
            homework.description?.let {
                if (it.length > 50) {
                    textViewDescription.text = root.context.getString(R.string.activity_homework_summarize_template, it.slice(0..50))
                }
            }
            homework.title?.let {
                if (it.length > 50) {
                    textViewTitle.text = root.context.getString(R.string.activity_homework_summarize_template, it.slice(0..50))
                }
            }
        }
    }

    companion object {
        fun inflate(parent: ViewGroup?) =
                HomeworkDetailsViewHolder(
                        DataBindingUtil.inflate(
                                LayoutInflater.from(parent?.context),
                                R.layout.item_list_homework,
                                parent,
                                false
                        )
                )
    }
}
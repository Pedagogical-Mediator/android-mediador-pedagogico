package com.ufms.mediadorpedagogico.presentation.homework.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ItemListResourceBinding
import com.ufms.mediadorpedagogico.domain.entity.homework.Homework

class HomeworkListViewHolder(
    private var binding: ItemListResourceBinding,
    private val onItemClickedCallback: (Homework) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(homework: Homework) {
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
                    textViewDescription.text =
                        root.context.getString(
                            R.string.summarize_three_dots_template,
                            it.slice(0..50)
                        )
                }
            }
            homework.title?.let {
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
        fun inflate(parent: ViewGroup?, onItemClickedCallback: (Homework) -> Unit) =
            HomeworkListViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent?.context),
                    R.layout.item_list_resource,
                    parent,
                    false
                ), onItemClickedCallback
            )
    }
}
package com.ufms.mediadorpedagogico.presentation.homework

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ItemListHomeworkBinding
import com.ufms.mediadorpedagogico.domain.entity.Homework
import com.ufms.mediadorpedagogico.presentation.util.extensions.setString

class HomeworkViewHolder(
    private var binding: ItemListHomeworkBinding,
    private val onItemClickedCallback: (Homework) -> Unit)
    : RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(homework: Homework) {
        with(binding) {
            homework.run {
                textViewTitle.text = title
                textViewDescription.text = description
                textViewDate.text = id.toString()
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
        fun inflate(parent: ViewGroup?, onItemClickedCallback: (Homework) -> Unit) = HomeworkViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent?.context),
                R.layout.item_list_homework,
                parent,
                false), onItemClickedCallback
        )
    }
}
package com.ufms.mediadorpedagogico.presentation.homework

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.databinding.ItemListHomeworkBinding

class HomeworkViewHolder(
    private var binding: ItemListHomeworkBinding,
    private val onItemClickedCallback: (Homework) -> Unit)
    : RecyclerView.ViewHolder(binding.root) {

    private fun setupBinding(homework: Homework) {
        with(binding) {
            textViewDescription.text = homework.description
            constraintLayoutItem.setOnClickListener { onItemClickedCallback.invoke(homework) }
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
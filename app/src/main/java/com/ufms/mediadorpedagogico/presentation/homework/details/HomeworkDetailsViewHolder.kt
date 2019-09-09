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
) : RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(link: HomeworkLink) {
        with(binding) {
            link.run {
                textViewLinkName.text = link.name
                textViewLink.text = link.link
            }
        }
    }

    companion object {
        fun inflate(parent: ViewGroup?) =
                HomeworkDetailsViewHolder(
                        DataBindingUtil.inflate(
                                LayoutInflater.from(parent?.context),
                                R.layout.item_list_homework_details,
                                parent,
                                false
                        )
                )
    }
}
package com.ufms.mediadorpedagogico.presentation.homework.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ItemListLinkBinding
import com.ufms.mediadorpedagogico.domain.entity.Link

class HomeworkDetailsViewHolder(
    private var binding: ItemListLinkBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(link: Link) {
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
                    R.layout.item_list_link,
                    parent,
                    false
                )
            )
    }
}
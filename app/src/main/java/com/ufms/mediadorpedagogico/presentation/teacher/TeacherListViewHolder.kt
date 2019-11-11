package com.ufms.mediadorpedagogico.presentation.teacher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ItemListResourceBinding
import com.ufms.mediadorpedagogico.domain.entity.Teacher

class TeacherListViewHolder(
    private var binding: ItemListResourceBinding
) : RecyclerView.ViewHolder(binding.root) {

    // TODO fazer o viewHolder
    fun setupBinding(teacher: Teacher) {
        with(binding) {
            textViewTitle.text = teacher.name
        }
    }

    companion object {
        fun inflate(parent: ViewGroup?) =
            TeacherListViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent?.context),
                    R.layout.item_list_resource,
                    parent,
                    false
                )
            )
    }
}
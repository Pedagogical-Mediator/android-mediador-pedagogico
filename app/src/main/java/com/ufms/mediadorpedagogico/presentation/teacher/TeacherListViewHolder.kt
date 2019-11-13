package com.ufms.mediadorpedagogico.presentation.teacher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.databinding.ItemListResourceBinding
import com.ufms.mediadorpedagogico.databinding.ItemListTeacherBinding
import com.ufms.mediadorpedagogico.domain.entity.Teacher
import com.ufms.mediadorpedagogico.domain.util.removeHtmlTags

class TeacherListViewHolder(
    private var binding: ItemListTeacherBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(teacher: Teacher) {
        with(binding) {
            textViewName.text = teacher.name
            textViewDescription.text = teacher.description.removeHtmlTags()
            textViewSubjects.text = teacher.subjects?.joinToString()
        }
    }

    companion object {
        fun inflate(parent: ViewGroup?) =
            TeacherListViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent?.context),
                    R.layout.item_list_teacher,
                    parent,
                    false
                )
            )
    }
}
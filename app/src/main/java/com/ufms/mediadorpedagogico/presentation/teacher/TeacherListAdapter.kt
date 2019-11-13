package com.ufms.mediadorpedagogico.presentation.teacher

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.domain.entity.Teacher

class TeacherListAdapter : RecyclerView.Adapter<TeacherListViewHolder>() {

    var teachers: ArrayList<Teacher> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherListViewHolder =
        TeacherListViewHolder.inflate(parent)

    override fun getItemCount() = teachers.size

    override fun onBindViewHolder(holder: TeacherListViewHolder, position: Int) {
        holder.setupBinding(teachers[position])
    }

    fun setItems(teachers: List<Teacher>) {
        this.teachers.addAll(teachers)
        notifyDataSetChanged()
    }

    fun removeAll() {
        teachers = arrayListOf()
    }
}
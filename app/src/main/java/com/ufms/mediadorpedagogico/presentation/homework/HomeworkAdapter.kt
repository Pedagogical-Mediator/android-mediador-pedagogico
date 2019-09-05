package com.ufms.mediadorpedagogico.presentation.homework

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.domain.entity.Homework

class HomeworkAdapter(
    private val onItemClickedCallback: (Homework) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var homeworkList: ArrayList<Homework> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        HomeworkViewHolder.inflate(parent, ::onItemClicked)

    override fun getItemCount() = homeworkList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val homework = homeworkList[position]
        (holder as? HomeworkViewHolder)?.setupBinding(homework)
    }

    fun setItems(homeworkList: List<Homework>) {
        this.homeworkList.addAll(homeworkList)
        notifyDataSetChanged()
    }

    private fun onItemClicked(homework: Homework?) {
        homework?.let {
            onItemClickedCallback.invoke(it)
        }
    }
}
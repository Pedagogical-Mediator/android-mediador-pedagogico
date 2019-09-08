package com.ufms.mediadorpedagogico.presentation.homework.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.domain.entity.Homework

class HomeworkListAdapter(
    private val onItemClickedCallback: (Homework) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var homeworkList: ArrayList<Homework> = arrayListOf()
    val listGet get() = homeworkList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        HomeworkListViewHolder.inflate(
            parent,
            ::onItemClicked
        )

    override fun getItemCount() = homeworkList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val homework = homeworkList[position]
        (holder as? HomeworkListViewHolder)?.setupBinding(homework)
    }

    fun setItems(homeworkList: List<Homework>) {
        this.homeworkList.addAll(homeworkList)
        notifyDataSetChanged()
    }

    fun removeAll() {
        this.homeworkList = arrayListOf()
    }

    private fun onItemClicked(homework: Homework?) {
        homework?.let {
            onItemClickedCallback.invoke(it)
        }
    }
}
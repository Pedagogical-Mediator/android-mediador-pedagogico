package com.ufms.mediadorpedagogico.presentation.homework.details

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.domain.entity.HomeworkLink

class HomeworkDetailsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var homeworkLinkList: ArrayList<HomeworkLink> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        HomeworkDetailsViewHolder.inflate(parent)

    override fun getItemCount() = homeworkLinkList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val homeworkLink = homeworkLinkList[position]
        (holder as? HomeworkDetailsViewHolder)?.setupBinding(homeworkLink)
    }

    fun setItems(homeworkLinkList: List<HomeworkLink>?) {
        homeworkLinkList?.let(this.homeworkLinkList::addAll)
        notifyDataSetChanged()
    }
}
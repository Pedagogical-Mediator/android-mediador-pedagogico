package com.ufms.mediadorpedagogico.presentation.homework.details

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.domain.entity.Link

class HomeworkDetailsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var linkList: ArrayList<Link> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        HomeworkDetailsViewHolder.inflate(parent)

    override fun getItemCount() = linkList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val homeworkLink = linkList[position]
        (holder as? HomeworkDetailsViewHolder)?.setupBinding(homeworkLink)
    }

    fun setItems(linkList: List<Link>?) {
        linkList?.let(this.linkList::addAll)
        notifyDataSetChanged()
    }
}
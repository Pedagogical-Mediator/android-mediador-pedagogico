package com.ufms.mediadorpedagogico.presentation.notice.details

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.domain.entity.Link

class NoticeDetailsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var linkList: ArrayList<Link> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        NoticeDetailsViewHolder.inflate(parent)

    override fun getItemCount() = linkList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val noticeLink = linkList[position]
        (holder as? NoticeDetailsViewHolder)?.setupBinding(noticeLink)
    }

    fun setItems(linkList: List<Link>?) {
        linkList?.let(this.linkList::addAll)
        notifyDataSetChanged()
    }
}
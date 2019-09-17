package com.ufms.mediadorpedagogico.presentation.notice.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.domain.entity.notice.Notice

class NoticeListAdapter(
    private val onItemClickedCallback: (Notice) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var noticeList: ArrayList<Notice> = arrayListOf()
    val listGet get() = noticeList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        NoticeListViewHolder.inflate(
            parent,
            ::onItemClicked
        )

    override fun getItemCount() = noticeList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val notice = noticeList[position]
        (holder as? NoticeListViewHolder)?.setupBinding(notice)
    }

    fun setItems(noticeList: List<Notice>) {
        this.noticeList.addAll(noticeList)
        notifyDataSetChanged()
    }

    fun removeAll() {
        this.noticeList = arrayListOf()
    }

    private fun onItemClicked(notice: Notice?) {
        notice?.let {
            onItemClickedCallback.invoke(it)
        }
    }
}
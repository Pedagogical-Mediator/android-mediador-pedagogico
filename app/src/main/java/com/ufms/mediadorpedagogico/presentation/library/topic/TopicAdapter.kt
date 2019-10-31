package com.ufms.mediadorpedagogico.presentation.library.topic

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.domain.entity.Topic

class TopicAdapter(
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<TopicViewHolder>() {

    private var topics = listOf<Topic>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        return TopicViewHolder.inflate(parent, onItemClick)
    }

    override fun getItemCount(): Int {
        return topics.size
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.setupBinding(topics[position])
    }

    internal fun setItems(topics: List<Topic>) {
        this.topics = topics
        notifyDataSetChanged()
    }
}
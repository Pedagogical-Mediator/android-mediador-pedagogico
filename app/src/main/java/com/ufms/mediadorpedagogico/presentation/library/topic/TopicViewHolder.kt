package com.ufms.mediadorpedagogico.presentation.library.topic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.databinding.ItemListTopicBinding
import com.ufms.mediadorpedagogico.domain.entity.Topic

class TopicViewHolder(
    private val binding: ItemListTopicBinding,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(topic: Topic) {
        binding.topic = topic
        binding.root.setOnClickListener {
            topic.id?.run(onItemClick)
        }
    }

    companion object {
        fun inflate(
            parent: ViewGroup,
            onItemClick: (Int) -> Unit
        ): TopicViewHolder {
            return TopicViewHolder(
                ItemListTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                onItemClick
            )
        }
    }
}
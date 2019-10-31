package com.ufms.mediadorpedagogico.presentation.library.libresource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.databinding.ItemListResourceBinding
import com.ufms.mediadorpedagogico.databinding.ItemListTopicBinding
import com.ufms.mediadorpedagogico.domain.entity.LibResource
import com.ufms.mediadorpedagogico.domain.entity.Topic

class LibResourceViewHolder(
    private val binding: ItemListResourceBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(libResource: LibResource) {
        // TODO criar layout nome e link e passar o binding
//        binding.libResource = libResource
    }

    companion object {
        fun inflate(
            parent: ViewGroup
        ): LibResourceViewHolder {
            return LibResourceViewHolder(
                ItemListResourceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }
}
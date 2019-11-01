package com.ufms.mediadorpedagogico.presentation.library.libresource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.databinding.ItemListLibResourceBinding
import com.ufms.mediadorpedagogico.domain.entity.LibResource

class LibResourceViewHolder(
    private val binding: ItemListLibResourceBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(libResource: LibResource) {
        binding.libResource = libResource
    }

    companion object {
        fun inflate(
            parent: ViewGroup
        ): LibResourceViewHolder {
            return LibResourceViewHolder(
                ItemListLibResourceBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}
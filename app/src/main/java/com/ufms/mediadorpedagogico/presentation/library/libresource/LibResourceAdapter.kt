package com.ufms.mediadorpedagogico.presentation.library.libresource

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufms.mediadorpedagogico.domain.entity.library.LibResource

class LibResourceAdapter : RecyclerView.Adapter<LibResourceViewHolder>() {

    private var libResources = listOf<LibResource>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibResourceViewHolder {
        return LibResourceViewHolder.inflate(parent)
    }

    override fun getItemCount(): Int {
        return libResources.size
    }

    override fun onBindViewHolder(holder: LibResourceViewHolder, position: Int) {
        holder.setupBinding(libResources[position])
    }

    internal fun setItems(libResources: List<LibResource>) {
        this.libResources = libResources
        notifyDataSetChanged()
    }
}
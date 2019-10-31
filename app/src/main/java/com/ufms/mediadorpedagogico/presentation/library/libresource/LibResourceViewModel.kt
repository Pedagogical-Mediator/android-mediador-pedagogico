package com.ufms.mediadorpedagogico.presentation.library.libresource

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.presentation.library.delegate.LibraryDelegate
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel

class LibResourceViewModel(
    private val id: Int,
    libraryDelegate: LibraryDelegate
) : BaseViewModel(), LibraryDelegate by libraryDelegate {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        super.onCreate()
        getLibResources(id, {}, {}, ::setPlaceholder)
    }
}
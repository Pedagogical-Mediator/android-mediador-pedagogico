package com.ufms.mediadorpedagogico.presentation.library.topic

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.presentation.library.delegate.LibraryDelegate
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel

class TopicViewModel(
    libraryDelegate: LibraryDelegate
) : BaseViewModel(), LibraryDelegate by libraryDelegate {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        super.onCreate()
        getTopics({}, {}, ::setPlaceholder)
    }
}
package com.ufms.mediadorpedagogico.presentation.about

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.presentation.about.delegate.AboutDelegate
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel

class AboutViewModel constructor(
    aboutDelegate: AboutDelegate
) : BaseViewModel(), AboutDelegate by aboutDelegate {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        super.onCreate()
        getAbout({}, {}, ::setPlaceholder)
    }
}
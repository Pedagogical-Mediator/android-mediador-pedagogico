package com.ufms.mediadorpedagogico.presentation.settings

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.domain.entity.notice.Notice
import com.ufms.mediadorpedagogico.presentation.util.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel

class SettingsViewModel() : BaseViewModel() {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {

    }
}
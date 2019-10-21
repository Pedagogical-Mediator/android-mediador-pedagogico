package com.ufms.mediadorpedagogico.presentation.notice.details

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.domain.entity.notice.Notice
import com.ufms.mediadorpedagogico.presentation.util.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel

class NoticeDetailsViewModel(
    private val notice: Notice
) : BaseViewModel() {

    val noticeContent: LiveData<Event<Notice>> get() = _noticeContent

    private val _noticeContent: MutableLiveData<Event<Notice>> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        super.onCreate()
        _noticeContent.value = Event(notice)
    }
}
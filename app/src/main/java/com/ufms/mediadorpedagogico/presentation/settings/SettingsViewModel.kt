package com.ufms.mediadorpedagogico.presentation.settings

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.domain.interactor.notification.ManageNews
import com.ufms.mediadorpedagogico.domain.interactor.notification.ManageNotices
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel

class SettingsViewModel(
    private val manageNotices: ManageNotices,
    private val manageNews: ManageNews
) : BaseViewModel() {

    val subscribedNotices: LiveData<Boolean> get() = _subscribedNotices
    val subscribedNews: LiveData<Boolean> get() = _subscribedNews

    private val _subscribedNotices: MutableLiveData<Boolean> = MutableLiveData()
    private val _subscribedNews: MutableLiveData<Boolean> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        _subscribedNotices.value = manageNotices.isSubscribed()
        _subscribedNews.value = manageNews.isSubscribed()
    }
}
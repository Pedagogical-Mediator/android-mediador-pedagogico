package com.ufms.mediadorpedagogico.presentation.settings

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.data.firebase.FirebaseMessagingServiceHandler.Companion.KEY_TOPIC_NEWS
import com.ufms.mediadorpedagogico.data.firebase.FirebaseMessagingServiceHandler.Companion.KEY_TOPIC_NOTICES
import com.ufms.mediadorpedagogico.data.firebase.FirebaseMessagingServiceHandler.Companion.KEY_TOPIC_TOKEN
import com.ufms.mediadorpedagogico.domain.boundary.resources.Cache
import com.ufms.mediadorpedagogico.domain.interactor.notification.ManageNews
import com.ufms.mediadorpedagogico.domain.interactor.notification.ManageNotices
import com.ufms.mediadorpedagogico.domain.util.subscriberHandler
import com.ufms.mediadorpedagogico.presentation.login.LoginNavData
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel

class SettingsViewModel(
    private val manageNotices: ManageNotices,
    private val manageNews: ManageNews,
    private val cache: Cache
) : BaseViewModel() {

    val subscribedNotices: LiveData<Boolean> get() = _subscribedNotices
    val subscribedNews: LiveData<Boolean> get() = _subscribedNews
    val logout: LiveData<Boolean> get() = _logout

    private val _subscribedNotices: MutableLiveData<Boolean> = MutableLiveData()
    private val _subscribedNews: MutableLiveData<Boolean> = MutableLiveData()
    private val _logout: MutableLiveData<Boolean> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        super.onCreate()
        _subscribedNotices.value = manageNotices.isSubscribed()
        _subscribedNews.value = manageNews.isSubscribed()
    }

    fun onNewsSwitch(check: Boolean) {
        subscriberHandler(KEY_TOPIC_NEWS, check)
        manageNews.subscribe(check)
    }

    fun onNoticesSwitch(check: Boolean) {
        subscriberHandler(KEY_TOPIC_NOTICES, check)
        manageNotices.subscribe(check)
    }
}
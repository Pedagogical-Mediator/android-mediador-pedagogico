package com.ufms.mediadorpedagogico.presentation.main

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.data.firebase.FirebaseMessagingServiceHandler
import com.ufms.mediadorpedagogico.domain.interactor.notification.ManageNews
import com.ufms.mediadorpedagogico.domain.interactor.notification.ManageNotices
import com.ufms.mediadorpedagogico.domain.interactor.user.InvalidFieldsException
import com.ufms.mediadorpedagogico.domain.util.subscriberHandler
import com.ufms.mediadorpedagogico.presentation.util.extensions.unsafeLet
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import com.ufms.mediadorpedagogico.presentation.util.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel

class MainViewModel(
    private val schedulerProvider: SchedulerProvider,
    private val manageNotices: ManageNotices,
    private val manageNews: ManageNews
) : BaseViewModel() {

    val errors: LiveData<Event<InvalidFieldsException>> get() = _errors
    val noContentReturned: LiveData<Event<Boolean>> get() = _noContentReturned

    private val _errors: MutableLiveData<Event<InvalidFieldsException>> = MutableLiveData()
    private val _noContentReturned: MutableLiveData<Event<Boolean>> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        //TODO buscar dados da escola e turma
        subscribeToTopics()
    }

    private fun subscribeToTopics() {
        unsafeLet(manageNews.isSubscribed(), manageNotices.isSubscribed()) {
            manageNews.subscribe(true)
            manageNotices.subscribe(true)
            subscriberHandler(FirebaseMessagingServiceHandler.KEY_TOPIC_NOTICES)
            subscriberHandler(FirebaseMessagingServiceHandler.KEY_TOPIC_NEWS)
        }
    }

    private fun onFailure(throwable: Throwable) {
        setDialog(throwable)
        _noContentReturned.value = Event(true)
    }
}
package com.ufms.mediadorpedagogico.presentation.main

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.domain.interactor.bullying.GetBullying
import com.ufms.mediadorpedagogico.domain.interactor.user.InvalidFieldsException
import com.ufms.mediadorpedagogico.presentation.bullying.BullyingNavData
import com.ufms.mediadorpedagogico.presentation.homework.list.HomeworkListNavData
import com.ufms.mediadorpedagogico.presentation.news.NewsListNavData
import com.ufms.mediadorpedagogico.presentation.notice.list.NoticeListNavData
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import com.ufms.mediadorpedagogico.presentation.util.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel

class MainViewModel(
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val errors: LiveData<Event<InvalidFieldsException>> get() = _errors
    val noContentReturned: LiveData<Event<Boolean>> get() = _noContentReturned

    private val _errors: MutableLiveData<Event<InvalidFieldsException>> = MutableLiveData()
    private val _noContentReturned: MutableLiveData<Event<Boolean>> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        //TODO buscar dados da escola e turma
    }

    private fun onFailure(throwable: Throwable) {
        setDialog(throwable)
        _noContentReturned.value = Event(true)
    }

    fun goToHomework() {
        goTo(HomeworkListNavData())
    }

    fun goToNotice() {
        goTo(NoticeListNavData())
    }

    fun goToNews() {
        goTo(NewsListNavData())
    }

    fun goToBullying() {
        goTo(BullyingNavData())
    }
}
package com.ufms.mediadorpedagogico.presentation.homework.details

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.domain.entity.Homework
import com.ufms.mediadorpedagogico.domain.entity.HomeworkContent
import com.ufms.mediadorpedagogico.domain.extensions.defaultSched
import com.ufms.mediadorpedagogico.domain.interactor.homework.GetHomework
import com.ufms.mediadorpedagogico.domain.interactor.user.InvalidFieldsException
import com.ufms.mediadorpedagogico.presentation.util.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.extensions.defaultPlaceholders
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import io.reactivex.rxkotlin.subscribeBy

class HomeworkDetailsViewModel(
    private val schedulerProvider: SchedulerProvider,
    private val homework: Homework
) : BaseViewModel() {

    val homeworkContent: LiveData<Event<Homework>> get() = _homeworkContent

    private val _homeworkContent: MutableLiveData<Event<Homework>> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        _homeworkContent.value = Event(homework)
    }

    fun setupOnItemClicked(homework: Homework) {
        //TODO GOTO details passando como bundle o homework
    }

    private fun onFailure(throwable: Throwable) {
        setDialog(throwable)
    }
}
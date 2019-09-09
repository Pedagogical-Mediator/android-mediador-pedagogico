package com.ufms.mediadorpedagogico.presentation.homework.details

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.domain.entity.homework.Homework
import com.ufms.mediadorpedagogico.presentation.util.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel

class HomeworkDetailsViewModel(
    private val homework: Homework
) : BaseViewModel() {

    val homeworkContent: LiveData<Event<Homework>> get() = _homeworkContent

    private val _homeworkContent: MutableLiveData<Event<Homework>> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        _homeworkContent.value = Event(homework)
    }
}
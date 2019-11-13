package com.ufms.mediadorpedagogico.presentation.teacher

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.domain.entity.Teacher
import com.ufms.mediadorpedagogico.domain.extensions.defaultSched
import com.ufms.mediadorpedagogico.domain.interactor.teacher.GetTeacher
import com.ufms.mediadorpedagogico.domain.interactor.user.InvalidFieldsException
import com.ufms.mediadorpedagogico.presentation.util.extensions.defaultPlaceholders
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import com.ufms.mediadorpedagogico.presentation.util.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy

class TeacherListViewModel(
    private val schedulerProvider: SchedulerProvider,
    private val getTeacher: GetTeacher
) : BaseViewModel() {

    val errors: LiveData<Event<InvalidFieldsException>> get() = _errors
    val teachers: LiveData<Event<List<Teacher>>> get() = _teachers
    val noContentReturned: LiveData<Event<Boolean>> get() = _noContentReturned

    private val _errors: MutableLiveData<Event<InvalidFieldsException>> = MutableLiveData()
    private val _teachers: MutableLiveData<Event<List<Teacher>>> = MutableLiveData()
    private val _noContentReturned: MutableLiveData<Event<Boolean>> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        super.onCreate()
        getTeachers()
    }

    private fun getTeachers() {
        getTeacher
            .executeList()
            .defaultSched(schedulerProvider)
            .defaultPlaceholders(::setPlaceholder)
            .subscribeBy(::onFailure) {
                _teachers.value = Event(it)
            }
    }

    private fun onFailure(throwable: Throwable) {
        setDialog(throwable)
        _noContentReturned.value = Event(true)
    }

    private fun onSuccess(teachers: List<Teacher>) {
        with(teachers) {
            if (isEmpty()) {
                _noContentReturned.value = Event(true)
            } else {
                _teachers.value = Event(this)
            }
        }
    }
}
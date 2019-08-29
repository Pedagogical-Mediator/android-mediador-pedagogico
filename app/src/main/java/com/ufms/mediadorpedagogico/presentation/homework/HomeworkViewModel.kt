package com.ufms.mediadorpedagogico.presentation.homework

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.domain.entity.Homework
import com.ufms.mediadorpedagogico.domain.entity.HomeworkContent
import com.ufms.mediadorpedagogico.domain.extensions.defaultSched
import com.ufms.mediadorpedagogico.domain.interactor.homework.GetHomework
import com.ufms.mediadorpedagogico.domain.interactor.user.InvalidFieldsException
import com.ufms.mediadorpedagogico.presentation.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.extensions.defaultPlaceholders
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import io.reactivex.rxkotlin.subscribeBy

class HomeworkViewModel(
    private val schedulerProvider: SchedulerProvider,
    private val getHomework: GetHomework
) : BaseViewModel() {

    val errors: LiveData<Event<InvalidFieldsException>> get() = errorsLiveData
    val goToHomeworkdDetails: LiveData<Boolean> get() = goToHomeworkdDetailsLiveData

    private val errorsLiveData: MutableLiveData<Event<InvalidFieldsException>> = MutableLiveData()
    private val goToHomeworkdDetailsLiveData: MutableLiveData<Boolean> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        getHomework.execute(0)
            .defaultPlaceholders(this::setPlaceholder)
            .defaultSched(schedulerProvider)
            .subscribeBy(this::onFailure, this::onSuccess)
    }

    fun setupOnItemClicked(homework: Homework) {
        //TODO GOTO details passando como bundle o homework
    }

    private fun onFailure(throwable: Throwable) {
        setDialog(throwable)
    }

    private fun onSuccess(content: HomeworkContent) {
        val a= ""
        // TODO atualiaz rlive data
    }
}
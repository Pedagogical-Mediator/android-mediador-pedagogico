package com.ufms.mediadorpedagogico.presentation.homework

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ufms.mediadorpedagogico.domain.interactor.user.InvalidFieldsException
import com.ufms.mediadorpedagogico.presentation.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.structure.base.BaseViewModel
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider

class HomeworkViewModel(
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val errors: LiveData<Event<InvalidFieldsException>> get() = errorsLiveData
    val goToHomeworkdDetails: LiveData<Boolean> get() = goToHomeworkdDetailsLiveData

    private val errorsLiveData: MutableLiveData<Event<InvalidFieldsException>> = MutableLiveData()
    private val goToHomeworkdDetailsLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun setupOnItemClicked(homework: Homework) {
        //TODO GOTO details passando como bundle o homework
    }

    private fun onFailure(throwable: Throwable) {
        setDialog(throwable)
    }
}
package com.ufms.mediadorpedagogico.presentation.bullying

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel

class BullyingViewModel(
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        //TODO buscar dados da escola e turma
    }
}
package com.ufms.mediadorpedagogico.presentation.bullying

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.domain.interactor.bullying.GetBullying
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel

class BullyingViewModel(
    private val schedulerProvider: SchedulerProvider,
    private val getBullying: GetBullying
) : BaseViewModel(){

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        super.onCreate()
        getBullying.execute()
    }
}
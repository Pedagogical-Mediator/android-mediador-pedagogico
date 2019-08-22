package com.ufms.mediadorpedagogico.presentation.util.resources

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider {
    fun main(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    fun io(): Scheduler {
        return Schedulers.io()
    }

    fun computation(): Scheduler {
        return Schedulers.computation()
    }
}

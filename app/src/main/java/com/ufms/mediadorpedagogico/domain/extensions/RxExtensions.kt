package com.ufms.mediadorpedagogico.domain.extensions

import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import io.reactivex.Single

fun <T> Single<T>.defaultSched(schedulerProvider: SchedulerProvider): Single<T> {
    return this.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.main())
}
package com.ufms.mediadorpedagogico.presentation.util.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ufms.mediadorpedagogico.presentation.util.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.util.structure.arch.EventObserver

fun <T> defaultMutableLiveData(t: T?): MutableLiveData<T> {
    val liveData = MutableLiveData<T>()
    liveData.value = t
    return liveData
}

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T?) -> Unit) {
    observe(owner, androidx.lifecycle.Observer { observer(it) })
}

fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, observer: (T?) -> Unit) {
    observe(owner, EventObserver(observer))
}

fun <T, U> LiveData<T>.map(mapper: (T?) -> U) = Transformations.map(this, mapper)

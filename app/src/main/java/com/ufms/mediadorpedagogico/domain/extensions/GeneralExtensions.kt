package com.ufms.mediadorpedagogico.domain.extensions

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

infix fun <T> Boolean.then(element: T): T? = if (this) element else null

infix fun Boolean.thenDo(function: () -> Unit) {
    if (this) function.invoke()
}

fun <T> List<T>.forEachBetween(start: Int, end: Int, callback: (value: T) -> Unit) {
    if (start >= end) return
    for (i in start + 1 until end) callback(get(i))
}

fun scheduleCallback(timeInSeconds: Long, callback: () -> Unit) {
    Executors.newSingleThreadScheduledExecutor().schedule(callback, timeInSeconds, TimeUnit.SECONDS)
}

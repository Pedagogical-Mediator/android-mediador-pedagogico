package com.ufms.mediadorpedagogico.domain.extensions

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

infix fun <T> Boolean.then(element: T): T? = if (this) element else null

fun scheduleCallback(timeInSeconds: Long, callback: () -> Unit) {
    Executors.newSingleThreadScheduledExecutor().schedule(callback, timeInSeconds, TimeUnit.SECONDS)
}

package com.ufms.mediadorpedagogico.presentation.util.extensions

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView

val RecyclerView.ViewHolder.context: Context get() = itemView.context

fun Intent.shouldClearTask(clearTask: Boolean) {
    if (clearTask) {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
}

fun <T1, T2, R> safeLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}

fun <T1, T2, R> unsafeLet(p1: T1?, p2: T2?, block: () -> R?): R? {
    return if (p1 == null && p2 == null) block() else null
}

fun <T1> T1.ifNull(block: () -> Unit) {
    if (this == null) block()
}

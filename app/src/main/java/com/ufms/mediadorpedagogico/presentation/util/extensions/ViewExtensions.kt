package com.ufms.mediadorpedagogico.presentation.util.extensions

import android.text.Editable
import android.view.View
import android.widget.Switch
import android.widget.TextView
import com.ufms.mediadorpedagogico.presentation.util.SimpleTextWatcher
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

// TextView
fun TextView.observeChanges(callback: (String) -> Unit): Disposable? {
    val subject = PublishSubject.create<String>()
    addTextChangedListener(object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable) {
            subject.onNext(s.toString())
        }
    })
    return subject.subscribe { callback(it) }
}

//View
fun View.setOnClickListener(callback: () -> Unit) {
    this.setOnClickListener { callback.invoke() }
}

fun View.setOnClickListener(callback: (Boolean) -> Unit) {
    this.setOnClickListener { callback.invoke(true) }
}


// views

fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun Switch.onChecked(callback: (Boolean) -> Unit) {
    setOnCheckedChangeListener { _, isChecked -> callback.invoke(isChecked) }
}
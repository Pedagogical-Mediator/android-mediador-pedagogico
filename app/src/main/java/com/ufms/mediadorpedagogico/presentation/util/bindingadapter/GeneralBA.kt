package com.ufms.mediadorpedagogico.presentation.util.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("makeVisible")
fun View.makeVisible(shouldBe: Boolean?) {
    visibility = shouldBe?.let {
        if (it) View.VISIBLE else View.GONE
    } ?: View.GONE
}
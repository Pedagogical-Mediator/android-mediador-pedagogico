package com.ufms.mediadorpedagogico.presentation.util.bindingadapter

import android.webkit.WebView
import androidx.databinding.BindingAdapter

@BindingAdapter("loadHTML")
fun WebView.loadHTML(html: String?) {
    loadDataWithBaseURL(
        ""
        , html, "text/html", "UTF-8", ""
    )
}
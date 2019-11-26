package com.ufms.mediadorpedagogico.presentation.util.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.ufms.mediadorpedagogico.databinding.ToolbarCustomizedBinding

private fun AppCompatActivity.setupToolbar(toolbar: Toolbar?, showHome: Boolean) {
    toolbar?.let { setSupportActionBar(it) }
    supportActionBar?.run {
        setDisplayHomeAsUpEnabled(showHome)
        setDisplayShowHomeEnabled(showHome)
        setDisplayShowTitleEnabled(false)
    }
}

//Toolbar
fun AppCompatActivity.setupCustomizedToolbar(
    includedToolbarViewBinding: ToolbarCustomizedBinding,
    showHome: Boolean = true,
    title: String? = null
) {
    if (title != null) {
        includedToolbarViewBinding.toolbarTitle.text = title
    }
    setupToolbar(includedToolbarViewBinding.toolbar, showHome)
}

//SoftKeyboard

fun AppCompatActivity.hideSoftKeyboard() {
    currentFocus?.let {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Fragment.loadPage(url: String?) {
    url?.run {
        var u = this
        if (!startsWith("http://") && !startsWith("https://"))
            u = "http://$url"
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(u)))
    }
}
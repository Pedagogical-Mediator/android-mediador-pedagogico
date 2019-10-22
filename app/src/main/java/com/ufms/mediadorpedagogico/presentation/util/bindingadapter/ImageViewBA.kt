package com.ufms.mediadorpedagogico.presentation.util.bindingadapter

import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ufms.mediadorpedagogico.R
import com.ufms.mediadorpedagogico.presentation.util.extensions.shortToast

@BindingAdapter("setImage")
fun ImageView.setImage(base64: String?) {
    base64?.let {
        try {
            val decodedString = Base64.decode(it, Base64.DEFAULT)
            val decodedByte =
                BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            setImageBitmap(decodedByte)
        } catch (e: Exception) {
            context?.run {
                shortToast(getString(R.string.activity_main_error_image_decode))
            }
        }
    }
}
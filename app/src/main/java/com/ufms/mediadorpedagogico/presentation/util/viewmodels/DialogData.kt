package com.ufms.mediadorpedagogico.presentation.util.viewmodels

import com.ufms.mediadorpedagogico.domain.boundary.resources.StringsProvider

class DialogData(
    val title: String,
    val message: String,
    val confirmButtonText: String? = null,
    val onConfirm: (() -> Unit)? = null,
    val dismissButtonText: String? = null,
    val onDismiss: (() -> Unit)? = null,
    val cancelable: Boolean? = true
) {
    companion object {

        fun message(
            title: String,
            message: String,
            onConfirm: (() -> Unit)? = null,
            onDismiss: (() -> Unit)? = null,
            cancelable: Boolean? = true
        ): DialogData {
            return DialogData(title, message, null, onConfirm, null, onDismiss, cancelable)
        }

        fun error(
            title: String,
            message: String,
            confirmButtonText: String? = null,
            onConfirm: (() -> Unit)? = null,
            onDismiss: (() -> Unit)? = null,
            cancelable: Boolean? = false
        ): DialogData {
            return DialogData(
                title,
                message,
                confirmButtonText,
                onConfirm,
                "",
                onDismiss,
                cancelable
            )
        }

        fun error(
            strings: StringsProvider,
            message: String,
            confirmButtonText: String? = null,
            onConfirm: (() -> Unit)? = null,
            onDismiss: (() -> Unit)? = null,
            cancelable: Boolean? = true
        ): DialogData {
            return DialogData(
                strings.errorTitle,
                message,
                confirmButtonText,
                onConfirm,
                null,
                onDismiss,
                cancelable
            )
        }
    }
}

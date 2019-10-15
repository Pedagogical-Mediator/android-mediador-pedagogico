package com.ufms.mediadorpedagogico.domain.util

fun String.safeSlice(start: Int = 0, end: Int = length): String {
    return if (start > length) this
    else if (end > length) slice(start until length)
    else slice(start..end).plus("...")
}

fun String?.removeHtmlTags(): String {
    return this?.run {
        replace(Regex("<.*?>"), " ")
            .replace(Regex("\\n"), " ")
            .trim()
    } ?: ""
}
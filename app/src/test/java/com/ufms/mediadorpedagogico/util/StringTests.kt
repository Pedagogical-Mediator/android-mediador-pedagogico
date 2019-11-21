package com.ufms.mediadorpedagogico.util

import com.ufms.mediadorpedagogico.domain.util.removeHtmlTags
import com.ufms.mediadorpedagogico.domain.util.safeSlice
import org.junit.Test

class StringTests {

    @Test
    fun safeSliceThreeDots() {
        val fifty = "aaaaaaaaaa"
        assert(fifty.safeSlice(0, 5) == "aaaaaa...")
    }

    @Test
    fun safeSliceEndBigger() {
        val fifty = "aaaaaaaaaa"
        assert(fifty.safeSlice(0, 50) == fifty)
    }

    @Test
    fun removeHtmlTags() {
        val tags = "<a>asas</a>"
        assert(tags.removeHtmlTags() == "asas")
    }

    @Test
    fun removeHtmlTagsWithoutTags() {
        val tags = "asas"
        assert(tags.removeHtmlTags() == "asas")
    }

    @Test
    fun removeHtmlTagsNull() {
        val a: String? = null
        assert(a.removeHtmlTags() == "")
    }
}

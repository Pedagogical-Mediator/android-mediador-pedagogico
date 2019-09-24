package com.ufms.mediadorpedagogico.data.remote.entity.notice

import com.google.gson.annotations.SerializedName
import com.ufms.mediadorpedagogico.data.remote.entity.news.ApiNews
import com.ufms.mediadorpedagogico.data.remote.mapper.Mapper
import com.ufms.mediadorpedagogico.domain.entity.notice.NoticeContent

data class ApiNoticeContent(
    @SerializedName("content") val content: List<ApiNotice>?
) {
    object ApiContentNoticeToContentNotice : Mapper<ApiNoticeContent, NoticeContent>() {
        override fun transform(t: ApiNoticeContent) = NoticeContent(
            content = t.content?.let(ApiNotice.ApiNoticeToNotice::transform)
        )
    }
}
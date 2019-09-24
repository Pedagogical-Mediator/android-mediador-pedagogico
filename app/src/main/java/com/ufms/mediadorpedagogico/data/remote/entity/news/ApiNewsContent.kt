package com.ufms.mediadorpedagogico.data.remote.entity.news
import com.google.gson.annotations.SerializedName
import com.ufms.mediadorpedagogico.data.remote.mapper.Mapper
import com.ufms.mediadorpedagogico.domain.entity.news.NewsContent
import com.ufms.mediadorpedagogico.domain.entity.notice.NoticeContent

data class ApiNewsContent(
    @SerializedName("content") val content: List<ApiNews>?
) {
    object ApiContentNewsToContentNews : Mapper<ApiNewsContent, NewsContent>() {
        override fun transform(t: ApiNewsContent) = NewsContent(
            content = t.content?.let(ApiNews.ApiNewsToNews::transform)
        )
    }
}
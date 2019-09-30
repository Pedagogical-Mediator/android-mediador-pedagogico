package com.ufms.mediadorpedagogico.data.remote.entity.news

import com.google.gson.annotations.SerializedName
import com.ufms.mediadorpedagogico.data.remote.entity.ApiLink
import com.ufms.mediadorpedagogico.data.remote.mapper.Mapper
import com.ufms.mediadorpedagogico.domain.entity.news.News
import com.ufms.mediadorpedagogico.domain.entity.notice.Notice
import java.io.Serializable

data class ApiNews(
    @SerializedName("id") val id: Int?,
    @SerializedName("titulo") val title: String?,
    @SerializedName("descricao") val description: String?,
    @SerializedName("link") val link: String?,
    @SerializedName("dataDeCriacao") val createdAt: String
) : Serializable {

    object ApiNewsToNews : Mapper<ApiNews, News>() {
        override fun transform(t: ApiNews) = News(
            id = t.id,
            title = t.title,
            description = t.description,
            link = t.link,
            createdAt = t.createdAt
        )
    }
}
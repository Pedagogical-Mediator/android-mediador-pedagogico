package com.ufms.mediadorpedagogico.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.ufms.mediadorpedagogico.data.remote.mapper.Mapper
import com.ufms.mediadorpedagogico.domain.entity.Homework
import java.io.Serializable

data class ApiHomework(
        @SerializedName("id") val id: Int?,
        @SerializedName("titulo") val title: String?,
        @SerializedName("descricao") val description: String?,
        @SerializedName("links") val homeworkLinks: List<ApiHomeworkLink>?,
        @SerializedName("dataDeCriacao") val createdAt: String
) : Serializable {
    object ApiHomeworkToHomework : Mapper<ApiHomework, Homework>() {
        override fun transform(t: ApiHomework) = Homework(
                id = t.id,
                title = t.title,
                description = t.description,
                homeworkLinks = t.homeworkLinks?.let(ApiHomeworkLink.ApiHomeworkLinkToHomeworkLink::transform),
                createdAt = t.createdAt
        )
    }
}
package com.ufms.mediadorpedagogico.data.remote.entity.homework

import com.google.gson.annotations.SerializedName
import com.ufms.mediadorpedagogico.data.remote.entity.ApiLink
import com.ufms.mediadorpedagogico.data.remote.mapper.Mapper
import com.ufms.mediadorpedagogico.domain.entity.homework.Homework
import java.io.Serializable

data class ApiHomework(
    @SerializedName("id") val id: Int?,
    @SerializedName("titulo") val title: String?,
    @SerializedName("descricao") val description: String?,
    @SerializedName("links") val links: List<ApiLink>?,
    @SerializedName("imagem") val imageBase64: String,
    @SerializedName("dataDeCriacao") val createdAt: String
) : Serializable {
    object ApiHomeworkToHomework : Mapper<ApiHomework, Homework>() {
        override fun transform(t: ApiHomework) = Homework(
            id = t.id,
            title = t.title,
            description = t.description,
            links = t.links?.let(ApiLink.ApiLinkToLink::transform),
            createdAt = t.createdAt,
            imageBase64 = t.imageBase64
        )
    }
}
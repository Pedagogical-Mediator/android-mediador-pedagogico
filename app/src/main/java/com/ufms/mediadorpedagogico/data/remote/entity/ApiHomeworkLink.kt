package com.ufms.mediadorpedagogico.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.ufms.mediadorpedagogico.data.remote.mapper.Mapper
import com.ufms.mediadorpedagogico.domain.entity.HomeworkLink
import java.io.Serializable

data class ApiHomeworkLink(
    @SerializedName("nome") val name: String?,
    @SerializedName("link") val link: String?
) : Serializable {
    object ApiHomeworkLinkToHomeworkLink : Mapper<ApiHomeworkLink, HomeworkLink>() {
        override fun transform(t: ApiHomeworkLink) = HomeworkLink(
            name = t.name,
            link = t.link
        )
    }
}
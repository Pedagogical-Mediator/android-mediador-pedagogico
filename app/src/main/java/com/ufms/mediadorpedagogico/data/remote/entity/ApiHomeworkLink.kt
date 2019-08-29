package com.ufms.mediadorpedagogico.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.ufms.mediadorpedagogico.data.remote.mapper.Mapper
import com.ufms.mediadorpedagogico.domain.entity.HomeworkLink
import java.io.Serializable

data class ApiHomeworkLink(
    @SerializedName("tipoDeLink") val linkType: Int?,
    @SerializedName("link") val link: String?
) : Serializable {
    object ApiHomeworkLinkToHomeworkLink : Mapper<ApiHomeworkLink, HomeworkLink>() {
        override fun transform(t: ApiHomeworkLink) = HomeworkLink(
            linkType = t.linkType,
            link = t.link
        )
    }
}
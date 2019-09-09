package com.ufms.mediadorpedagogico.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.ufms.mediadorpedagogico.data.remote.mapper.Mapper
import com.ufms.mediadorpedagogico.domain.entity.Link
import java.io.Serializable

data class ApiLink(
    @SerializedName("nome") val name: String?,
    @SerializedName("link") val link: String?
) : Serializable {
    object ApiLinkToLink : Mapper<ApiLink, Link>() {
        override fun transform(t: ApiLink) = Link(
            name = t.name,
            link = t.link
        )
    }
}
package com.ufms.mediadorpedagogico.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.ufms.mediadorpedagogico.data.remote.mapper.Mapper
import com.ufms.mediadorpedagogico.domain.entity.LibResource
import java.io.Serializable

data class ApiLibResource(
    @SerializedName("id") val id: Int?,
    @SerializedName("idDoTopico") val topicId: Int?,
    @SerializedName("nome") val name: String?,
    @SerializedName("link") val link: String?
) : Serializable {

    object ApiLibResourceToLibResource: Mapper<ApiLibResource, LibResource>() {
        override fun transform(t: ApiLibResource): LibResource {
            return LibResource(
                id = t.id,
                topicId = t.topicId,
                name = t.name,
                link = t.link
            )
        }
    }
}
package com.ufms.mediadorpedagogico.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.ufms.mediadorpedagogico.data.remote.mapper.Mapper
import com.ufms.mediadorpedagogico.domain.entity.About
import java.io.Serializable

data class ApiAbout(
    @SerializedName("id") val id: Int?,
    @SerializedName("descricao") val description: String?,
    @SerializedName("imagem") val image: String?,
    @SerializedName("linkDaEscola") val link: String?
) : Serializable {

    object ApiAboutToAbout : Mapper<ApiAbout, About>() {
        override fun transform(t: ApiAbout) = About(
            id = t.id,
            description = t.description,
            image = t.image,
            link = t.link
        )
    }
}


package com.ufms.mediadorpedagogico.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.ufms.mediadorpedagogico.data.remote.mapper.Mapper
import com.ufms.mediadorpedagogico.domain.entity.Guild
import java.io.Serializable

data class ApiGuild(
    @SerializedName("id") val id: Int?,
    @SerializedName("descricao") val description: String?,
    @SerializedName("imagem") val image: String?,
    @SerializedName("linkDoGremio") val link: String?
) : Serializable {

    object ApiGuildToGuild : Mapper<ApiGuild, Guild>() {
        override fun transform(t: ApiGuild) = Guild(
            id = t.id,
            description = t.description,
            image = t.image,
            link = t.link
        )
    }
}


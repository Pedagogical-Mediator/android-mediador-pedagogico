package com.ufms.mediadorpedagogico.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.ufms.mediadorpedagogico.data.remote.mapper.Mapper
import com.ufms.mediadorpedagogico.domain.entity.Bullying
import java.io.Serializable

data class ApiBullying(
    @SerializedName("id") val id: Int?,
    @SerializedName("descricao") val description: String?,
    @SerializedName("imagem") val image: String?,
    @SerializedName("linkDoFormulario") val link: String?
) : Serializable {

    object ApiBullyingToBullying : Mapper<ApiBullying, Bullying>() {
        override fun transform(t: ApiBullying) = Bullying(
            id = t.id,
            description = t.description,
            image = t.image,
            link = t.link
        )
    }
}


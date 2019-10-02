package com.ufms.mediadorpedagogico.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.ufms.mediadorpedagogico.data.remote.mapper.Mapper
import com.ufms.mediadorpedagogico.domain.entity.Bullying
import java.io.Serializable

data class ApiBullying(
    @SerializedName("descricao") val description: String?,
    @SerializedName("link") val link: String?
) : Serializable {

    object ApiBullyingToBullying : Mapper<ApiBullying, Bullying>() {
        override fun transform(t: ApiBullying) = Bullying(
            description = t.description,
            link = t.link
        )
    }
}


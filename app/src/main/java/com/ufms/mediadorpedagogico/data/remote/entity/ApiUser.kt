package com.ufms.mediadorpedagogico.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.ufms.mediadorpedagogico.data.remote.mapper.Mapper
import com.ufms.mediadorpedagogico.domain.entity.User

data class ApiUser(
    @SerializedName("id") val id: String?,
    @SerializedName("nome") val name: String?,
    @SerializedName("token") val token: String?,
    @SerializedName("chaveDeAcesso") val classKey: String?
) {
    object ApiUserToUserMapper : Mapper<ApiUser, User>() {
        override fun transform(t: ApiUser) = User(
            id = t.id,
            name = t.name,
            token = t.token,
            classKey = t.classKey
        )
    }
}

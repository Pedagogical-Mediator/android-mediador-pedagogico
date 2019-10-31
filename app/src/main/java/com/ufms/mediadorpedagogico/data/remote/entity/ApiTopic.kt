package com.ufms.mediadorpedagogico.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.ufms.mediadorpedagogico.data.remote.mapper.Mapper
import com.ufms.mediadorpedagogico.domain.entity.Topic

data class ApiTopic(
    @SerializedName("id") val id: Int?,
    @SerializedName("nome") val name: String?
) {

    object ApiTopicToTopic : Mapper<ApiTopic, Topic>() {
        override fun transform(t: ApiTopic): Topic {
            return Topic(
                id = t.id,
                name = t.name
            )
        }
    }
}
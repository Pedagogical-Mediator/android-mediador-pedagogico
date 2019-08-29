package com.ufms.mediadorpedagogico.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.ufms.mediadorpedagogico.data.remote.mapper.Mapper
import com.ufms.mediadorpedagogico.domain.entity.HomeworkContent

data class ApiHomeworkContent(
    @SerializedName("content") val content: List<ApiHomework>?
) {
    object ApiContentHomeworkToContentHomework : Mapper<ApiHomeworkContent, HomeworkContent>() {
        override fun transform(t: ApiHomeworkContent) = HomeworkContent(
            content = t.content?.let(ApiHomework.ApiHomeworkToHomework::transform)
        )
    }
}
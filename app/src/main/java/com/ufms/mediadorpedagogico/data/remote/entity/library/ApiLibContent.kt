package com.ufms.mediadorpedagogico.data.remote.entity.library

import com.google.gson.annotations.SerializedName
import com.ufms.mediadorpedagogico.data.remote.mapper.Mapper
import com.ufms.mediadorpedagogico.domain.entity.library.LibContent

data class ApiLibContent(
    @SerializedName("content") val content: List<ApiLibResource>?
) {
    object ApiLibContentToLibContent : Mapper<ApiLibContent, LibContent>() {
        override fun transform(t: ApiLibContent) =
            LibContent(
                content = t.content?.let(ApiLibResource.ApiLibResourceToLibResource::transform)
            )
    }
}
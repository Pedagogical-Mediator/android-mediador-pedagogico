package com.ufms.mediadorpedagogico.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.ufms.mediadorpedagogico.data.remote.mapper.Mapper
import com.ufms.mediadorpedagogico.domain.entity.Calendar
import java.io.Serializable

data class ApiCalendar(
    @SerializedName("id") val id: Int?,
    @SerializedName("linkDoCalendario") val link: String?
) : Serializable {

    object ApiCalendarToCalendar : Mapper<ApiCalendar, Calendar>() {
        override fun transform(t: ApiCalendar) = Calendar(
            id = t.id,
            link = t.link
        )
    }
}


package com.ufms.mediadorpedagogico.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.ufms.mediadorpedagogico.data.remote.mapper.Mapper
import com.ufms.mediadorpedagogico.domain.entity.Teacher
import java.io.Serializable

data class ApiTeacher(
    @SerializedName("id") val id: Int?,
    @SerializedName("nome") val name: String?,
    @SerializedName("disciplinas") val subjects: List<String>?,
    @SerializedName("descricao") val description: String?
) : Serializable {

    object ApiTeacherToTeacher : Mapper<ApiTeacher, Teacher>() {
        override fun transform(t: ApiTeacher) = Teacher(
            id = t.id,
            name = t.name,
            subjects = t.subjects,
            description = t.description
        )
    }
}
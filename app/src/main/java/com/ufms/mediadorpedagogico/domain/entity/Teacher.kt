package com.ufms.mediadorpedagogico.domain.entity

import java.io.Serializable

data class Teacher(
    var id: Int? = null,
    var name: String? = null,
    var subjects: List<String>? = null,
    var description: String? = null
) : Serializable
package com.ufms.mediadorpedagogico.domain.entity

import java.io.Serializable

data class Homework(
    var id: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var homeworkLinks: List<HomeworkLink>? = null
) : Serializable

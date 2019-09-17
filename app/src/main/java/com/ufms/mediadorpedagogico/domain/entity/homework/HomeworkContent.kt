package com.ufms.mediadorpedagogico.domain.entity.homework

import java.io.Serializable

data class HomeworkContent(
    var content: List<Homework>? = null
) : Serializable

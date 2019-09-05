package com.ufms.mediadorpedagogico.domain.entity

import java.io.Serializable

data class HomeworkContent(
        var content: List<Homework>? = null
) : Serializable

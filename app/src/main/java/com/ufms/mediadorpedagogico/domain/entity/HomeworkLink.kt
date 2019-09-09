package com.ufms.mediadorpedagogico.domain.entity

import java.io.Serializable

data class HomeworkLink(
        var name: String? = null,
        var link: String? = null
) : Serializable

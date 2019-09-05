package com.ufms.mediadorpedagogico.domain.entity

import java.io.Serializable

data class HomeworkLink(
        var linkType: Int? = null,
        var link: String? = null
) : Serializable

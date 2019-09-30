package com.ufms.mediadorpedagogico.domain.entity.news

import java.io.Serializable

data class News(
    var id: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var link: String? = null,
    var createdAt: String? = null
) : Serializable

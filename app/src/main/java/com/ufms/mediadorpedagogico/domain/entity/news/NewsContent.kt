package com.ufms.mediadorpedagogico.domain.entity.news

import java.io.Serializable

data class NewsContent(
    var content: List<News>? = null
) : Serializable

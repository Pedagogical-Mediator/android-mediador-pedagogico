package com.ufms.mediadorpedagogico.domain.entity.notice

import com.ufms.mediadorpedagogico.domain.entity.Link
import java.io.Serializable

data class Notice(
    var id: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var links: List<Link>? = null,
    var createdAt: String? = null,
    var imageBase64: String? = ""
) : Serializable

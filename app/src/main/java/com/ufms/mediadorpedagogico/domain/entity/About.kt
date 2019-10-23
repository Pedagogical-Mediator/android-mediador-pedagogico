package com.ufms.mediadorpedagogico.domain.entity

import java.io.Serializable

data class About(
    var id: Int? = null,
    var description: String? = null,
    var image: String? = null,
    var link: String? = null
) : Serializable

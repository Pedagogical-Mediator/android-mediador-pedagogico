package com.ufms.mediadorpedagogico.domain.entity

import java.io.Serializable

data class Link(
    var name: String? = null,
    var link: String? = null
) : Serializable

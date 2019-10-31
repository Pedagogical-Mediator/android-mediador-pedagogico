package com.ufms.mediadorpedagogico.domain.entity

import java.io.Serializable

data class LibResource(
    val id: Int? = null,
    val topicId: Int? = null,
    val name: String? = null,
    val link: String? = null
) : Serializable
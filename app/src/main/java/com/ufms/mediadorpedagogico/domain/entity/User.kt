package com.ufms.mediadorpedagogico.domain.entity

import java.io.Serializable

data class User(
    var id: String? = null,
    var name: String? = null,
    var token: String? = null
) : Serializable

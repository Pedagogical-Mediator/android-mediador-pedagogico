package com.ufms.mediadorpedagogico.domain.entity.library

import java.io.Serializable

data class LibContent(
    var content: List<LibResource>? = null
) : Serializable

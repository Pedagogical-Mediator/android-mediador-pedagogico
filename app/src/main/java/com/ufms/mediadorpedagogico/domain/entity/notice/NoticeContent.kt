package com.ufms.mediadorpedagogico.domain.entity.notice

import java.io.Serializable

data class NoticeContent(
    var content: List<Notice>? = null
) : Serializable

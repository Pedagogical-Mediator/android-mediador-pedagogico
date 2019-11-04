package com.ufms.mediadorpedagogico.domain.boundary

import com.ufms.mediadorpedagogico.domain.entity.Guild
import io.reactivex.Single

interface GuildRepository {
    fun getGuildInformation(id: Int): Single<Guild>
}
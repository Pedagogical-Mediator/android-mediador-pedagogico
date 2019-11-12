package com.ufms.mediadorpedagogico.domain.interactor.guild

import com.ufms.mediadorpedagogico.domain.boundary.GuildRepository
import com.ufms.mediadorpedagogico.domain.entity.Guild
import com.ufms.mediadorpedagogico.domain.interactor.user.GetPersistedUser
import io.reactivex.Single

class GetGuild constructor(
    private val repository: GuildRepository
) {

    fun execute(): Single<Guild> {
        return repository.getGuildInformation()
    }
}

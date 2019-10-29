package com.ufms.mediadorpedagogico.data.remote.repository

import com.ufms.mediadorpedagogico.data.remote.client.ApiClient
import com.ufms.mediadorpedagogico.data.remote.entity.ApiGuild
import com.ufms.mediadorpedagogico.domain.boundary.GuildRepository
import com.ufms.mediadorpedagogico.domain.entity.Guild
import io.reactivex.Single

class DefaultGuildRepository(
    private val apiClient: ApiClient
) : GuildRepository {

    override fun getGuildInformation(): Single<Guild> {
        return apiClient.getGuildInformation().map(ApiGuild.ApiGuildToGuild::transform)
    }
}


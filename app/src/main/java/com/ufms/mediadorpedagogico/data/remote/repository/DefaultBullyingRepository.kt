package com.ufms.mediadorpedagogico.data.remote.repository

import com.ufms.mediadorpedagogico.data.remote.client.ApiClient
import com.ufms.mediadorpedagogico.data.remote.entity.ApiBullying
import com.ufms.mediadorpedagogico.domain.boundary.BullyingRepository
import com.ufms.mediadorpedagogico.domain.entity.Bullying
import io.reactivex.Single

class DefaultBullyingRepository(
    private val apiClient: ApiClient
) : BullyingRepository {

    override fun getBullyingInformation(id: Int): Single<Bullying> {
        return apiClient.getBullyingInformation(id).map(ApiBullying.ApiBullyingToBullying::transform)
    }
}


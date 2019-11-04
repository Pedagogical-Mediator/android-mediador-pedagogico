package com.ufms.mediadorpedagogico.domain.boundary

import com.ufms.mediadorpedagogico.domain.entity.Bullying
import io.reactivex.Single

interface BullyingRepository {
    fun getBullyingInformation(id: Int): Single<Bullying>
}
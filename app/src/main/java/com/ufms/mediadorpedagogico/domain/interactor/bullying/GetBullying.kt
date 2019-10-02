package com.ufms.mediadorpedagogico.domain.interactor.bullying

import com.ufms.mediadorpedagogico.domain.boundary.BullyingRepository
import com.ufms.mediadorpedagogico.domain.entity.Bullying
import io.reactivex.Single

class GetBullying constructor(
    private val repository: BullyingRepository
) {

    fun execute(): Single<Bullying> {
        return repository.getBullyingInformation()
    }
}

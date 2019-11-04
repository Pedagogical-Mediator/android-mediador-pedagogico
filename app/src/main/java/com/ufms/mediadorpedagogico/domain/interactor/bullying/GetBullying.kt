package com.ufms.mediadorpedagogico.domain.interactor.bullying

import com.ufms.mediadorpedagogico.domain.boundary.BullyingRepository
import com.ufms.mediadorpedagogico.domain.entity.Bullying
import com.ufms.mediadorpedagogico.domain.interactor.user.GetPersistedUser
import io.reactivex.Single

class GetBullying constructor(
    private val repository: BullyingRepository,
    private val persistedUser: GetPersistedUser
) {

    fun execute(): Single<Bullying> {
        return repository.getBullyingInformation(persistedUser.getUserId())
    }
}

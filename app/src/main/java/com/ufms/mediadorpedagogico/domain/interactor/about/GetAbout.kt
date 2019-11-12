package com.ufms.mediadorpedagogico.domain.interactor.about

import com.ufms.mediadorpedagogico.domain.boundary.AboutRepository
import com.ufms.mediadorpedagogico.domain.entity.About
import com.ufms.mediadorpedagogico.domain.interactor.user.GetPersistedUser
import io.reactivex.Single

class GetAbout constructor(
    private val repository: AboutRepository) {

    fun execute(): Single<About> {
        return repository.getAboutInformation()
    }
}

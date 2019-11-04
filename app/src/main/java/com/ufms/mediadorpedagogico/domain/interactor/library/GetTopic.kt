package com.ufms.mediadorpedagogico.domain.interactor.library

import com.ufms.mediadorpedagogico.domain.boundary.LibraryRepository
import com.ufms.mediadorpedagogico.domain.entity.library.Topic
import com.ufms.mediadorpedagogico.domain.interactor.user.GetPersistedUser
import io.reactivex.Single

class GetTopic(
    private val libraryRepository: LibraryRepository,
    private val persistedUser: GetPersistedUser
) {

    fun execute(): Single<List<Topic>> {
        return libraryRepository.getTopics(persistedUser.getUserId())
    }
}
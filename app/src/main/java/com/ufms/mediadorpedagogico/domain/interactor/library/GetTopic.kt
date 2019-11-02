package com.ufms.mediadorpedagogico.domain.interactor.library

import com.ufms.mediadorpedagogico.domain.boundary.LibraryRepository
import com.ufms.mediadorpedagogico.domain.entity.library.Topic
import io.reactivex.Single

class GetTopic(
    private val libraryRepository: LibraryRepository
) {

    fun execute(): Single<List<Topic>> {
        return libraryRepository.getTopics()
    }
}
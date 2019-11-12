package com.ufms.mediadorpedagogico.domain.interactor.library

import com.ufms.mediadorpedagogico.domain.boundary.LibraryRepository
import com.ufms.mediadorpedagogico.domain.entity.library.LibContent
import com.ufms.mediadorpedagogico.domain.entity.library.LibResource
import com.ufms.mediadorpedagogico.domain.interactor.user.GetPersistedUser
import io.reactivex.Single

class GetLibResources(
    private val libraryRepository: LibraryRepository
) {
    fun execute(pageNumber: Int, topicId: Int): Single<LibContent> {
        return libraryRepository.getLibResources(pageNumber, topicId)
    }
}
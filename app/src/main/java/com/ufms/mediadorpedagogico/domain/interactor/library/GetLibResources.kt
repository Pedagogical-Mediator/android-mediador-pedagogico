package com.ufms.mediadorpedagogico.domain.interactor.library

import com.ufms.mediadorpedagogico.domain.boundary.LibraryRepository
import com.ufms.mediadorpedagogico.domain.entity.LibResource
import io.reactivex.Single

class GetLibResources(
    private val libraryRepository: LibraryRepository
) {
    fun execute(id: Int): Single<List<LibResource>> {
        return libraryRepository.getLibResources(id)
    }
}
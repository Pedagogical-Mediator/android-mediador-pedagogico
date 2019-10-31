package com.ufms.mediadorpedagogico.domain.boundary

import com.ufms.mediadorpedagogico.domain.entity.LibResource
import com.ufms.mediadorpedagogico.domain.entity.Topic
import io.reactivex.Single

interface LibraryRepository {
    fun getTopics(): Single<List<Topic>>
    fun getLibResources(id: Int): Single<List<LibResource>>
}
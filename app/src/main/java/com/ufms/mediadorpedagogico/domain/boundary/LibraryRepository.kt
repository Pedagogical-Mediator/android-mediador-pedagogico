package com.ufms.mediadorpedagogico.domain.boundary

import com.ufms.mediadorpedagogico.domain.entity.library.LibContent
import com.ufms.mediadorpedagogico.domain.entity.library.LibResource
import com.ufms.mediadorpedagogico.domain.entity.library.Topic
import io.reactivex.Single

interface LibraryRepository {
    fun getTopics(id: Int): Single<List<Topic>>
    fun getLibResources(id: Int, pageNumber: Int, topicId: Int): Single<LibContent>
}
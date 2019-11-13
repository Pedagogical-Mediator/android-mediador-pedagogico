package com.ufms.mediadorpedagogico.domain.boundary

import com.ufms.mediadorpedagogico.domain.entity.library.LibContent
import com.ufms.mediadorpedagogico.domain.entity.library.LibResource
import com.ufms.mediadorpedagogico.domain.entity.library.Topic
import io.reactivex.Single

interface LibraryRepository {
    fun getTopics(): Single<List<Topic>>
    fun getLibResources(pageNumber: Int, topicId: Int): Single<LibContent>
}
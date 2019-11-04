package com.ufms.mediadorpedagogico.data.remote.repository

import com.ufms.mediadorpedagogico.data.remote.client.ApiClient
import com.ufms.mediadorpedagogico.data.remote.entity.library.ApiLibContent
import com.ufms.mediadorpedagogico.data.remote.entity.library.ApiLibResource
import com.ufms.mediadorpedagogico.data.remote.entity.library.ApiTopic
import com.ufms.mediadorpedagogico.domain.boundary.LibraryRepository
import com.ufms.mediadorpedagogico.domain.entity.library.LibContent
import com.ufms.mediadorpedagogico.domain.entity.library.LibResource
import com.ufms.mediadorpedagogico.domain.entity.library.Topic
import io.reactivex.Single

class DefaultLibraryRepository(
    private val apiClient: ApiClient
): LibraryRepository {

    override fun getTopics(id: Int): Single<List<Topic>> {
        return apiClient.getTopics(id).map(ApiTopic.ApiTopicToTopic::transform)
    }

    override fun getLibResources(id: Int, pageNumber: Int, topicId: Int): Single<LibContent> {
        return apiClient.getLibResources(id, pageNumber, topicId).map(ApiLibContent.ApiLibContentToLibContent::transform)
    }
}
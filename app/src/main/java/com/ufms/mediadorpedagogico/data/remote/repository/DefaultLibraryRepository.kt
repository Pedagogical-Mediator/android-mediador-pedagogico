package com.ufms.mediadorpedagogico.data.remote.repository

import com.ufms.mediadorpedagogico.data.remote.client.ApiClient
import com.ufms.mediadorpedagogico.data.remote.entity.ApiLibResource
import com.ufms.mediadorpedagogico.data.remote.entity.ApiTopic
import com.ufms.mediadorpedagogico.domain.boundary.LibraryRepository
import com.ufms.mediadorpedagogico.domain.entity.LibResource
import com.ufms.mediadorpedagogico.domain.entity.Topic
import io.reactivex.Single

class DefaultLibraryRepository(
    private val apiClient: ApiClient
): LibraryRepository {

    override fun getTopics(): Single<List<Topic>> {
        return apiClient.getTopics().map(ApiTopic.ApiTopicToTopic::transform)
    }

    override fun getLibResources(id: Int): Single<List<LibResource>> {
        return apiClient.getLibResources().map(ApiLibResource.ApiLibResourceToLibResource::transform)
    }
}
package com.ufms.mediadorpedagogico.data.remote.repository

import com.ufms.mediadorpedagogico.data.remote.client.ApiClient
import com.ufms.mediadorpedagogico.data.remote.entity.news.ApiNewsContent
import com.ufms.mediadorpedagogico.domain.boundary.NewsRepository

class DefaultNewsRepository(
    private val apiClient: ApiClient
) : NewsRepository {
    override fun getNewsContentList(id: Int, pageNumber: Int) = apiClient.getListOfNews(id, pageNumber).map(
        ApiNewsContent.ApiContentNewsToContentNews::transform
    )
}
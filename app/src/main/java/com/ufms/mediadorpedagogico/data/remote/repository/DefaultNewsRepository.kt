package com.ufms.mediadorpedagogico.data.remote.repository

import com.ufms.mediadorpedagogico.data.remote.client.ApiClient
import com.ufms.mediadorpedagogico.data.remote.entity.news.ApiNewsContent
import com.ufms.mediadorpedagogico.domain.boundary.NewsRepository

class DefaultNewsRepository(
    private val apiClient: ApiClient
) : NewsRepository {
    override fun getNewsContentList(pageNumber: Int) = apiClient.getListOfNews(pageNumber).map(
        ApiNewsContent.ApiContentNewsToContentNews::transform
    )
}
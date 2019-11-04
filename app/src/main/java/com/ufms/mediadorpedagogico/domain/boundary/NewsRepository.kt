package com.ufms.mediadorpedagogico.domain.boundary

import com.ufms.mediadorpedagogico.domain.entity.news.NewsContent
import io.reactivex.Single

interface NewsRepository {
    fun getNewsContentList(id: Int, pageNumber: Int): Single<NewsContent>
}
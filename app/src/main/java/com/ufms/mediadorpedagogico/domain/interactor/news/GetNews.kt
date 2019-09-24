package com.ufms.mediadorpedagogico.domain.interactor.news

import com.ufms.mediadorpedagogico.domain.boundary.NewsRepository
import com.ufms.mediadorpedagogico.domain.entity.news.NewsContent
import io.reactivex.Single

class GetNews constructor(
    private val repository: NewsRepository
) {

    fun execute(pageNumber: Int): Single<NewsContent> {
        return repository.getNewsContentList(pageNumber)
    }
}

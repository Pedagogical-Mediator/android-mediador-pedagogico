package com.ufms.mediadorpedagogico.domain.interactor.news

import com.ufms.mediadorpedagogico.domain.boundary.NewsRepository
import com.ufms.mediadorpedagogico.domain.entity.news.NewsContent
import com.ufms.mediadorpedagogico.domain.interactor.user.GetPersistedUser
import io.reactivex.Single

class GetNews constructor(
    private val repository: NewsRepository,
    private val persistedUser: GetPersistedUser
) {

    fun execute(pageNumber: Int): Single<NewsContent> {
        return repository.getNewsContentList(persistedUser.getUserId(), pageNumber)
    }
}

package com.ufms.mediadorpedagogico.domain.interactor.homework

import com.ufms.mediadorpedagogico.domain.boundary.HomeworkRepository
import com.ufms.mediadorpedagogico.domain.entity.homework.HomeworkContent
import com.ufms.mediadorpedagogico.domain.interactor.user.GetPersistedUser
import io.reactivex.Single

class GetHomework constructor(
    private val repository: HomeworkRepository,
    private val persistedUser: GetPersistedUser
) {

    fun execute(pageNumber: Int, classKey: String): Single<HomeworkContent> {
        return repository.getHomeworkList(persistedUser.getUserId(), pageNumber, classKey)
    }
}

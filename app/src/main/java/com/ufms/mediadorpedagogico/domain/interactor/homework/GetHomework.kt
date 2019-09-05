package com.ufms.mediadorpedagogico.domain.interactor.homework

import com.ufms.mediadorpedagogico.domain.boundary.HomeworkRepository
import com.ufms.mediadorpedagogico.domain.entity.HomeworkContent
import io.reactivex.Single

class GetHomework constructor(
        private val repository: HomeworkRepository
) {

    fun execute(pageNumber: Int): Single<HomeworkContent> {
        return repository.getHomeworkList(pageNumber)
    }
}

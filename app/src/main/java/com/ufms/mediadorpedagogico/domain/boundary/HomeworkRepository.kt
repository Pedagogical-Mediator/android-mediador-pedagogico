package com.ufms.mediadorpedagogico.domain.boundary

import com.ufms.mediadorpedagogico.domain.entity.HomeworkContent
import io.reactivex.Single

interface HomeworkRepository {
    fun getHomeworkList(pageNumber: Int): Single<HomeworkContent>
}
package com.ufms.mediadorpedagogico.domain.interactor.notice

import com.ufms.mediadorpedagogico.domain.boundary.NoticeRepository
import com.ufms.mediadorpedagogico.domain.entity.notice.NoticeContent
import io.reactivex.Single

class GetNotice constructor(
    private val repository: NoticeRepository
) {

    fun execute(pageNumber: Int): Single<NoticeContent> {
        return repository.getNoticeList(pageNumber)
    }
}

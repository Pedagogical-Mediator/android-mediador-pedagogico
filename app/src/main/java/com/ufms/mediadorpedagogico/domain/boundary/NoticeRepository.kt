package com.ufms.mediadorpedagogico.domain.boundary

import com.ufms.mediadorpedagogico.domain.entity.notice.NoticeContent
import io.reactivex.Single

interface NoticeRepository {
    fun getNoticeList(pageNumber: Int): Single<NoticeContent>
}
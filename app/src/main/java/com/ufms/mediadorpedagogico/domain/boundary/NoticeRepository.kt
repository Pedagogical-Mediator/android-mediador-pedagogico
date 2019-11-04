package com.ufms.mediadorpedagogico.domain.boundary

import com.ufms.mediadorpedagogico.domain.entity.notice.NoticeContent
import io.reactivex.Single

interface NoticeRepository {
    fun getNoticeList(id: Int, pageNumber: Int): Single<NoticeContent>
}
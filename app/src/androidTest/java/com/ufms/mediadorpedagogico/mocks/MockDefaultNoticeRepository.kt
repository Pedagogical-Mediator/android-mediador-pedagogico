package com.ufms.mediadorpedagogico.mocks

import com.ufms.mediadorpedagogico.domain.boundary.NoticeRepository
import com.ufms.mediadorpedagogico.domain.entity.notice.Notice
import com.ufms.mediadorpedagogico.domain.entity.notice.NoticeContent
import io.reactivex.Single

class MockDefaultNoticeRepository : NoticeRepository {

    private val firstPageMocked = NoticeContent(listOf(
        Notice(id = 1, title = "1", description = "1"),
        Notice(id = 2, title = "2", description = "1"),
        Notice(id = 3, title = "3", description = "1"),
        Notice(id = 4, title = "4", description = "1"),
        Notice(id = 5, title = "5", description = "1"),
        Notice(id = 6, title = "6", description = "1"),
        Notice(id = 7, title = "7", description = "1"),
        Notice(id = 8, title = "8", description = "1"),
        Notice(id = 9, title = "9", description = "1"),
        Notice(id = 10, title = "10", description = "1")
    ))

    private val secondPageMocked = NoticeContent(listOf(
        Notice(id = 11, title = "11", description = "1"),
        Notice(id = 12, title = "12", description = "1"),
        Notice(id = 13, title = "13", description = "1"),
        Notice(id = 14, title = "14", description = "1"),
        Notice(id = 15, title = "15", description = "1"),
        Notice(id = 16, title = "16", description = "1"),
        Notice(id = 17, title = "17", description = "1"),
        Notice(id = 18, title = "18", description = "1"),
        Notice(id = 19, title = "19", description = "1"),
        Notice(id = 20, title = "20", description = "1")
    ))

    override fun getNoticeList(pageNumber: Int): Single<NoticeContent> {
        return Single.just(when (pageNumber) {
            0 -> firstPageMocked
            1 -> firstPageMocked
            2 -> secondPageMocked
            else -> null
        })
    }


}
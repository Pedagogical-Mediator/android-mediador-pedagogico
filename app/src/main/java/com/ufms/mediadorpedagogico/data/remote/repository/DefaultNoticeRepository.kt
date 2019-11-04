package com.ufms.mediadorpedagogico.data.remote.repository

import com.ufms.mediadorpedagogico.data.remote.client.ApiClient
import com.ufms.mediadorpedagogico.data.remote.entity.notice.ApiNoticeContent
import com.ufms.mediadorpedagogico.domain.boundary.NoticeRepository

class DefaultNoticeRepository(
    private val apiClient: ApiClient
) : NoticeRepository {
    override fun getNoticeList(id: Int, pageNumber: Int) = apiClient.getListOfNotice(id, pageNumber).map(
        ApiNoticeContent.ApiContentNoticeToContentNotice::transform
    )
}
package com.ufms.mediadorpedagogico.data.remote.repository

import com.ufms.mediadorpedagogico.data.remote.client.ApiClient
import com.ufms.mediadorpedagogico.data.remote.entity.ApiCalendar
import com.ufms.mediadorpedagogico.domain.boundary.CalendarRepository
import com.ufms.mediadorpedagogico.domain.entity.Calendar
import io.reactivex.Single

class DefaultCalendarRepository(
    private val apiClient: ApiClient
) : CalendarRepository {

    override fun getCalendarInformation(): Single<Calendar> {
        return apiClient.getCalendar().map(ApiCalendar.ApiCalendarToCalendar::transform)
    }
}


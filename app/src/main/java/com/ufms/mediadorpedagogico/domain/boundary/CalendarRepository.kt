package com.ufms.mediadorpedagogico.domain.boundary

import com.ufms.mediadorpedagogico.domain.entity.Calendar
import io.reactivex.Single

interface CalendarRepository {
    fun getCalendarInformation(id: Int): Single<Calendar>
}
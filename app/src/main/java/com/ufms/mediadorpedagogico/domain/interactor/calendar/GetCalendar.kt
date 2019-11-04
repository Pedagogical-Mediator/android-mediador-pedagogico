package com.ufms.mediadorpedagogico.domain.interactor.calendar

import com.ufms.mediadorpedagogico.domain.boundary.CalendarRepository
import com.ufms.mediadorpedagogico.domain.entity.Calendar
import com.ufms.mediadorpedagogico.domain.interactor.user.GetPersistedUser
import io.reactivex.Single

class GetCalendar constructor(
    private val repository: CalendarRepository,
    private val persistedUser: GetPersistedUser
) {

    fun execute(): Single<Calendar> {
        return repository.getCalendarInformation(persistedUser.getUserId())
    }
}

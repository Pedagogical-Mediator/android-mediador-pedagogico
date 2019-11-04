package com.ufms.mediadorpedagogico.presentation.calendar.delegate

import androidx.lifecycle.LiveData
import com.ufms.mediadorpedagogico.domain.entity.Calendar
import com.ufms.mediadorpedagogico.presentation.util.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder

interface CalendarDelegate {
    val calendarReceived: LiveData<Event<Calendar>>
    fun getCalendar(
        onSuccess: (Calendar) -> Unit,
        onFailure: (Throwable) -> Unit,
        placeholderPlacerAction: (Placeholder) -> (Unit)
    )
}
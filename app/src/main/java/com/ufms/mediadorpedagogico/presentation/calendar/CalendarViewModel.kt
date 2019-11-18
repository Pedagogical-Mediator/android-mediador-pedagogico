package com.ufms.mediadorpedagogico.presentation.calendar

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.presentation.calendar.delegate.CalendarDelegate
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel

class CalendarViewModel(
    calendarDelegate: CalendarDelegate
) : BaseViewModel(), CalendarDelegate by calendarDelegate {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        super.onCreate()
        getCalendar({}, {}, ::setPlaceholder)
    }
}
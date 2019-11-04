package com.ufms.mediadorpedagogico.presentation.calendar.delegate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ufms.mediadorpedagogico.domain.entity.Calendar
import com.ufms.mediadorpedagogico.domain.extensions.defaultSched
import com.ufms.mediadorpedagogico.domain.interactor.calendar.GetCalendar
import com.ufms.mediadorpedagogico.presentation.util.extensions.defaultPlaceholders
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import com.ufms.mediadorpedagogico.presentation.util.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import io.reactivex.rxkotlin.subscribeBy

class DefaultCalendarDelegate constructor(
    private val getCalendar: GetCalendar,
    private val schedulerProvider: SchedulerProvider
) : CalendarDelegate {

    override val calendarReceived: LiveData<Event<Calendar>> get() = calendarReceivedLiveData

    private val calendarReceivedLiveData = MutableLiveData<Event<Calendar>>()

    override fun getCalendar(
        onSuccess: (Calendar) -> Unit,
        onFailure: (Throwable) -> Unit,
        placeholderPlacerAction: (Placeholder) -> (Unit)
    ) {
        getCalendar.execute().defaultSched(schedulerProvider)
            .defaultPlaceholders(placeholderPlacerAction)
            .subscribeBy(onFailure) {
                calendarReceivedLiveData.value = Event(it)
                onSuccess.invoke(it)
            }
    }
}
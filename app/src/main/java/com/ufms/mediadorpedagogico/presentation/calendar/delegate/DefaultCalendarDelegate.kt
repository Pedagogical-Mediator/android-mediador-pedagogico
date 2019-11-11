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
            .subscribeBy({
                calendarReceivedLiveData.value = Event(
                    Calendar(
                    id = 1,
                    link = "https://calendar.google.com/calendar/embed?height=600&amp;wkst=1&amp;bgcolor=%23ffffff&amp;ctz=America%2FCampo_Grande&amp;src=bWVkaWFkb3JwZWRhZ29naWNvZmFicmljYUBnbWFpbC5jb20&amp;src=Mzg0YTVtYTMxMnZtMGZxMmE3ZWZ1Z3Y3bGtAZ3JvdXAuY2FsZW5kYXIuZ29vZ2xlLmNvbQ&amp;src=YWRkcmVzc2Jvb2sjY29udGFjdHNAZ3JvdXAudi5jYWxlbmRhci5nb29nbGUuY29t&amp;src=cHQuYnJhemlsaWFuI2hvbGlkYXlAZ3JvdXAudi5jYWxlbmRhci5nb29nbGUuY29t&amp;color=%2322AA99&amp;color=%2381910B&amp;color=%23329262&amp;color=%231F753C&amp;mode=WEEK&amp;showNav=1&amp;showPrint=0&amp;showTz=0&amp;showCalendars=0&amp;showTabs=0"
                ))
            }) {
                calendarReceivedLiveData.value = Event(it)
                onSuccess.invoke(it)
            }
    }
}
package com.ufms.mediadorpedagogico.presentation.main

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.data.firebase.FirebaseMessagingServiceHandler
import com.ufms.mediadorpedagogico.domain.boundary.resources.Cache
import com.ufms.mediadorpedagogico.domain.boundary.resources.StringsProvider
import com.ufms.mediadorpedagogico.domain.interactor.help.HelpManager
import com.ufms.mediadorpedagogico.domain.interactor.notification.ManageNews
import com.ufms.mediadorpedagogico.domain.interactor.notification.ManageNotices
import com.ufms.mediadorpedagogico.domain.interactor.user.InvalidFieldsException
import com.ufms.mediadorpedagogico.domain.util.getToday
import com.ufms.mediadorpedagogico.domain.util.subscriberHandler
import com.ufms.mediadorpedagogico.presentation.calendar.delegate.CalendarDelegate
import com.ufms.mediadorpedagogico.presentation.util.extensions.unsafeLet
import com.ufms.mediadorpedagogico.presentation.util.resources.AndroidStringProvider
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import com.ufms.mediadorpedagogico.presentation.util.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import java.util.*

class MainViewModel(
    private val schedulerProvider: SchedulerProvider,
    private val manageNotices: ManageNotices,
    private val manageNews: ManageNews,
    private val helpManager: HelpManager,
    calendarDelegate: CalendarDelegate
) : BaseViewModel(), CalendarDelegate by calendarDelegate {

    val errors: LiveData<Event<InvalidFieldsException>> get() = _errors
    val noContentReturned: LiveData<Event<Boolean>> get() = _noContentReturned
    val playTour: LiveData<Event<Boolean>> get() = _playTour
    val today: LiveData<String> get() = _today

    private val _errors: MutableLiveData<Event<InvalidFieldsException>> = MutableLiveData()
    private val _noContentReturned: MutableLiveData<Event<Boolean>> = MutableLiveData()
    private val _playTour: MutableLiveData<Event<Boolean>> = MutableLiveData()
    private val _today = MutableLiveData<String>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        //TODO buscar dados da escola e turma
        super.onCreate()
        subscribeToTopics()
        _playTour.value = Event(helpManager.isFirstTime())
        val today = Calendar.getInstance().getToday()
        val day = getTodayWeekDay()
        _today.value = "$today ($day)"
    }

    fun onCalendarClicked() {
        getCalendar({}, {}, ::setPlaceholder)
    }

    internal fun tourWasPlayed() {
        helpManager.tourWasPlayed()
    }

    private fun subscribeToTopics() {
        unsafeLet(manageNews.isSubscribed(), manageNotices.isSubscribed()) {
            manageNews.subscribe(true)
            manageNotices.subscribe(true)
            subscriberHandler(FirebaseMessagingServiceHandler.KEY_TOPIC_NOTICES)
            subscriberHandler(FirebaseMessagingServiceHandler.KEY_TOPIC_NEWS)
        }
    }

    private fun onFailure(throwable: Throwable) {
        setDialog(throwable)
        _noContentReturned.value = Event(true)
    }

    private fun getTodayWeekDay(): String {
        return when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            Calendar.SUNDAY -> strings.sunday
            Calendar.MONDAY -> strings.monday
            Calendar.TUESDAY -> strings.tuesday
            Calendar.WEDNESDAY -> strings.wednesday
            Calendar.THURSDAY -> strings.thursday
            Calendar.FRIDAY -> strings.friday
            else -> strings.saturday
        }
    }
}
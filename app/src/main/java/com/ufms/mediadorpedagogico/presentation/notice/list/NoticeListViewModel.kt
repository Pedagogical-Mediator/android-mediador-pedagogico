package com.ufms.mediadorpedagogico.presentation.notice.list

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.domain.entity.notice.Notice
import com.ufms.mediadorpedagogico.domain.entity.notice.NoticeContent
import com.ufms.mediadorpedagogico.domain.extensions.defaultSched
import com.ufms.mediadorpedagogico.domain.interactor.notice.GetNotice
import com.ufms.mediadorpedagogico.domain.interactor.user.InvalidFieldsException
import com.ufms.mediadorpedagogico.presentation.notice.details.NoticeDetailsNavData
import com.ufms.mediadorpedagogico.presentation.util.extensions.defaultPlaceholders
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import com.ufms.mediadorpedagogico.presentation.util.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy

class NoticeListViewModel(
    private val schedulerProvider: SchedulerProvider,
    private val getNotice: GetNotice
) : BaseViewModel() {

    val errors: LiveData<Event<InvalidFieldsException>> get() = _errors
    val goToNoticedDetails: LiveData<Boolean> get() = _goToNoticedDetails
    val noticeContent: LiveData<Event<List<Notice>>> get() = _noticeContent
    val noContentReturned: LiveData<Event<Boolean>> get() = _noContentReturned

    private val _errors: MutableLiveData<Event<InvalidFieldsException>> = MutableLiveData()
    private val _goToNoticedDetails: MutableLiveData<Boolean> = MutableLiveData()
    private val _noticeContent: MutableLiveData<Event<List<Notice>>> = MutableLiveData()
    private val _noContentReturned: MutableLiveData<Event<Boolean>> = MutableLiveData()
    private var pageNumber = 0

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        loadMoreNotice()
    }

    fun loadMoreNotice() {
        getNotice.execute(pageNumber)
            .defaultPlaceholders(this::setPlaceholder)
            .defaultSched(schedulerProvider)
            .subscribeBy(this::onFailure, this::onSuccess)
    }

    fun setupOnItemClicked(notice: Notice) {
        goTo(NoticeDetailsNavData(notice))
    }

    private fun onFailure(throwable: Throwable) {
        setDialog(throwable)
        _noContentReturned.value = Event(true)
    }

    private fun onSuccess(content: NoticeContent) {
        content.content?.run {
            if (isEmpty()) {
                _noContentReturned.value = Event(true)
            } else {
                _noticeContent.value = Event(this)
            }
        }
        pageNumber++
    }
}
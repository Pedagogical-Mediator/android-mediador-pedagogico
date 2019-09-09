package com.ufms.mediadorpedagogico.presentation.notice.list

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.domain.entity.homework.Homework
import com.ufms.mediadorpedagogico.domain.entity.homework.HomeworkContent
import com.ufms.mediadorpedagogico.domain.extensions.defaultSched
import com.ufms.mediadorpedagogico.domain.interactor.homework.GetHomework
import com.ufms.mediadorpedagogico.domain.interactor.user.InvalidFieldsException
import com.ufms.mediadorpedagogico.presentation.homework.details.HomeworkDetailsNavData
import com.ufms.mediadorpedagogico.presentation.util.extensions.defaultPlaceholders
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import com.ufms.mediadorpedagogico.presentation.util.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy

class HomeworkListViewModel(
    private val schedulerProvider: SchedulerProvider,
    private val getHomework: GetHomework
) : BaseViewModel() {

    val errors: LiveData<Event<InvalidFieldsException>> get() = _errors
    val goToHomeworkdDetails: LiveData<Boolean> get() = _goToHomeworkdDetails
    val homeworkContent: LiveData<Event<List<Homework>>> get() = _homeworkContent
    val noContentReturned: LiveData<Event<Boolean>> get() = _noContentReturned

    private val _errors: MutableLiveData<Event<InvalidFieldsException>> = MutableLiveData()
    private val _goToHomeworkdDetails: MutableLiveData<Boolean> = MutableLiveData()
    private val _homeworkContent: MutableLiveData<Event<List<Homework>>> = MutableLiveData()
    private val _noContentReturned: MutableLiveData<Event<Boolean>> = MutableLiveData()
    private var pageNumber = 0

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        loadMoreHomework()
    }

    fun loadMoreHomework() {
        getHomework.execute(pageNumber)
            .defaultPlaceholders(this::setPlaceholder)
            .defaultSched(schedulerProvider)
            .subscribeBy(this::onFailure, this::onSuccess)
    }

    fun setupOnItemClicked(homework: Homework) {
        goTo(HomeworkDetailsNavData(homework))
    }

    private fun onFailure(throwable: Throwable) {
        setDialog(throwable)
        _noContentReturned.value = Event(true)
    }

    private fun onSuccess(content: HomeworkContent) {
        content.content?.run {
            if (isEmpty()) {
                _noContentReturned.value = Event(true)
            } else {
                _homeworkContent.value = Event(this)
            }
        }
        pageNumber++
    }
}
package com.ufms.mediadorpedagogico.presentation.news

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.domain.entity.news.News
import com.ufms.mediadorpedagogico.domain.entity.news.NewsContent
import com.ufms.mediadorpedagogico.domain.extensions.defaultSched
import com.ufms.mediadorpedagogico.domain.interactor.news.GetNews
import com.ufms.mediadorpedagogico.presentation.util.extensions.defaultPlaceholders
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import com.ufms.mediadorpedagogico.presentation.util.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy

class NewsListViewModel(
    private val schedulerProvider: SchedulerProvider,
    private val getNews: GetNews
) : BaseViewModel() {

    val goToNewsDetails: LiveData<Boolean> get() = _goToNewsDetails
    val newsContent: LiveData<Event<List<News>>> get() = _newsContent
    val noContentReturned: LiveData<Event<Boolean>> get() = _noContentReturned

    private val _goToNewsDetails: MutableLiveData<Boolean> = MutableLiveData()
    private val _newsContent: MutableLiveData<Event<List<News>>> = MutableLiveData()
    private val _noContentReturned: MutableLiveData<Event<Boolean>> = MutableLiveData()
    private var pageNumber = 0
    private var isLoading = false
    private var thereAreMoreToLoad = true

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        loadMoreNews()
    }

    fun loadMoreNews() {
        if (!isLoading && thereAreMoreToLoad) {
            getNews.execute(pageNumber)
                .defaultPlaceholders(this::setPlaceholder)
                .defaultSched(schedulerProvider)
                .subscribeBy(this::onFailure, this::onSuccess)
        }
    }

    private fun onFailure(throwable: Throwable) {
        setDialog(throwable)
        _noContentReturned.value = Event(true)
    }

    private fun onSuccess(content: NewsContent) {
        content.content?.run {
            isLoading = false
            thereAreMoreToLoad = size == 10
            if (isEmpty()) {
                _noContentReturned.value = Event(true)
            } else {
                _newsContent.value = Event(this)
            }
        }
        pageNumber++
    }
}
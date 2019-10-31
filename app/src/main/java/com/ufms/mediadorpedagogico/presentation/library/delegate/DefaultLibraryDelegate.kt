package com.ufms.mediadorpedagogico.presentation.library.delegate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ufms.mediadorpedagogico.domain.entity.LibResource
import com.ufms.mediadorpedagogico.domain.entity.Topic
import com.ufms.mediadorpedagogico.domain.extensions.defaultSched
import com.ufms.mediadorpedagogico.domain.interactor.library.GetLibResources
import com.ufms.mediadorpedagogico.domain.interactor.library.GetTopic
import com.ufms.mediadorpedagogico.presentation.util.extensions.defaultPlaceholders
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import com.ufms.mediadorpedagogico.presentation.util.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import io.reactivex.rxkotlin.subscribeBy

class DefaultLibraryDelegate(
    private val getTopic: GetTopic,
    private val getLibResources: GetLibResources,
    private val schedulerProvider: SchedulerProvider
) : LibraryDelegate {

    override val topics: LiveData<Event<List<Topic>>> get() = _topics
    override val libResources: LiveData<Event<List<LibResource>>> get() = _libResources
    override val noContentReturned: LiveData<Event<Unit>> get() = _noContentReturned

    private val _topics = MutableLiveData<Event<List<Topic>>>()
    private val _libResources = MutableLiveData<Event<List<LibResource>>>()
    private val _noContentReturned = MutableLiveData<Event<Unit>>()

    override fun getTopics(
        onSuccess: (List<Topic>) -> Unit,
        onFailure: (Throwable) -> Unit,
        placeholderAction: (Placeholder) -> Unit
    ) {
        getTopic
            .execute()
            .defaultSched(schedulerProvider)
            .defaultPlaceholders(placeholderAction)
            .subscribeBy(onFailure) {
                _topics.value = Event(it)
            }
    }

    override fun getLibResources(
        id: Int,
        onSuccess: (List<LibResource>) -> Unit,
        onFailure: (Throwable) -> Unit,
        placeholderAction: (Placeholder) -> Unit
    ) {
        getLibResources
            .execute(id)
            .defaultSched(schedulerProvider)
            .defaultPlaceholders(placeholderAction)
            .subscribeBy(onFailure) {
                if (it.isEmpty()) {
                    _noContentReturned.value = Event(Unit)
                } else {
                    _libResources.value = Event(it)
                }
            }
    }
}
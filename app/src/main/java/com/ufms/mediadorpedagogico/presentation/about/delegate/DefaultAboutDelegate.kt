package com.ufms.mediadorpedagogico.presentation.about.delegate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ufms.mediadorpedagogico.domain.entity.About
import com.ufms.mediadorpedagogico.domain.extensions.defaultSched
import com.ufms.mediadorpedagogico.domain.interactor.about.GetAbout
import com.ufms.mediadorpedagogico.presentation.util.extensions.defaultPlaceholders
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import io.reactivex.rxkotlin.subscribeBy

class DefaultAboutDelegate constructor(
    private val getAbout: GetAbout,
    private val schedulerProvider: SchedulerProvider
) : AboutDelegate {

    override val aboutReceived: LiveData<About> get() = aboutReceivedLiveData

    private val aboutReceivedLiveData = MutableLiveData<About>()

    override fun getAbout(
        onSuccess: (About) -> Unit,
        onFailure: (Throwable) -> Unit,
        placeholderPlacerAction: (Placeholder) -> (Unit)
    ) {
        getAbout.execute().defaultSched(schedulerProvider)
            .defaultPlaceholders(placeholderPlacerAction)
            .subscribeBy(onFailure) {
                aboutReceivedLiveData.value = it
                onSuccess.invoke(it)
            }
    }
}
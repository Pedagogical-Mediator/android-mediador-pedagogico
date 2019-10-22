package com.ufms.mediadorpedagogico.presentation.bullying.delegate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ufms.mediadorpedagogico.domain.entity.Bullying
import com.ufms.mediadorpedagogico.domain.extensions.defaultSched
import com.ufms.mediadorpedagogico.domain.interactor.bullying.GetBullying
import com.ufms.mediadorpedagogico.presentation.util.extensions.defaultPlaceholders
import com.ufms.mediadorpedagogico.presentation.util.resources.SchedulerProvider
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import io.reactivex.rxkotlin.subscribeBy

class DefaultBullyingDelegate constructor(
    private val getBullying: GetBullying,
    private val schedulerProvider: SchedulerProvider
) : BullyingDelegate {

    override val bullyingReceived: LiveData<Bullying> get() = bullyingReceivedLiveData

    private val bullyingReceivedLiveData = MutableLiveData<Bullying>()

    override fun getBullying(
        onSuccess: (Bullying) -> Unit,
        onFailure: (Throwable) -> Unit,
        placeholderPlacerAction: (Placeholder) -> (Unit)
    ) {
        getBullying.execute().defaultSched(schedulerProvider)
            .defaultPlaceholders(placeholderPlacerAction)
            .subscribeBy(onFailure) {
                bullyingReceivedLiveData.value = it
                onSuccess.invoke(it)
            }
    }
}
package com.ufms.mediadorpedagogico.presentation.util.extensions

import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import io.reactivex.Single

fun <T> Single<T>.defaultPlaceholders(placeholderPlacerAction: (Placeholder) -> (Unit)): Single<T> {
    return this.defaultConsumers(
        { placeholderPlacerAction(Placeholder.Loading()) },
        { placeholderPlacerAction(Placeholder.HideAll) })
}

fun <T> Single<T>.defaultConsumers(
    onSubscribeCallback: () -> (Unit),
    doAfterTerminatecallback: () -> (Unit)
): Single<T> {
    return this.doOnSubscribe { onSubscribeCallback.invoke() }
        .doAfterTerminate({ doAfterTerminatecallback.invoke() })
}

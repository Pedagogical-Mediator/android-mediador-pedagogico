package com.ufms.mediadorpedagogico.presentation.util.extensions

import com.ufms.mediadorpedagogico.domain.extensions.defaultConsumers
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder
import io.reactivex.Observable
import io.reactivex.Single


fun <T> Single<T>.defaultPlaceholders(placeholderPlacerAction: (Placeholder) -> (Unit)): Single<T> {
    return this.defaultConsumers(
        { placeholderPlacerAction(Placeholder.Loading()) },
        { placeholderPlacerAction(Placeholder.HideAll) })
}

fun <T> Observable<T>.defaultPlaceholders(placeholderPlacerAction: (Placeholder) -> (Unit)): Observable<T> {
    return this.defaultConsumers(
        { placeholderPlacerAction(Placeholder.Loading()) },
        { placeholderPlacerAction(Placeholder.HideAll) })
}

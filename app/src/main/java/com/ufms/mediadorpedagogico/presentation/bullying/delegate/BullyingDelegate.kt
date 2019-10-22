package com.ufms.mediadorpedagogico.presentation.bullying.delegate

import androidx.lifecycle.LiveData
import com.ufms.mediadorpedagogico.domain.entity.Bullying
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder

interface BullyingDelegate {
    val bullyingReceived: LiveData<Bullying>
    fun getBullying(
        onSuccess: (Bullying) -> Unit,
        onFailure: (Throwable) -> Unit,
        placeholderPlacerAction: (Placeholder) -> (Unit)
    )
}
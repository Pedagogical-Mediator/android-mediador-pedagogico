package com.ufms.mediadorpedagogico.presentation.about.delegate

import androidx.lifecycle.LiveData
import com.ufms.mediadorpedagogico.domain.entity.About
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder

interface AboutDelegate {
    val aboutReceived: LiveData<About>
    fun getAbout(
        onSuccess: (About) -> Unit,
        onFailure: (Throwable) -> Unit,
        placeholderPlacerAction: (Placeholder) -> (Unit)
    )
}
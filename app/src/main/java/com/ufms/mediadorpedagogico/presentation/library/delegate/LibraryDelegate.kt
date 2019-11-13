package com.ufms.mediadorpedagogico.presentation.library.delegate

import androidx.lifecycle.LiveData
import com.ufms.mediadorpedagogico.domain.entity.library.LibResource
import com.ufms.mediadorpedagogico.domain.entity.library.Topic
import com.ufms.mediadorpedagogico.presentation.util.structure.arch.Event
import com.ufms.mediadorpedagogico.presentation.util.viewmodels.Placeholder

interface LibraryDelegate {
    val topics: LiveData<List<Topic>>
    val libResources: LiveData<Event<List<LibResource>>>
    val noContentReturned: LiveData<Event<Unit>>

    fun getTopics(
        onSuccess: (List<Topic>) -> Unit,
        onFailure: (Throwable) -> Unit,
        placeholderAction: (Placeholder) -> Unit
    )

    fun getLibResources(
        id: Int,
        onSuccess: (List<LibResource>) -> Unit,
        onFailure: (Throwable) -> Unit,
        placeholderAction: (Placeholder) -> Unit
    )
}
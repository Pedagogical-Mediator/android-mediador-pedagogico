package com.ufms.mediadorpedagogico.presentation.bullying

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.presentation.bullying.delegate.BullyingDelegate
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel

class BullyingViewModel constructor(
    bullyingDelegate: BullyingDelegate
) : BaseViewModel(), BullyingDelegate by bullyingDelegate {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        super.onCreate()
        getBullying({}, {}, ::setPlaceholder)
    }
}
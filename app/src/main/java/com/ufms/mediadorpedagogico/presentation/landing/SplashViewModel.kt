package com.ufms.mediadorpedagogico.presentation.landing

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.OnLifecycleEvent
import com.ufms.mediadorpedagogico.domain.extensions.scheduleCallback
import com.ufms.mediadorpedagogico.domain.interactor.user.GetPersistedUser
import com.ufms.mediadorpedagogico.presentation.login.LoginNavData
import com.ufms.mediadorpedagogico.presentation.main.MainNavData
import com.ufms.mediadorpedagogico.presentation.util.extensions.defaultMutableLiveData
import com.ufms.mediadorpedagogico.presentation.util.structure.base.BaseViewModel

class SplashViewModel(
    private val getPersistedUser: GetPersistedUser
) : BaseViewModel() {

    val goToMain: LiveData<Boolean> get() = goToMainLiveData
    val goToLogin: LiveData<Boolean> get() = goToLoginLiveData

    private val goToMainLiveData = defaultMutableLiveData(false)
    private val goToLoginLiveData = defaultMutableLiveData(false)

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun checkUser() {
        scheduleCallback(DEFAULT_DELAY_IN_SECONDS) {
            if (getPersistedUser.execute() == null) {
                goTo(LoginNavData())
            } else {
                goTo(MainNavData())
            }
        }
    }

    companion object {
        const val DEFAULT_DELAY_IN_SECONDS = 2L
    }
}